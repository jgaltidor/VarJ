package tame
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode
import java.io.File
import java.io.IOException

object Tester
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "tame.Tester")
		IterSeq.getSrcGenerics(vf.getProgram).foreach(processGeneric)
	}

	def processGeneric(gtd:AST.GenericTypeDecl):Unit = {
		for(index <- 0 until gtd.getNumTypeParameter) {
			val param = gtd getTypeParameter index
			val dvar = gtd getDVar param
			val bounds = ASTNode.strategy.varBounds(gtd, param).toList
			println(dvar + ": " + dvar.eval())
			println("-"*32)
			println("bounds:")
			for((bound, j) <- bounds.zipWithIndex) {
				printf("  bound %d: %s%n", j+1, bound)
			}
			println("dvar.dvarBoundClosure: " + dvar.varBoundClosure)
			println("dvar.isRecursivelyBounded: " + dvar.isRecursivelyBounded)
		}
	}
}


object LookupVar extends FilesParams
{
	@Parameter(names = Array("-g", "--generic"), required = true,
						 description = "Name of generic to look up dvars")
	var genericName:String = null

	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "tame.LookupVar")
		IterSeq.getGeneric(vf.getProgram, genericName) match
		{
			case Some(gtd) => Tester.processGeneric(gtd)
			case None =>
				Console.err.println("Generic not found: " + genericName)
		}
	}
}


object AnalyzeType extends FilesParams
{
	@Parameter(names = Array("-t", "--type"), required = true,
	           description = "Name of class/interface to analyze")
	var typeName:String = null


	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "tame.AnalyzeType")
		IterSeq.getType(vf.getProgram, typeName) match
		{
			case Some(typeDecl) => analyzeTypeDec(typeDecl)
			case None =>
				Console.err.println("Class/Interface not found: " + typeName)
		}
	}

	def analyzeTypeDec(typeDecl:AST.TypeDecl):Unit = {
		println("type: " + typeDecl.fullName)
		typeDecl.logFieldFlowsTo
	}
}


object RewriteSources extends FilesParams
{
	@Parameter(names = Array("-t", "--types"), required = true,
	           description = "Type defs to rewrite separated by ','")
	var typesToRewriteStr:String = null

	@Parameter(names = Array("-m", "--modfile"), required = true,
	           description = "File to write modification specification")
	var modificationSpec:String = null

	@Parameter(names = Array("-d", "--outdir"), required = true,
	           description = "Directory containing rewritten files")
	var newSourcesDirName:String = null

	/** Set of input type defs that the user specified to rewrite.
	  * Should be accessed only after command line arguments are processed.
	  */
	var typesToRewriteNames:Seq[String] = null

	@throws(classOf[ParameterException])
	@throws(classOf[IOException])
	override def preprocessArgs(args:Array[String]):Unit = {
		super.preprocessArgs(args)
		
		typesToRewriteNames = typesToRewriteStr split ","
		if(typesToRewriteNames.isEmpty)
			throw new ParameterException("No types specified for rewrite")
		
		val newSourcesDir = new File(newSourcesDirName)
		if(newSourcesDir.isFile)
			throw new ParameterException(String.format(
				"%s is an existing file", newSourcesDirName))

		ASTNode.rewriteOut = new java.io.PrintStream(modificationSpec)
	}
	
	@throws(classOf[IOException])
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "tame.RewriteSources")
		val typesToRewrite = new java.util.LinkedList[AST.TypeDecl]
		for(typeName <- typesToRewriteNames) {
			IterSeq.getType(vf.getProgram, typeName) match
			{
				case Some(typeDecl) => typesToRewrite add typeDecl
				case None =>
					Console.err.println("Class/Interface not found: " + typeName)
			}
		}
		// Generate modificationSpec
		ASTNode generateRewrites typesToRewrite
		// write fake replacement info so that all input source files
		// are copied to the target directory (newSourcesDir) even if
		// some source files did not require any rewrites
		vf.getProgram.writeFakeReplaceInfo
		// all writes to ASTNode.rewriteOut performed so closing the file
		ASTNode.rewriteOut.close
		
		
		val modificationSpecFile = new File(modificationSpec)
		val newSourcesDir = new File(newSourcesDirName)
		if(!newSourcesDir.isDirectory) {
			println("Creating directory: " + newSourcesDir.mkdirs)
		}
		println("Performing rewrites specified in: " + modificationSpecFile)
		txtreplace.ReplaceText.rewriteFiles(modificationSpecFile, newSourcesDir)
	}
}

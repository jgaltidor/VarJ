package ui
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode
import AST.TypeDecl
import java.io.File
import java.io.IOException
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._


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
		BaseParams.processArgsAndCompile(args, vf, this, "ui.RewriteSources")
		val typesToRewrite = new java.util.LinkedList[TypeDecl]
		for(typeName <- typesToRewriteNames) {
			IterSeq.getType(vf.getProgram, typeName) match
			{
				case Some(typeDecl) => typesToRewrite add typeDecl
				case None =>
					Console.err.println("Class/Interface not found: " + typeName)
			}
		}
		println("Computing rewrites to perform")
		// Generate modificationSpec
		for(typdecl <- typesToRewrite) {
			println("Computing rewrites for: " + typdecl.fullName)
			typdecl.rewriteMemberTypes
		}
		// write fake replacement info so that all input source files
		// are copied to the target directory (newSourcesDir) even if
		// some source files did not require any rewrites
		vf.getProgram.writeFakeReplaceInfo
		// all writes to ASTNode.rewriteOut performed so closing the file
		ASTNode.rewriteOut.close
		
		
		val modificationSpecFile = new File(modificationSpec)
		val newSourcesDir = new File(newSourcesDirName)
		if(!newSourcesDir.isDirectory) {
			println("Creating directory: " + newSourcesDir)
			newSourcesDir.mkdirs
		}
		println("Performing rewrites specified in: " + modificationSpecFile)
		txtreplace.ReplaceText.rewriteFiles(modificationSpecFile, newSourcesDir)
	}
}

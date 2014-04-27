package ui
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode
import AST.TypeDecl
import java.io.File
import java.io.IOException
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._


object RewriteSources extends FilesCLParser
{
	@Parameter(names = Array("-t", "--type"), required = true,
	           description = "Type defs to rewrite")
	var typesToRewriteNames:java.util.List[String] = AST.ASTUtils.createList[String]

	@Parameter(names = Array("-m", "--modfile"), required = true,
	           description = "File to write modification specification")
	var modificationSpec:String = null

	@Parameter(names = Array("-d", "--outdir"), required = true,
	           description = "Directory containing rewritten files")
	var newSourcesDirName:String = null
	
	
	protected override def optionsAreOK:Boolean = {
		if(!super.optionsAreOK)
			return false
		if(typesToRewriteNames.isEmpty) {
			Console.err.println("No types specified for rewrite")
			return false
		}
		if(new File(newSourcesDirName).isFile) {
			Console.err.printf("%s is an existing file", newSourcesDirName)
			Console.err.println
			return false
		}
		return true
	}
	
	@throws(classOf[IOException])
	def main(args:Array[String]):Unit =
	{
		val programName = RewriteSources.getClass.getName.split("\\$").head
		val jc = new JCommander
		jc addObject RewriteSources
		jc setProgramName programName
		val program =
		  RewriteSources.setJCommander(jc)
		                .parseArgs(args)
		                .buildAnalysisSettings
		                .compile
		                .getProgram
		ASTNode.rewriteOut = new java.io.PrintStream(modificationSpec)
		val typesToRewrite = new java.util.LinkedList[TypeDecl]
		val query = AST.ASTUtils.getDefaultProgramQuery
		for(typeName <- typesToRewriteNames) {
			val typeDecl = query.getType(program, typeName)
			if(typeDecl != null)
				typesToRewrite add typeDecl
			else
				Console.err.println("Class/Interface not found: " + typeName)
		}
		println("Computing rewrites to perform")
		// Generate modificationSpec
		for(typdecl <- typesToRewrite) {
			println("Computing rewrites for: " + typdecl.fullName)
			typdecl.rewriteTypesInSig
		}
		// write fake replacement info so that all input source files
		// are copied to the target directory (newSourcesDir) even if
		// some source files did not require any rewrites
		program.writeFakeReplaceInfo
		// all writes to ASTNode.rewriteOut performed so closing the file
		ASTNode.rewriteOut.close
		val modificationSpecFile = new File(modificationSpec)
		val newSourcesDir = new File(newSourcesDirName)
		if(!newSourcesDir.isDirectory) {
			println("Creating directory: " + newSourcesDir)
			newSourcesDir.mkdirs
		}
		println("Performing rewrites specified in: " + modificationSpecFile)
		backend.txtreplace.ReplaceText.rewriteFiles(
		  modificationSpecFile, newSourcesDir)
	}
}

package ui
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode
import java.io.File
import java.io.IOException

object RewriteAllSources extends FilesCLParser
{
	@Parameter(names = Array("-m", "--modfile"), required = true,
	           description = "File to write modification specification")
	var modificationSpec:String = null

	@Parameter(names = Array("-d", "--outdir"), required = true,
	           description = "Directory containing rewritten files")
	var newSourcesDirName:String = null
	
	protected override def optionsAreOK:Boolean = {
		if(!super.optionsAreOK)
			return false
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
		val programName = RewriteAllSources.getClass.getName.split("\\$").head
		val jc = new JCommander
		jc addObject RewriteAllSources
		jc setProgramName programName
		val program =
		  RewriteAllSources.setJCommander(jc)
		                   .parseArgs(args)
		                   .buildAnalysisSettings
		                   .compile
		                  .getProgram
		ASTNode.rewriteOut = new java.io.PrintStream(modificationSpec)
		// Generate modificationSpec
		program.rewriteAllVarTypes
		// all writes to ASTNode.rewriteOut performed so closing the file
		ASTNode.rewriteOut.close
	
		// create directory for newSourcesDirName if it
		// does not exists
		val newSourcesDir = new File(newSourcesDirName)
		if(!newSourcesDir.isDirectory) {
			println("Creating directory: " + newSourcesDir)
			newSourcesDir.mkdirs
		}
		val modificationSpecFile = new File(modificationSpec)
		println("Performing rewrites specified in: " + modificationSpecFile)
		backend.txtreplace.ReplaceText.rewriteFiles(
		  modificationSpecFile, newSourcesDir)
	}
}

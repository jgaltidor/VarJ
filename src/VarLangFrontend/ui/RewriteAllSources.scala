package ui
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode
import java.io.File
import java.io.IOException

object RewriteAllSources extends FilesParams
{
	@Parameter(names = Array("-m", "--modfile"), required = true,
	           description = "File to write modification specification")
	var modificationSpec:String = null

	@Parameter(names = Array("-d", "--outdir"), required = true,
	           description = "Directory containing rewritten files")
	var newSourcesDirName:String = null

	@throws(classOf[ParameterException])
	@throws(classOf[IOException])
	override def preprocessArgs(args:Array[String]):Unit = {
		super.preprocessArgs(args)
		
		val newSourcesDir = new File(newSourcesDirName)
		if(newSourcesDir.isFile)
			throw new ParameterException(String.format(
				"%s is an existing file", newSourcesDirName))

		ASTNode.rewriteOut = new java.io.PrintStream(modificationSpec)
	}
	
	@throws(classOf[IOException])
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "ui.RewriteAllSources")
		// Generate modificationSpec
		vf.getProgram.rewriteAllSources
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


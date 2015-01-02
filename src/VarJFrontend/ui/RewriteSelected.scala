package ui
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException

import AST.ASTNode
import AST.TypeDecl
import AST.AnalysisSettings

import java.io.File
import java.io.IOException
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._

import scala.util.parsing.json.JSON

object RewriteSelected extends FilesCLParser
{
	@Parameter(names = Array("--declsfile"), required = true,
	           description = "JSON file specifying which declarations to " +
	                         "include/exclude for rewriting with wildcards")
	var includesExcludesFileName:String = null

	@Parameter(names = Array("-m", "--modfile"), required = true,
	           description = "File to write modification (list of rewrites) specification")
	var modificationSpec:String = null

	@Parameter(names = Array("-d", "--outdir"), required = true,
	           description = "Directory containing rewritten files")
	var newSourcesDirName:String = null


	class IncludesExcludesPair(val includes:Seq[String], val excludes:Seq[String])
	{ }
	
	def getIncludesExcludes(jsonFile:File):IncludesExcludesPair =
		getIncludesExcludesFromJSONString(Utils.getText(jsonFile))

	def getIncludesExcludesFromJSONString(jsonStr:String):IncludesExcludesPair =
		JSON.parseFull(jsonStr) match {
			case Some(json) =>
				val map = json.asInstanceOf[Map[String, List[String]]]
				val includes = map("includes")
				val excludes = map("excludes")
				new IncludesExcludesPair(includes, excludes)
			case None =>
				throw new AST.VarAnalysisException(
					"JSON string could not be parsed:\n" + jsonStr)
		}
	
	
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
	
	override def createSettingsBuilder(
		sourcePaths:java.util.List[String]):AnalysisSettings.Builder =
	{
		val includesExcludes = getIncludesExcludes(
			new File(includesExcludesFileName))
		val rewriteStrategy = new AST.SelectedRewriteStrategy(
			includesExcludes.includes, includesExcludes.excludes)
		val builder = super.createSettingsBuilder(sourcePaths)
		val rewriteOut = new java.io.PrintStream(modificationSpec)
		builder.rewriteStrategy(rewriteStrategy)
		       .rewriteOut(rewriteOut)
	}
	
	
	@throws(classOf[IOException])
	def main(args:Array[String]):Unit =
	{
		val programName = RewriteSelected.getClass.getName.split("\\$").head
		val jc = new JCommander
		jc addObject RewriteSelected
		jc setProgramName programName
		val program =
		  RewriteSelected.setJCommander(jc)
		                .parseArgs(args)
		                .buildAnalysisSettings
		                .compile
		                .getProgram
		// Generate modificationSpec
		program.rewriteTypesInSig()
		// all writes to ASTNode.settings.rewriteOut performed
		// so closing the file
		ASTNode.settings.rewriteOut.close

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

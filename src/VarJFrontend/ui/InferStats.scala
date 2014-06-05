package ui
import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode._
// importing implicit conversions for java.util collections
import scala.collection.JavaConversions._
import java.util.Collections

object InferStats extends BaseCLParser
{
	// Main Parameter
  @Parameter(description = "<dir1:nameN> ... <dirN:nameN>")
  var pathNameArgs:java.util.List[String] = AST.ASTUtils.createList[String]

	@Parameter(names = Array("-t", "--texout"), required = true,
	           description = "Name of tex file to generate")
	var outTexFileName:String = null

	@Parameter(names = Array("--json"), required = false,
	           description = "Name of JSON file to generate")
	var jsonFileName:String = null
	
	def processLib(libpath:String, libname:String):LibStats = {
		val program =
  	  buildAnalysisSettings(Collections.singletonList(libpath))
  		  .compile
  		  .getProgram
		printf("Analyzing library %s in path %s", libname, libpath)
		println
		val query = AST.ASTUtils.getDefaultProgramQuery
		val typeDecls = query getTypes program
		val libstats = ComputeStats computeStats typeDecls
		libstats.name = libname
		println("Completed analysis of: " + libname)
		libstats
	}
	
	def main(args:Array[String]):Unit = {
		val programName = InferStats.getClass.getName.split("\\$").head
		val jc = new JCommander
		jc addObject InferStats
		jc setProgramName programName
		InferStats.setJCommander(jc)
		          .parseArgs(args)
		val pathNamePairs = getPathNamePairs(pathNameArgs)
		val allLibStats:Seq[LibStats] =
			for((libpath, libname) <- pathNamePairs) yield {
				val libstats = processLib(libpath, libname)
				// free up memory from last run
				Runtime.getRuntime.gc
				libstats
			}
		val allstats = new AllStats(allLibStats)
		// val outTexFileName = parser.outTexFileName
		println("Writing out Tex Table to file: " + outTexFileName)
		Utils.writeToFile(Table1.texTable(allstats), outTexFileName)
		Utils.appendToFile(Table3.texTable(allstats), outTexFileName)
		// val jsonFileName = parser.jsonFileName
		if(jsonFileName != null) {
			println("Writing out to JSON file: " + jsonFileName)
			Utils.writeToFile(allstats.toJSON.toString(), jsonFileName)
		}
		println("Successful completion")
	}

	def getPathNamePairs(pathNames:Seq[String]):Seq[(String,String)] = {
		def splitPathName(pathName:String):(String,String) = {
			val i = pathName lastIndexOf ':'
			(pathName.substring(0, i), pathName.substring(i+1))
		}
		try {
			return pathNames map splitPathName
		}
		catch {
			case exc : Exception =>
				throw new ParameterException(String.format(
					"Error while parsing path-name pairs: " + exc.getMessage))
		}
	}
}

package ui
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.ASTNode._
// importing implicit conversions for java.util collections
import scala.collection.JavaConversions._
import java.util.ArrayList


object InferStats extends BaseParams
{
	// Main Parameter
  @Parameter(description = "<dir1:nameN> ... <dirN:nameN>")
  var pathNameArgs:java.util.List[String] = new ArrayList[String]

	@Parameter(names = Array("-t", "--texout"), required = true,
	           description = "Name of tex file to generate")
	var outTexFileName:String = null

	@Parameter(names = Array("--json"), required = false,
	           description = "Name of JSON file to generate")
	var jsonFileName:String = null

	/** Should be accessed only after command line arguments are processed */
	var pathNamePairs:Seq[(String,String)] = null
	
	/** Should be accessed only after command line arguments are processed */
	var optionArgs:Seq[String] = null
	
	@throws(classOf[ParameterException])
	override def preprocessArgs(args:Array[String]):Unit = {
		super.preprocessArgs(args)
		
		optionArgs = List.empty ++ createCompilerArgs

		def splitPathName(pathName:String):(String,String) = {
			val i = pathName lastIndexOf ':'
			(pathName.substring(0, i), pathName.substring(i+1))
		}
		try {
			pathNamePairs = pathNameArgs map splitPathName
		}
		catch {
			case exc : Exception =>
				throw new ParameterException(String.format(
					"Error while parsing path-name pairs: " + exc.getMessage))
		}
		if (pathNamePairs.isEmpty)
			throw new ParameterException("No path-name pairs specified")
	}


	def main(args:Array[String]):Unit = {
		BaseParams.parseAndPreProcess(args,
			new VarFrontend, this, "ui.InferStats")

		val allLibStats:Seq[LibStats] =
			for((libpath, libname) <- pathNamePairs) yield {
				val libstats = processLib(libpath, libname)
				// free up memory from last run
				Runtime.getRuntime.gc
				libstats
			}
		val allstats = new AllStats(allLibStats)
		println("Writing out Tex Table to file: " + outTexFileName)
		Utils.writeToFile((new Table1).texTable(allstats), outTexFileName)
		Utils.appendToFile((new Table3).texTable(allstats), outTexFileName)
		if(jsonFileName != null) {
			println("Writing out to JSON file: " + jsonFileName)
			Utils.writeToFile(allstats.toJSON.toString(), jsonFileName)
		}
		println("Successful completion")
	}


	def processLib(libpath:String, libname:String):LibStats = {
		// Compute new args
		// Collect source files
		val sourceFiles = FilesParams.collectSourceFiles(libpath)
		val newArgs = (optionArgs ++ sourceFiles).toArray
		printf("Analyzing library %s in path %s", libname, libpath)
		println
		val vf = new VarFrontend
		VarFrontend.compile(vf, newArgs)
		val program = vf.getProgram
		val typeDecls = IterSeq getSrcTypes program
		val libstats = ComputeStats computeStats typeDecls
		libstats.name = libname
		println("Completed analysis of: " + libname)
		libstats
	}
}

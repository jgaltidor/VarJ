package tame

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

	@Parameter(names = Array("t", "--texout"), required = true,
	           description = "Name of tex file to generate")
	var outTexFileName:String = null
	
	/** Should be accessed only after command line arguments are processed */
	var pathNamePairs:Seq[(String,String)] = null
	
	/** Should be called after command line arguments are processed */
	var optionArgs:java.util.List[String] = null
	
	@throws(classOf[ParameterException])
	override def preprocessArgs(args:Array[String]):Unit = {
		super.preprocessArgs(args)
		
		optionArgs = createCompilerArgs

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
			new VarFrontend, this, "tame.InferStats")

		val allLibStats:Seq[LibStats] = pathNamePairs.map { p =>
			val libstats = processLib(p._1, p._2)
			// free up memory from last run
			Runtime.getRuntime.gc
			libstats
		}
		val allstats = new AllStats(allLibStats)
		println("Writing out Tex Table to file: " + outTexFileName)
		Utils.writeToFile(Table1.texTable(allstats), outTexFileName)
		println("Successful completion")
	}


	def processLib(libpath:String, libname:String):LibStats = {
		// Compute new args
		// Collect source files
		val sourceFiles = FilesParams.collectSourceFiles(libpath)
		var newArgs = (optionArgs ++ sourceFiles).toArray
		println("Analyzing library: " + libname)
		val vf = new VarFrontend
		VarFrontend.compile(vf, newArgs)
		val typeDecls = IterSeq getSrcTypes vf.getProgram
		val libstats = ComputeStats computeStats typeDecls
		libstats.name = libname
		println("Completed analysis of: " + libname)
		libstats
	}
}


object ComputeStats
{
	import AST.TypeDecl
	import AST.GenericTypeDecl
	import AST.ASTNode._

	def computeStats(typeDecls:Seq[TypeDecl]):LibStats = {
		val libstats = new LibStats
		val startTime = System.currentTimeMillis
		for { typ <- typeDecls
		      if(typ.isClassDecl || typ.isInterfaceDecl)
		}
		{
			val vstats = 
				if(typ.isClassDecl) libstats.clsStats
				else libstats.intStats
			vstats.totalUselessWildcards += typ.uselessWildCardsInSig
			vstats.totalWildCardActuals  += typ.numWildCardActualsInSig
			vstats.totalOverSpecified += typ.overSpecifiedActualsInSig
			vstats.totalArgActuals += typ.numMethArgTypeActualsInSig
			if(!typ.isGenericType) {
				vstats.totalMonoTypes += 1
			}
			else {
				val gtd = typ.asInstanceOf[GenericTypeDecl]
				var isInvariant = false
				var isCovariant = false
				var isContravariant = false
				var isBivariant = false
				var isRecVar = false
				val numParams = gtd.getNumTypeParameter
				
				for(index <- 0 until numParams) {
					val dvar = gtd getDVar index
					val variance = dvar.eval
					if(variance equals INVARIANT) {
						vstats.totalInVarParams += 1
						isInvariant = true
					}
					else if(variance equals COVARIANT) {
						vstats.totalCoVarParams += 1
						isCovariant = true
					}
					else if(variance equals CONTRAVARIANT) {
						vstats.totalContraVarParams += 1
						isContravariant = true
					}
					else if(variance equals BIVARIANT) {
						vstats.totalBiVarParams += 1
						isBivariant = true
					}
					
					if(dvar.isRecursivelyBounded) {
						isRecVar = true
						vstats.totalRecVarParams += 1 
					}
				}
				if(isInvariant)     vstats.totalInVar += 1
				if(isCovariant)     vstats.totalCoVar += 1
				if(isContravariant) vstats.totalContraVar += 1
				if(isBivariant)     vstats.totalBiVar += 1
				if(isRecVar)        vstats.totalRecVar += 1
			}
		}
		val endTime = System.currentTimeMillis
		// # of milliseconds to analyze library
		libstats.runningTime = endTime - startTime
		libstats
	}
	
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "tame.ComputeStats")
		val typeDecls = IterSeq getSrcTypes vf.getProgram
		val libstats = computeStats(typeDecls)
		val allstats = new AllStats(List(libstats))
		print(Table1.texTable(allstats))
	}
}

package tame

// import implicit conversion for converting java.util collections
// import scala.collection.jcl.Conversions._
import scala.collection.JavaConversions._
import AST.ASTNode._

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
		if(args.length == 0) {
			vf.printUsage
			sys.exit(1)
		}
		val newArgs = vf getNewArgsRecursively args
		VarFrontend.compile(vf, newArgs)
		val typeDecls = IterSeq getSrcTypes vf.getProgram
		val libstats = computeStats(typeDecls)
		val allstats = new AllStats(List(libstats))
		print(Table1.texTable(allstats))
	}
}

class StatsFrontend extends VarFrontend
{
	var outTexFileName:String = null
	var pathNamePairs:Seq[(String,String)] = null
	var optionArgs:Seq[String] = null

	override def printUsage:Unit = {
		super.printUsage
		println("tame.StatsFrontend usage: <options> <dir1:nameN> ... <dirN:nameN>")
		println("tame.StatsFrontend options:")
		println("  -texout" + (" "*17) + "Name of tex file to generate")
	}
	
	override def initOptions:Unit = {
		super.initOptions
		options.addKeyValueOption("-texout")
	}

	def preprocessArgs(args:Array[String]):Array[String] = {

		def splitPathName(pathName:String):(String,String) = {
			val i = pathName lastIndexOf ':'
			(pathName.substring(0, i), pathName.substring(i+1))
		}
		val optFiles = getOptFilesPair(args) // calls initOptions
		pathNamePairs = optFiles.files.map(splitPathName)
		outTexFileName = getValueForRequiredOption("-texout")
		optionArgs = optFiles.options
		args // These args will be ignored 
	}
	
	def processLib(libpath:String, libname:String):LibStats = {
		// Compute new args
		var newArgs = (optionArgs ++ List(libpath)).toArray
		newArgs = getNewArgsRecursively(newArgs)
		println("Analyzing library: " + libname)
		val sf = new StatsFrontend
		VarFrontend.compile(sf, newArgs)
		val typeDecls = IterSeq getSrcTypes sf.getProgram
		val libstats = ComputeStats computeStats typeDecls
		libstats.name = libname
		println("Completed analysis of: " + libname)
		libstats
	}
}

object InferStats
{
	def main(args:Array[String]):Unit = {
		val frontend = new StatsFrontend
		if(args.length == 0) {
			frontend.printUsage
			sys.exit(1)
		}
		frontend preprocessArgs args
		val allLibStats:Seq[LibStats] = frontend.pathNamePairs.map { p =>
			val libstats = frontend.processLib(p._1, p._2)
			// free up memory from last run
			Runtime.getRuntime.gc
			libstats
		}
		val allstats = new AllStats(allLibStats)
		println("Writing out Tex Table to file: " + frontend.outTexFileName)
		Utils.writeToFile(Table1.texTable(allstats), frontend.outTexFileName)
		println("Successful completion")
	}
}

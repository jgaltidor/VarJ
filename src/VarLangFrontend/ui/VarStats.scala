package ui
import scala.util.parsing.json.{JSON,
                                JSONObject,
                                JSONArray}

import Utils.{toJSONObject,toJSONArray}

class AllStats(val allLibStats:Seq[LibStats])
{
	def totalLibStats:LibStats = {
		val totalLS = allLibStats.reduceLeft[LibStats]((x, y) => x + y)
		totalLS.name = "Total"
		totalLS
	}

	def toJSON = new JSONArray(
		allLibStats.toList map (ls => ls.toJSON)
	)
}

object AllStats
{
	def fromJSON(json:JSONArray):AllStats = {
		val allLibStats = json.list.map(
			a => LibStats.fromJSON(toJSONObject(a)))
		new AllStats(allLibStats)
	}
	
	def fromJSONString(str:String):AllStats = {
		val jsonList = JSON.parseFull(str).get.asInstanceOf[List[Any]]
		fromJSON(JSONArray(jsonList))
	}

	def fromJSONSFile(filename:String):AllStats =
		fromJSONString(Utils.getTextFromFile(filename))
}

class LibStats(val clsStats: VarStats, val intStats: VarStats)
{
	def this() = this(new VarStats, new VarStats)
	var name = "Library"
	
	/** time to analyze library in milliseconds */
	var runningTime:Long = 0
	
	def totalStats = clsStats + intStats
	
	def addFrom(other:LibStats):Unit = {
		this.clsStats addFrom other.clsStats
		this.intStats addFrom other.intStats
		this.runningTime += other.runningTime
	}

	def +(other:LibStats):LibStats = {
		val ls = new LibStats
		ls addFrom this
		ls addFrom other
		ls
	}
	
	def toJSON = JSONObject(Map(
		"Library"       -> name,
		"clsStats"      -> clsStats.toJSON,
		"intStats"      -> intStats.toJSON,
		"runningTime"   -> runningTime
	))
}

object LibStats
{
	def fromJSON(json: JSONObject):LibStats = {
		val clsStats =
			VarStats fromJSON toJSONObject(json.obj("clsStats"))
		val intStats =
			VarStats fromJSON toJSONObject(json.obj("intStats"))
		val ls = new LibStats(clsStats, intStats)
		json.obj get "Library" match {
			case Some(libval) => ls.name = libval.asInstanceOf[String]
			case None => ()
		}
		json.obj get "runningTime" match {
			case Some(libval) =>
				ls.runningTime = libval.asInstanceOf[Double].toLong
			case None => ()
		}
		ls
	}
}


class VarStats
{
	// asserted status

	/** Num of type definitions with no type parameters */
	var totalMonoTypes = 0

	var totalInVar = 0
	var totalCoVar = 0 
	var totalContraVar = 0 
	var totalBiVar = 0
	
	var totalInVarParams = 0
	var totalCoVarParams = 0 
	var totalContraVarParams = 0 
	var totalBiVarParams = 0

	var totalUselessWildcards = 0
	var totalWildCardActuals  = 0
	var totalOverSpecified = 0
	var totalArgActuals = 0
	
	var totalRecVar = 0
	var totalRecVarParams = 0
	var totalParamClosureSize = 0


	/** Number of parameterized decls */
	var totalPDecls = 0
	/** Num of rewritable pdecls */
	var totalRewritablePDecls = 0
	/** Num of rewritten decls */
	var totalRewritten = 0
	/** Sum of all cardinalities of flowsto sets */
	var totalFlowsTo:Long = 0
	/** Sum of all cardinalities of rewritable flowsto sets */
	var totalRewritableFlowsTo:Long = 0
	/** Num of decls of a variant type */
	var totalVDecls = 0
	/** Num of rewritable vdecls */
	var totalRewritableVDecls = 0
	/** Num of rewritten vdecls */
	var totalRewrittenVDecls = 0

	// inferred stats

	def totalVar = totalCoVar + totalContraVar + totalBiVar
	def totalGenerics = totalVar + totalInVar
	def totalTypeDefs = totalMonoTypes + totalGenerics

	def ratioInVar     = totalInVar.toDouble / totalGenerics
	def ratioCoVar     = totalCoVar.toDouble / totalGenerics
	def ratioContraVar = totalContraVar.toDouble / totalGenerics
	def ratioBiVar     = totalBiVar.toDouble / totalGenerics
	def ratioVar       = totalVar.toDouble / totalGenerics

	def totalVarParams = totalCoVarParams + totalContraVarParams + totalBiVarParams
	def totalTypeParams = totalVarParams + totalInVarParams

	def ratioInVarParams     = totalInVarParams.toDouble / totalTypeParams
	def ratioCoVarParams     = totalCoVarParams.toDouble / totalTypeParams
	def ratioContraVarParams = totalContraVarParams.toDouble / totalTypeParams
	def ratioBiVarParams     = totalBiVarParams.toDouble / totalTypeParams
	def ratioVarParams       = totalVarParams.toDouble / totalTypeParams
	

	def ratioUselessWildCards = totalUselessWildcards.toDouble / totalWildCardActuals
	def ratioOverSpecified = totalOverSpecified.toDouble / totalArgActuals
	def ratioRecVar = totalRecVar.toDouble / totalGenerics
	
	def ratioRecVarParams = totalRecVarParams.toDouble / totalTypeParams
	def ratioParamClosureSize = totalParamClosureSize.toDouble / totalTypeParams

	// flowsto inferred stats

	def ratioRewritablePDecls:Double =
	 totalRewritablePDecls.toDouble / totalPDecls

	def ratioRewritten:Double =
	 totalRewritten.toDouble / totalPDecls

	def averageFlowsToSize:Double =
	 totalFlowsTo.toDouble / totalPDecls

	def averageRewritableFlowsToSize:Double =
	 totalRewritableFlowsTo.toDouble / totalRewritablePDecls

	def ratioRewritableVDecls:Double =
	 totalRewritableVDecls.toDouble / totalVDecls

	def ratioRewrittenVDecls:Double =
	 totalRewrittenVDecls.toDouble / totalVDecls

	def addFrom(other:VarStats):Unit = {
		this.totalMonoTypes += other.totalMonoTypes
		
		this.totalInVar     += other.totalInVar
		this.totalCoVar     += other.totalCoVar
		this.totalContraVar += other.totalContraVar
		this.totalBiVar     += other.totalBiVar
		
		this.totalInVarParams     += other.totalInVarParams
		this.totalCoVarParams     += other.totalCoVarParams
		this.totalContraVarParams += other.totalContraVarParams
		this.totalBiVarParams     += other.totalBiVarParams

		this.totalUselessWildcards += other.totalUselessWildcards
		this.totalWildCardActuals  += other.totalWildCardActuals
		this.totalArgActuals    += other.totalArgActuals
		this.totalOverSpecified += other.totalOverSpecified

		this.totalRecVar             += other.totalRecVar
		this.totalRecVarParams       += other.totalRecVarParams
		this.totalParamClosureSize   += other.totalParamClosureSize
		
		this.totalPDecls             += other.totalPDecls
		this.totalRewritablePDecls   += other.totalRewritablePDecls
		this.totalRewritten          += other.totalRewritten
		this.totalFlowsTo            += other.totalFlowsTo
		this.totalRewritableFlowsTo  += other.totalRewritableFlowsTo
		this.totalVDecls             += other.totalVDecls
		this.totalRewritableVDecls   += other.totalRewritableVDecls
		this.totalRewrittenVDecls    += other.totalRewrittenVDecls
	}
	
	def +(other:VarStats):VarStats = {
		val vs = new VarStats
		vs addFrom this
		vs addFrom other
		vs
	}

	def toJSON = new JSONObject(Map(
		"totalMonoTypes" -> totalMonoTypes,

		"totalInVar" -> totalInVar,
		"totalCoVar" -> totalCoVar,
		"totalContraVar" -> totalContraVar,
		"totalBiVar" -> totalBiVar,

		"totalInVarParams" -> totalInVarParams,
		"totalCoVarParams" -> totalCoVarParams,
		"totalContraVarParams" -> totalContraVarParams,
		"totalBiVarParams" -> totalBiVarParams,

		"totalUselessWildcards" -> totalUselessWildcards,
		"totalWildCardActuals" -> totalWildCardActuals,
		"totalOverSpecified" -> totalOverSpecified,
		"totalArgActuals" -> totalArgActuals,

		"totalRecVar" -> totalRecVar,
		"totalRecVarParams" -> totalRecVarParams,
		"totalParamClosureSize" -> totalParamClosureSize,
		
		"totalPDecls"             -> totalPDecls,
		"totalRewritablePDecls"   -> totalRewritablePDecls,
		"totalRewritten"          -> totalRewritten,
		"totalFlowsTo"            -> totalFlowsTo,
		"totalRewritableFlowsTo"  -> totalRewritableFlowsTo,
		"totalVDecls"             -> totalVDecls,
		"totalRewritableVDecls"   -> totalRewritableVDecls,
		"totalRewrittenVDecls"    -> totalRewrittenVDecls
	))
}

// Utility Functions
object VarStats
{	
	def fromJSON(json: JSONObject):VarStats = {
		// For converting any to ints
		implicit def any2Int(any:Any) = any.asInstanceOf[Double].toInt
		implicit def any2Long(any:Any) = any.asInstanceOf[Double].toLong
	
		val vs = new VarStats
		vs.totalMonoTypes = json.obj("totalMonoTypes")

		vs.totalInVar = json.obj("totalInVar")
		vs.totalCoVar = json.obj("totalCoVar")
		vs.totalContraVar = json.obj("totalContraVar")
		vs.totalBiVar = json.obj("totalBiVar")

		vs.totalInVarParams = json.obj("totalInVarParams")
		vs.totalCoVarParams = json.obj("totalCoVarParams")
		vs.totalContraVarParams = json.obj("totalContraVarParams")
		vs.totalBiVarParams = json.obj("totalBiVarParams")

		vs.totalUselessWildcards = json.obj("totalUselessWildcards")
		vs.totalWildCardActuals = json.obj("totalWildCardActuals")
		vs.totalOverSpecified = json.obj("totalOverSpecified")
		vs.totalArgActuals = json.obj("totalArgActuals")

		vs.totalRecVar = json.obj("totalRecVar")
		vs.totalRecVarParams = json.obj("totalRecVarParams")
		vs.totalParamClosureSize = json.obj("totalParamClosureSize")
		
		vs.totalPDecls             = json.obj("totalPDecls")
		vs.totalRewritablePDecls   = json.obj("totalRewritablePDecls")
		vs.totalRewritten          = json.obj("totalRewritten")
		vs.totalFlowsTo            = json.obj("totalFlowsTo")
		vs.totalRewritableFlowsTo  = json.obj("totalRewritableFlowsTo")
		vs.totalVDecls             = json.obj("totalVDecls")
		vs.totalRewritableVDecls   = json.obj("totalRewritableVDecls")
		vs.totalRewrittenVDecls    = json.obj("totalRewrittenVDecls")

		vs
	}
}

object VarStatsTester
{
	// Main method for testing purpose
	def main(args:Array[String]):Unit = {
		val ls1 = new LibStats
		val ls2 = new LibStats
		ls1.name = "Lib 1"
		ls2.name = "Lib 2"
		val allstats = new AllStats(List(ls1, ls2))
		print((new Table1).texTable(allstats))
	}
}

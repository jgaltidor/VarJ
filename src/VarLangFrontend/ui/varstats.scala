package ui
// import String.format

object Table1
{
	import VarStats.texPercent
	import VarStats.texBold

	val tableHeader =
"""\begin{tabular}{|ll|c|c|c|c|c|c|c|c|c|c|c|c|c|} \hline
Library & & \# Type     & \# Generic  & \multicolumn{5}{c|}{Type Definitions} & Recursive & Unnecess. & Over-specif.\\
        & & defs & defs & invar. & variant & cov. & contrav. & biv. & variances & wildcards   & methods  \\
\hline
"""

	val tableSuffix =
"""\end{tabular}
"""

	val endTableRow = " \\\\ \n"

	def texTable(allstats:AllStats):String = {
		val sb = new StringBuilder(2 << 12)
		sb.append(tableHeader)
		for(libstats <- allstats.allLibStats) {
			sb.append(libTexRows(libstats))
			sb.append("\\hline \n")
		}
		sb.append(texTotalRows(allstats.totalLibStats))
		sb.append("\\hline \n")
		sb.append(tableSuffix)
		sb.toString
	}

	def libTexRows(libstats:LibStats):String = {
		val sb = new StringBuilder(256)
		// first row
		sb.append("""\multirow{3}{*}{%s} & classes & """.format(libstats.name))
		sb.append(statTexRow(libstats.clsStats)).append(endTableRow)
		// second row
		sb.append(" & interfaces & ")
		sb.append(statTexRow(libstats.intStats)).append(endTableRow)
		// third row
		sb.append(" & total & ")
		sb.append(statTexRow(libstats.totalStats)).append(endTableRow)
		sb.toString
	}

	def texTotalRows(totalLibStats:LibStats):String = {
		val sb = new StringBuilder(700)
		// first row
		sb.append("""\multirow{3}{*}{%s} & %s &""".format(
			texBold("Total"), texBold("classes")))
		sb.append(statTexRow(totalLibStats.clsStats, texBold)).append(endTableRow)
		// second row
		sb.append(" & %s & ".format(texBold("interfaces")))
		sb.append(statTexRow(totalLibStats.intStats, texBold)).append(endTableRow)
		// third row
		sb.append(" & %s & ".format(texBold("total")))
		sb.append(statTexRow(totalLibStats.totalStats, texBold)).append(endTableRow)
		sb.toString
	}


	def statTexRow(vs:VarStats):String =
		Utils.joinstr(" & ",
			vs.totalTypeDefs,
			vs.totalGenerics,
			texPercent(vs.ratioInVar),
			texPercent(vs.ratioVar),
			texPercent(vs.ratioCoVar),
			texPercent(vs.ratioContraVar),
			texPercent(vs.ratioBiVar),
			texPercent(vs.ratioRecVarParams),
			texPercent(vs.ratioUselessWildCards),
			texPercent(vs.ratioOverSpecified)
		)
	
	def statTexRow(vs:VarStats, formatter:Any => String):String =
		Utils.joinstr(" & ",
			formatter(vs.totalTypeDefs),
			formatter(vs.totalGenerics),
			formatter(texPercent(vs.ratioInVar)),
			formatter(texPercent(vs.ratioVar)),
			formatter(texPercent(vs.ratioCoVar)),
			formatter(texPercent(vs.ratioContraVar)),
			formatter(texPercent(vs.ratioBiVar)),
			formatter(texPercent(vs.ratioRecVarParams)),
			formatter(texPercent(vs.ratioUselessWildCards)),
			formatter(texPercent(vs.ratioOverSpecified))
		)
}


class AllStats(val allLibStats:Seq[LibStats])
{
	def totalLibStats:LibStats = {
		val totalLS = allLibStats.reduceLeft[LibStats]((x, y) => x + y)
		totalLS.name = "Total"
		totalLS
	}
}

class LibStats
{
	val clsStats = new VarStats
	val intStats = new VarStats
	var name = "Library"
	// time to analyze library in milliseconds
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
}


class VarStats
{
	var totalMonoTypes = 0 // type definitions with no type parameters

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
	}
	
	def +(other:VarStats):VarStats = {
		val vs = new VarStats
		vs addFrom this
		vs addFrom other
		vs
	}
}

// Utility Functions
object VarStats
{	
	def texPercent(d:Double):String = {
		val s = java.text.NumberFormat.getPercentInstance.format(d)
		val slen = s.length
		if(slen < 2) "0\\%" else s.substring(0, slen-1) + "\\%"
	}
	
	def texBold(any:Any):String =
		"""\textbf{%s}""" format any.toString
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
		print(Table1.texTable(allstats))
	}
}

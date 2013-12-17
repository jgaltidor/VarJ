package ui
import scala.util.parsing.json.{JSONObject,JSONArray}

class Table1
{
	def tableHeader =
"""\begin{tabular}{|ll|c|c|c|c|c|c|c|c|c|c|c|c|c|} \hline
Library & & \# Type     & \# Generic  & \multicolumn{5}{c|}{Type Definitions} & Recursive & Unnecess. & Over-specif.\\
        & & defs & defs & invar. & variant & cov. & contrav. & biv. & variances & wildcards   & methods  \\
\hline
"""

	def tableSuffix =
"""\end{tabular}
"""

	def endTableRow = " \\\\ \n"

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

	def texTotalRows(totalLibStats:LibStats):String =
	{
		def texBold(a:Any):String = "\\textbf{%s}" format a.toString
	
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

	def texPercent(d:Double):String = {
		val s = java.text.NumberFormat.getPercentInstance.format(d)
		val slen = s.length
		if(slen < 2) "0\\%" else s.substring(0, slen-1) + "\\%"
	}
}

class Table2
{
  private val table1 = new Table1
  import table1._

	def texTable(sigStats:AllStats, bodStats:AllStats):String = {
		val sb = new StringBuilder(2 << 12)
		sb.append(tableHeader)
		for((sigLibStats, bodLibStats) <-
		      sigStats.allLibStats zip bodStats.allLibStats)
		{
			sb.append(libTexRows(sigLibStats, bodLibStats))
			sb.append("\\hline \n")
		}
		sb.append(texTotalRows(sigStats.totalLibStats, bodStats.totalLibStats))
		sb.append("\\hline \n")
		sb.append(tableSuffix)
		sb.toString
	}	

	def libTexRows(sigStats:LibStats, bodStats:LibStats):String =
	{
		def shader(a:Any):String = "\\highlight{%s}" format a
	
		val sb = new StringBuilder(256)
		// class rows
		sb.append("""\multirow{6}{*}{%s} & classes & """.format(sigStats.name))
		sb.append(statTexRow(sigStats.clsStats)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodStats.clsStats, shader)).append(endTableRow)
		// interface rows
		sb.append(" & interfaces & ")
		sb.append(statTexRow(sigStats.intStats)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodStats.intStats, shader)).append(endTableRow)
		// totals rows
		sb.append(" & total & ")
		sb.append(statTexRow(sigStats.totalStats)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodStats.totalStats, shader)).append(endTableRow)
		sb.toString
	}

	def texTotalRows(clsTotalStats:LibStats, bodTotalStats:LibStats):String =
	{
		def texBold(a:Any):String = "\\textbf{%s}" format a.toString
		def shadeAndBold(a:Any):String =
			"""\highlight{\textbf{%s}}""" format a.toString
	
		val sb = new StringBuilder(700)
		// class rows
		sb.append("""\multirow{6}{*}{%s} & %s &""".format(
			texBold("Total"), texBold("classes")))
		sb.append(statTexRow(clsTotalStats.clsStats, texBold)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodTotalStats.clsStats, shadeAndBold)).append(endTableRow)
		// interface rows
		sb.append(" & %s & ".format(texBold("interfaces")))
		sb.append(statTexRow(clsTotalStats.intStats, texBold)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodTotalStats.intStats, shadeAndBold)).append(endTableRow)
		// total rows
		sb.append(" & %s & ".format(texBold("total")))
		sb.append(statTexRow(clsTotalStats.totalStats, texBold)).append(endTableRow)
		sb.append(" &    & ")
		sb.append(statTexRow(bodTotalStats.totalStats, shadeAndBold)).append(endTableRow)
		sb.toString
	}
}

object Table2
{
	def main(args:Array[String]):Unit = {
		if(args.length < 3) {
			Console.err.println(
				"usage: <out tex file> <sig json file> <bod json file>")
			sys.exit(1)
		}
		val outTexFileName = args(0)
		val sigStats = AllStats.fromJSONSFile(args(1))
		val bodStats = AllStats.fromJSONSFile(args(2))
		Utils.writeToFile(
			(new Table2).texTable(sigStats, bodStats),
			outTexFileName)
	}
}

class Table3 extends Table1
{
	override def tableHeader =
"""
% Table columns: (1) Library,
%                (2) (category: classes, interfaces, total)
%                (3) # of (parameterized) decls,
%                (4) # of rewritable decls,
%                (5) percentage of rewritable decls,
%                (6) # of variant decls,
%                (7) percentage of rewritable variant decls,
%                (8) Average size of flowsto set
\begin{tabular}{|ll|c|c|c|c|c|c|c|} \hline
Library & & \# Parameterized & \# Rewritable & Rewriteable & \# Variant & Rewritable   & Rewritable & Flowsto  \\
        & &    Decl Total    & P-Decl Total  & P-Decl \%   &  Decls     & V-Decl Total & V-Decl \%  & Avg. Size \\
\hline
"""

	override def statTexRow(vs:VarStats):String =
		Utils.joinstr(" & ",
			vs.totalPDecls,
			vs.totalRewritablePDecls,
			texPercent(vs.ratioRewritablePDecls),
			
			vs.totalVDecls,
			vs.totalRewritableVDecls,
			texPercent(vs.ratioRewritableVDecls),
			
			"%.2f".format(vs.averageFlowsToSize)
		)
	
	override def statTexRow(vs:VarStats, formatter:Any => String):String =
		Utils.joinstr(" & ",
			formatter(vs.totalPDecls),
			formatter(vs.totalRewritablePDecls),
			formatter(texPercent(vs.ratioRewritablePDecls)),
			
			formatter(vs.totalVDecls),
			formatter(vs.totalRewritableVDecls),
			formatter(texPercent(vs.ratioRewritableVDecls)),
			
			formatter("%.2f".format(vs.averageFlowsToSize))
		)
}

object Table3
{
	def texTable(allstats:AllStats) = (new Table3).texTable(allstats)
	

	def main(args:Array[String]):Unit = {
		if(args.length < 3) {
			Console.err.println(
				"usage: <out tex file> <sig json file> <bod json file>")
			sys.exit(1)
		}
		val outTexFileName = args(0)
		val sigStats = AllStats.fromJSONSFile(args(1))
		val bodStats = AllStats.fromJSONSFile(args(2))
		Utils.writeToFile(
			Table3.texTable(sigStats),
			outTexFileName)
		Utils.writeToFile(
			Table3.texTable(bodStats),
			outTexFileName)
	}
}


package ui
import scala.util.parsing.json.{JSONObject,JSONArray}

class Table1
{
	import Table1._ // Importing static members of Table1

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
		import Table1.texBold
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
		statTexRow(vs, Table1.any2String)
	
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

object Table1
{
	private val theInstance = new Table1
	
	def texTable(allstats:AllStats) = theInstance texTable allstats

	def any2String(a:Any):String = a.toString
	
	def decimal2String(d:Double):String = "%.2f" format d

	def texPercent(d:Double):String = {
		val s = java.text.NumberFormat.getPercentInstance.format(d)
		val slen = s.length
		if(slen < 2) "0\\%" else s.substring(0, slen-1) + "\\%"
	}
	
	def texBold(a:Any):String = "\\textbf{%s}" format a.toString
	
	def shader(a:Any):String = "\\highlight{%s}" format a
	
	def shadeAndBold(a:Any):String =
		"""\highlight{\textbf{%s}}""" format a.toString
}

class Table2
{
	import Table1._
 	private val table1Delegate = new Table1
  
  def tableHeader = table1Delegate.tableHeader
  def tableSuffix = table1Delegate.tableSuffix
  def endTableRow = table1Delegate.endTableRow
  def statTexRow(vs:VarStats) = table1Delegate statTexRow vs
  def statTexRow(vs:VarStats, formatter:Any => String) =
  	table1Delegate statTexRow (vs, formatter)

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
	private val theInstance = new Table2
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)

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
			texTable(sigStats, bodStats),
			outTexFileName)
	}
}

class Table3 extends Table1
{
	import Table1._ // Importing static members of Table1

	override def tableHeader =
"""
% Table columns: (1) Library
%                (2) (category: classes, interfaces, total)
%                (3) # of (parameterized) decls
%                (4) # of rewritable decls
%                (5) % of rewritable decls
%                (6) # of rewritten decls
%                (7) % of rewritten decls
%                (8) Average size of flowsto set
%                (9) Average size of flowsto set for rewritable decls

\begin{tabular}{|ll|c|c|c|c|c|c|c|c|} \hline
Library & & \# Parameterized & \# Rewritable & Rewriteable & Rewritten & Rewritten  & Flowsto   & Flowsto-R  \\
        & &    Decl Total    & P-Decl Total  & P-Decl \%   & Total     & Percentage & Avg. Size & Avg. Size \\
\hline
"""

	override def statTexRow(vs:VarStats):String =
		statTexRow(vs, Table1.any2String)


	override def statTexRow(vs:VarStats, formatter:Any => String):String =
		Utils.joinstr(" & ",
			formatter(vs.totalPDecls),
			
			formatter(vs.totalRewritablePDecls),
			formatter(texPercent(vs.ratioRewritablePDecls)),
			
			formatter(vs.totalRewritten),
			formatter(texPercent(vs.ratioRewritten)),
			
			formatter(decimal2String(vs.averageFlowsToSize)),
			formatter(decimal2String(vs.averageRewritableFlowsToSize))
		)
}

object Table3
{
	private val theInstance = new Table3
	
	def texTable(allstats:AllStats) = theInstance texTable allstats

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

class Table4 extends Table2
{
	private val table3Delegate = new Table3
	override def tableHeader = table3Delegate.tableHeader
	
	override def statTexRow(vs:VarStats) =
		table3Delegate statTexRow vs
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table3Delegate statTexRow (vs, formatter)
}


object Table4
{
	private val theInstance = new Table4
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)

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
			texTable(sigStats, bodStats),
			outTexFileName)
	}
}


class Table5 extends Table1
{
	import Table1._ // Importing static members of Table1

	override def tableHeader =
"""
% Table columns: (1) Library
%                (2) (category: classes, interfaces, total)
%                (3) # of variant decls,
%                (4) # rewritable variant decls,
%                (5) % rewritable variant decls,
%                (6) # rewritten variant decls,
%                (7) % rewritten variant decls

\begin{tabular}{|ll|c|c|c|c|c|} \hline
Library & & \# Variant & Rewritable   & Rewritable & Rewritten    & Rewritten  \\
        & & Decls      & V-Decl Total & V-Decl \%  & V-Decl Total & V-Decl \% \\
\hline
"""

	override def statTexRow(vs:VarStats):String =
		statTexRow(vs, Table1.any2String)

	override def statTexRow(vs:VarStats, formatter:Any => String):String =
		Utils.joinstr(" & ",
			formatter(vs.totalVDecls),
			formatter(vs.totalRewritableVDecls),
			formatter(texPercent(vs.ratioRewritableVDecls)),

			formatter(vs.totalRewrittenVDecls),
			formatter(texPercent(vs.ratioRewrittenVDecls))
		)
}

object Table5
{
	private val theInstance = new Table5
	
	def texTable(allstats:AllStats) = theInstance texTable allstats

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
			Table5.texTable(sigStats),
			outTexFileName)
		Utils.writeToFile(
			Table5.texTable(bodStats),
			outTexFileName)
	}
}


class Table6 extends Table2
{
	private val table5Delegate = new Table5
	override def tableHeader = table5Delegate.tableHeader
	
	override def statTexRow(vs:VarStats) =
		table5Delegate statTexRow vs
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table5Delegate statTexRow (vs, formatter)
}

object Table6
{
	private val theInstance = new Table6
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)

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
			texTable(sigStats, bodStats),
			outTexFileName)
	}
}


object TexTable
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
			Table2.texTable(sigStats, bodStats),
			outTexFileName)
		Utils.appendToFile(
			Table4.texTable(sigStats, bodStats),
			outTexFileName)
		Utils.appendToFile(
			Table6.texTable(sigStats, bodStats),
			outTexFileName)
	}
}

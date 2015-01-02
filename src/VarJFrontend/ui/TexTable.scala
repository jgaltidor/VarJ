package ui
import scala.util.parsing.json.{JSONObject,JSONArray}

trait TexTableGenerator
{
	def texTable(allstats:AllStats):String
}

abstract class TableTemplate extends TexTableGenerator
{
	def tableHeader:String

	def tableSuffix = TableTemplate.defaultTableSuffix

	def endTableRow = TableTemplate.defaultEndTableRow

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
		import TableTemplate.texBold
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
		statTexRow(vs, TableTemplate.any2String)

	def statTexRow(vs:VarStats, formatter:Any => String):String
}

object TableTemplate
{
	def defaultTableSuffix =
"""\end{tabular}
"""

	def defaultEndTableRow = " \\\\ \n"

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

class Table1 extends TableTemplate
{
  import TableTemplate._

	override def tableHeader =
"""\begin{tabular}{|ll|c|c|c|c|c|c|c|c|c|c|c|c|c|} \hline
Library & & \# Type     & \# Generic  & \multicolumn{5}{c|}{Type Definitions} & Recursive & Unnecess. & Over-specif.\\
        & & defs & defs & invar. & variant & cov. & contrav. & biv. & variances & wildcards   & methods  \\
\hline
"""

	override def statTexRow(vs:VarStats, formatter:Any => String) =
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

object Table1 extends TexTableGenerator
{
	private val theInstance = new Table1
	def texTable(allstats:AllStats) = theInstance texTable allstats
}

trait TexTableGenerator2
{
	def texTable(sigStats:AllStats, bodStats:AllStats):String
}

abstract class TableTemplate2 extends TexTableGenerator2
{
	import TableTemplate._

	def tableHeader:String

	def tableSuffix = defaultTableSuffix
	def endTableRow = defaultEndTableRow

	def statTexRow(vs:VarStats):String =
		statTexRow(vs, TableTemplate.any2String)

	def statTexRow(vs:VarStats, formatter:Any => String):String

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

class Table2 extends TableTemplate2
{
	import Table2.table1Delegate

	override def tableHeader = table1Delegate.tableHeader

	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table1Delegate.statTexRow(vs, formatter)
}

object Table2 extends TexTableGenerator2
{
	private val theInstance = new Table2

	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)

	private val table1Delegate = new Table1
}


class Table3 extends TableTemplate
{
	import TableTemplate._ // Importing static members of Table1

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

	override def statTexRow(vs:VarStats, formatter:Any => String) =
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

object Table3 extends TexTableGenerator
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

class Table4 extends TableTemplate2
{
	private val table3Delegate = new Table3
	override def tableHeader = table3Delegate.tableHeader
	
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table3Delegate statTexRow (vs, formatter)
}


object Table4 extends TexTableGenerator2
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


class Table5 extends TableTemplate
{
	import TableTemplate._ // Importing static members of Table1

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

	override def statTexRow(vs:VarStats, formatter:Any => String) =
		Utils.joinstr(" & ",
			formatter(vs.totalVDecls),
			formatter(vs.totalRewritableVDecls),
			formatter(texPercent(vs.ratioRewritableVDecls)),

			formatter(vs.totalRewrittenVDecls),
			formatter(texPercent(vs.ratioRewrittenVDecls))
		)
}

object Table5 extends TexTableGenerator
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


class Table6 extends TableTemplate2
{
	private val table5Delegate = new Table5
	override def tableHeader = table5Delegate.tableHeader
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table5Delegate statTexRow (vs, formatter)
}

object Table6 extends TexTableGenerator2
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

/** Type Parameter Inference Results */
class Table7 extends TableTemplate
{
	import TableTemplate._

	override def tableHeader =
"""
% Table columns: (1) Library
%                (2) (category: classes, interfaces, total)
%                (3) # of Type parameter
%                (4) % of invariant type parameters
%                (5) % of variant type parameters
%                (6) % of covariant type parameters
%                (7) % of contravariant type parameters
%                (8) % of bivariant type parameters
%                (9) % of recursive variance parameters

\begin{tabular}{|ll|c|c|c|c|c|c|c|c|c|c|c|c|} \hline
Library & & \# Type   & \multicolumn{5}{c|}{Type Parameters}     & Recursive \\
        & & Params    & invar. & variant & cov. & contrav. & biv. & variances\\
\hline
"""

	override def statTexRow(vs:VarStats, formatter:Any => String) =
		Utils.joinstr(" & ",
			formatter(vs.totalTypeParams),
			formatter(texPercent(vs.ratioInVarParams)),
			formatter(texPercent(vs.ratioVarParams)),
			formatter(texPercent(vs.ratioCoVarParams)),
			formatter(texPercent(vs.ratioContraVarParams)),
			formatter(texPercent(vs.ratioBiVarParams)),
			formatter(texPercent(vs.ratioRecVarParams))
		)
}

class Table8 extends TableTemplate2
{
	private val table7Delegate = new Table7
	override def tableHeader = table7Delegate.tableHeader
	
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table7Delegate statTexRow (vs, formatter)
}

object Table8 extends TexTableGenerator2
{
	private val theInstance = new Table8
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)
}

class Table9 extends TableTemplate
{
	import TableTemplate._

	override def tableHeader =
"""\begin{tabular}{|ll|c|c|c|c|c|c|c|c|c|c|} \hline
Library & & \#    & \#  & \multicolumn{5}{c|}{Type Definitions} \\
        & & Types & Generics & invar. & variant & cov. & contrav. & biv. \\
\hline
"""

	override def statTexRow(vs:VarStats, formatter:Any => String) =
		Utils.joinstr(" & ",
			formatter(vs.totalTypeDefs),
			formatter(vs.totalGenerics),
			formatter(texPercent(vs.ratioInVar)),
			formatter(texPercent(vs.ratioVar)),
			formatter(texPercent(vs.ratioCoVar)),
			formatter(texPercent(vs.ratioContraVar)),
			formatter(texPercent(vs.ratioBiVar))
		)
}

class Table10 extends TableTemplate2
{
	private val table9Delegate = new Table9
	override def tableHeader = table9Delegate.tableHeader
	
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table9Delegate statTexRow (vs, formatter)
}

object Table10 extends TexTableGenerator2
{
	private val theInstance = new Table10
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)
}

class Table11 extends TableTemplate
{
	import TableTemplate._

	override def tableHeader =
"""\begin{tabular}{|ll|c|c|c|c|c|c|c|c|} \hline
Library & & \#    & \#        & Unnecessary & Over-specified \\
        & & Types & Generics  & wildcards   & methods  \\
\hline
"""

	override def statTexRow(vs:VarStats, formatter:Any => String) =
		Utils.joinstr(" & ",
			formatter(vs.totalTypeDefs),
			formatter(vs.totalGenerics),
			formatter(texPercent(vs.ratioUselessWildCards)),
			formatter(texPercent(vs.ratioOverSpecified))
		)
}

class Table12 extends TableTemplate2
{
	private val table11Delegate = new Table11
	override def tableHeader = table11Delegate.tableHeader
	
	override def statTexRow(vs:VarStats, formatter:Any => String) =
		table11Delegate statTexRow (vs, formatter)
}

object Table12 extends TexTableGenerator2
{
	private val theInstance = new Table12
	
	def texTable(sigStats:AllStats, bodStats:AllStats) =
		theInstance texTable (sigStats, bodStats)
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
		
		val generators:List[TexTableGenerator2] =
			List(Table4,
			     Table6,
			     Table8,
			     Table10,
			     Table12)
		
		for(generator <- generators) {
			Utils.appendToFile(
				generator.texTable(sigStats, bodStats),
				outTexFileName)
		}
	}
}

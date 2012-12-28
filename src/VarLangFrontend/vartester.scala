package tame
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._
import com.beust.jcommander.Parameter


object Tester
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "tame.Tester")
		IterSeq.getSrcGenerics(vf.getProgram).foreach(processGeneric)
	}

	def processGeneric(gtd:AST.GenericTypeDecl):Unit = {
		for(index <- 0 until gtd.getNumTypeParameter) {
			val param = gtd getTypeParameter index
			val dvar = gtd getDVar param
			val bounds = AST.ASTNode.strategy.varBounds(gtd, param).toList
			println(dvar + ": " + dvar.eval())
			println("-"*32)
			println("bounds:")
			for((bound, j) <- bounds.zipWithIndex) {
				printf("  bound %d: %s%n", j+1, bound)
			}
			println("dvar.dvarBoundClosure: " + dvar.varBoundClosure)
			println("dvar.isRecursivelyBounded: " + dvar.isRecursivelyBounded)
		}
	}
}


object LookupVar extends FilesParams
{
	@Parameter(names = Array("-g", "--generic"), required = true,
						 description = "Name of generic to look up dvars")
	var genericName:String = null

	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "tame.LookupVar")
		IterSeq.getGeneric(vf.getProgram, genericName) match
		{
			case Some(gtd) => Tester.processGeneric(gtd)
			case None =>
				Console.err.println("Generic not found: " + genericName)
		}
	}
}


object AnalyzeType extends FilesParams
{
	@Parameter(names = Array("-t", "--type"), required = true,
	           description = "Name of class/interface to analyze")
	var typeName:String = null


	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "tame.AnalyzeType")
		IterSeq.getType(vf.getProgram, typeName) match
		{
			case Some(typeDecl) => analyzeTypeDec(typeDecl)
			case None =>
				Console.err.println("Class/Interface not found: " + typeName)
		}
	}

	def analyzeTypeDec(typeDecl:AST.TypeDecl):Unit = {
		println("type: " + typeDecl.fullName)
		typeDecl.logFieldFlowsTo
	}
}

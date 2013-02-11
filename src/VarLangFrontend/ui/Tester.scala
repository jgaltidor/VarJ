package ui
import AST.GenericTypeDecl
import AST.ASTNode
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._

object Tester
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "ui.Tester")
		IterSeq.getSrcGenerics(vf.getProgram).foreach(processGeneric)
	}

	def processGeneric(gtd:GenericTypeDecl):Unit = {
		for(index <- 0 until gtd.getNumTypeParameter) {
			val param = gtd getTypeParameter index
			val dvar = gtd getDVar param
			// val bounds = ASTNode.strategy.varBounds(gtd, param).toList
			val bounds = dvar.bounds.toList
			println(dvar + ": " + dvar.eval)
			println("-"*32)
			println("bounds:")
			for((bound, j) <- bounds.zipWithIndex) {
				printf("  bound %d: %s%n", j+1, bound)
			}
			println("dvar.dvarBoundClosure: " + dvar.varBoundClosure)
			println("dvar.isRecursivelyBounded: " + dvar.isRecursivelyBounded)
		}
		// process nested generic types too
		gtd.getNestedTypeDecls.filter(_.isGenericType).map(_.asInstanceOf[GenericTypeDecl]).foreach(processGeneric)
	}
}

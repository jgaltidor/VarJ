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
		for(dvar <- gtd.getDVars) {
			dvar.printValueAndBounds
			val uvars = dvar.uvarsInBounds
			if(!uvars.isEmpty) {
				println("bounds on uvars generated for " + dvar)
				uvars foreach { u =>
					u.printValueAndBounds
					println
				}
			}
			println("dvar.dvarBoundClosure: " + dvar.varBoundClosure)
			println("dvar.isRecursivelyBounded: " + dvar.isRecursivelyBounded)
		}
		// process nested generic types too
		gtd.getNestedTypeDecls.filter(_.isGenericType)
		                      .map(_.asInstanceOf[GenericTypeDecl])
		                      .foreach(processGeneric)
	}
}

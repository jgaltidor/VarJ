package ui
import AST.GenericTypeDecl
import AST.ASTNode
import AST.CompilationUnit
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._

object LookedUpMethods
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "ui.Tester")
		IterSeq.getSrcCompUnit(vf.getProgram).foreach(processCompilationUnit)
	}


	def processCompilationUnit(cunit:CompilationUnit):Unit = {
		println("printing looked up methods for: " + cunit.pathName)
		// cunit.printAccessedMeths
		cunit.printAccessedInstantiatedMeths
	}
}

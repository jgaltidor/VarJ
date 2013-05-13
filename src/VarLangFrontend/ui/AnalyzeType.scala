package ui
import com.beust.jcommander.Parameter
import AST.GenericTypeDecl
import AST.TypeDecl

object AnalyzeType extends FilesParams
{
	@Parameter(names = Array("-t", "--type"), required = true,
	           description = "Name of class/interface to analyze")
	var typeName:String = null


	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "ui.AnalyzeType")
		IterSeq.getType(vf.getProgram, typeName) match
		{
			case Some(typeDecl) => analyzeTypeDec(typeDecl)
			case None =>
				Console.err.println("Class/Interface not found: " + typeName)
		}
	}

	def analyzeTypeDec(typeDecl:TypeDecl):Unit = {
		if(typeDecl.isInstanceOf[GenericTypeDecl]) {
			Tester.processGeneric(typeDecl.asInstanceOf[GenericTypeDecl])
		}
		println("type: " + typeDecl.fullName)
		println("outputting assigned to analysis")
		typeDecl.logSuccessor
		println("outputting assigned to analysis")
		typeDecl.logFlowsTo
	}
}

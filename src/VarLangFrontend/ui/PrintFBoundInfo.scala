package ui

object PrintFBoundInfo
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "ui.PrintFBoundInfo")
		vf.getProgram.logFBoundInfo
	}
}

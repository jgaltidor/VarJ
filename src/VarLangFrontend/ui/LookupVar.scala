package ui
import com.beust.jcommander.Parameter

object LookupVar extends FilesParams
{
	@Parameter(names = Array("-g", "--generic"), required = true,
						 description = "Name of generic to look up dvars")
	var genericName:String = null

	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		BaseParams.processArgsAndCompile(args, vf, this, "ui.LookupVar")
		IterSeq.getGeneric(vf.getProgram, genericName) match
		{
			case Some(gtd) => Tester.processGeneric(gtd)
			case None =>
				Console.err.println("Generic not found: " + genericName)
		}
	}
}
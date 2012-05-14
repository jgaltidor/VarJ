package tame
// import implicit conversion for converting java.util collections
// import scala.collection.jcl.Conversions._
import scala.collection.JavaConversions._

object Tester
{
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		if(args.length == 0) {
			vf.printUsage
			sys.exit(1)
		}
		val newArgs = vf getNewArgsRecursively args
		VarFrontend.compile(vf, newArgs)
		IterSeq.getSrcGenerics(vf.getProgram).foreach(processGeneric)
	}
	
	def processGeneric(gtd:AST.GenericTypeDecl):Unit = {
		for(index <- 0 until gtd.getNumTypeParameter) {
			val param = gtd getTypeParameter index
			val dvar = gtd getDVar param
			val bounds = gtd.varBounds(param).toList
			println(dvar + ": " + dvar.eval())
			println("-"*32)
			println("bounds:")
			for((bound, j) <- bounds.zipWithIndex) {
				printf("  bound %d: %s%n", j+1, bound)
			}
			println("dvar.dvarBoundClosure: " + dvar.dvarBoundClosure)
			println("dvar.isRecursivelyBounded: " + dvar.isRecursivelyBounded)
			println
			println("Original version of: " + gtd.fullName)
			println
			println(gtd)
			println
			println("Rewritten version of: " + gtd.fullName)
			println
			println(gtd.toWild)
		}
	}
}

object LookupVar extends VarFrontend
{
	var genericName:String = null
	
	override def initOptions:Unit = {
		super.initOptions
		options.addKeyValueOption("-generic")
	}
	
	def preprocessArgs(args:Array[String]):Array[String] = {
		val newArgs = getNewArgsRecursively(args)
		genericName = getValueForRequiredOption("-generic")
		newArgs
	}

	override def printUsage:Unit = {
		super.printUsage
		println("LookupVar options:")
		println("  -generic" + (" "*17) + "Name of generic to look up dvars")
	}


	def main(args:Array[String]):Unit = {
		if(args.length == 0) {
			printUsage
			sys.exit(1)
		}
		val newArgs = preprocessArgs(args)
		VarFrontend.compile(this, newArgs)
		IterSeq.getGenerics(getProgram).find(
			gtd => gtd.fullName.equals(genericName)) match
		{
			case Some(gtd) => Tester.processGeneric(gtd)
			case None =>
				System.err.println("Generic not found: " + genericName)
		}
	}
}

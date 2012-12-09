package tame
// import implicit conversion for converting java.util collections
import scala.collection.JavaConversions._
import AST.ASTNode.strategy

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
			val bounds = strategy.varBounds(gtd, param).toList
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
		IterSeq.getGeneric(getProgram, genericName) match
		{
			case Some(gtd) => Tester.processGeneric(gtd)
			case None =>
				Console.err.println("Generic not found: " + genericName)
		}
	}
}


object AnalyzeType extends VarFrontend
{
	var typeName:String = null

	override def initOptions:Unit = {
		super.initOptions
		options.addKeyValueOption("-type")
	}
	
	def preprocessArgs(args:Array[String]):Array[String] = {
		val newArgs = getNewArgsRecursively(args)
		typeName = getValueForRequiredOption("-type")
		newArgs
	}

	override def printUsage:Unit = {
		super.printUsage
		println("AnalyzeType options:")
		println("  -type" + (" "*17) + "Name of class or interface to analyze")
	}
	
	def main(args:Array[String]):Unit = {
		if(args.length == 0) {
			printUsage
			sys.exit(1)
		}
		val newArgs = preprocessArgs(args)
		VarFrontend.compile(this, newArgs)
		IterSeq.getType(getProgram, typeName) match
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


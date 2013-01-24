package ui

import AST.GenericTypeDecl
import AST.TypeVariable
import AST.TypeDecl
import AST.Program
import AST.CompilationUnit
import AST.DVar
import java.io.{File,FileWriter,PrintWriter}

object Utils
{
	// To split a list into multiple arguments to pass to joinstr:
	// join(",", List(1,2,3):_*)
	def joinstr(sep:String, args:Any*):String = {
		if(args.isEmpty) ""
		else
			args.map(_.toString).reduceLeft((x,y) => x + sep + y)
	}
	
	def writeToFile(str:String, filepath:String):Unit =
		writeToFile(str, new File(filepath))
	
	def writeToFile(str:String, file:File):Unit = {
		val writer = new PrintWriter(new FileWriter(file, false))
		writer.println(str)
		writer.close
	}

	def appendToFile(str:String, filepath:String):Unit =
		appendToFile(str, new File(filepath))
	
	def appendToFile(str:String, file:File):Unit = {
		val writer = new PrintWriter(new FileWriter(file, true))
		writer.println(str)
		writer.close
	}

	def getDVars(gtd:GenericTypeDecl):Seq[DVar] =
		(0 until gtd.getNumTypeParameter).map {
			i => gtd.getDVar(gtd.getTypeParameter(i))
		}

	def numTypesCompiled(program:Program):Int = {
		var total = 0
		val itr = program.compilationUnitIterator
		while(itr.hasNext) {
			val cunit = itr.next.asInstanceOf[CompilationUnit]
			if(cunit.fromSource)
				total += cunit.getNumTypeDecl
		}
		total
	}
	
	def numGenericsCompiled(program:Program):Int = {
		var total = 0
		val itr = program.compilationUnitIterator
		while(itr.hasNext) {
			val cunit = itr.next.asInstanceOf[CompilationUnit]
			if(cunit.fromSource) {
				val numTypeDecls = cunit.getNumTypeDecl
				for(i <- 0 until numTypeDecls) {
					val td = cunit.getTypeDecl(i)
					if (td.isGenericType) {
						total += 1
					}
				}
			}
		}
		total
	}
}


package ui

import AST.GenericTypeDecl
import AST.TypeVariable
import AST.TypeDecl
import AST.Program
import AST.CompilationUnit
import AST.DVar
import java.io.{File,FileWriter,PrintWriter}

import scala.util.parsing.json.{JSON,
                                JSONObject,
                                JSONArray}

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


	def getTextFromFile(filename:String):String =
		getText(new java.io.File(filename))
	
	def getText(file:java.io.File):String = {
		val sb = new StringBuilder(256)
		for(line <- scala.io.Source.fromFile(file).getLines) {
			sb.append(line)
		}
		sb.toString
	}
	
	def toJSONObject(a:Any) =
		JSONObject(a.asInstanceOf[Map[String, Any]])

	def toJSONArray(a:Any) =
		JSONArray(a.asInstanceOf[List[Any]])


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


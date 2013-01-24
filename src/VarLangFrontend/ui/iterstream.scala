package ui

import AST.GenericTypeDecl
import AST.TypeVariable
import AST.TypeDecl
import AST.Program
import AST.CompilationUnit
import AST.DVar

import scala.collection.immutable.{SortedSet, TreeSet}
import scala.math.Ordering

object IterStream
{
	def getTypes(cunit:CompilationUnit):Stream[TypeDecl] = {
		val numTypeDecls = cunit.getNumTypeDecl
		
		def fromIndex(index:Int):Stream[TypeDecl] = {
			if(index < numTypeDecls)
				Stream.cons(cunit.getTypeDecl(index), fromIndex(index+1))
			else Stream.empty
		}
		fromIndex(0)
	}

	def getTypes(program:Program):Stream[TypeDecl] =
		getCompUnits(program).flatMap(getTypes)

	def getGenerics(program:Program):Stream[GenericTypeDecl] =
		getTypes(program).filter(_.isGenericType).map(_.asInstanceOf[GenericTypeDecl])

	def getCompUnits(program:Program):Stream[CompilationUnit] = {
		
		def fromItr(itr:java.util.Iterator[_]):Stream[CompilationUnit] = {
			if(itr.hasNext) {
				val cunit = itr.next.asInstanceOf[CompilationUnit]
				Stream.cons(cunit, fromItr(itr))
			}
			else
				Stream.empty
		}
		fromItr(program.compilationUnitIterator)
	}
	
	def getSrcCompUnits(program:Program):Stream[CompilationUnit] =
		getCompUnits(program).filter(_.fromSource)
	
	def getSrcTypes(program:Program):Stream[TypeDecl] =
		getSrcCompUnits(program).flatMap(getTypes)

	def getSrcGenerics(program:Program):Stream[GenericTypeDecl] =
		getSrcTypes(program).filter(_.isGenericType).map(_.asInstanceOf[GenericTypeDecl])

	def getGenTypesSortedByName(program:Program):SortedSet[GenericTypeDecl] =
	{
		val ordering = new Ordering[GenericTypeDecl] {
			def compare(x:GenericTypeDecl, y:GenericTypeDecl):Int =
				x.fullName compare y.fullName
		}
		TreeSet()(ordering) ++ getSrcGenerics(program)
	}

	def getGeneric(program:Program, name:String):Option[GenericTypeDecl] =
		getGenerics(program).find(gtd => gtd.fullName.equals(name))

	def getType(program:Program, name:String):Option[TypeDecl] =
		getTypes(program).find(td => td.fullName.equals(name))
}

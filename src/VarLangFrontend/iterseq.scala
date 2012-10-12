package tame

import AST.GenericTypeDecl
import AST.TypeVariable
import AST.TypeDecl
import AST.Program
import AST.CompilationUnit

import scala.collection.immutable.{SortedSet, TreeSet}
import scala.math.Ordering

object IterSeq
{
	def getTypes(cunit:CompilationUnit):Seq[TypeDecl] =
		(0 until cunit.getNumTypeDecl) map (cunit.getTypeDecl(_))

	def getTypes(program:Program):Seq[TypeDecl] =
		getCompUnits(program).flatMap(getTypes)

	def getGenerics(program:Program):Seq[GenericTypeDecl] =
		getTypes(program).filter(_.isGenericType).map(_.asInstanceOf[GenericTypeDecl])

	def getCompUnits(program:Program):Seq[CompilationUnit] =
		(0 until program.getNumCompilationUnit) map (program.getCompilationUnit(_))

	def getSrcCompUnit(program:Program):Seq[CompilationUnit] =
		getCompUnits(program).filter(_.fromSource)
	
	def getSrcTypes(program:Program):Seq[TypeDecl] =
		getSrcCompUnit(program).flatMap(getTypes)
	
	def getSrcGenerics(program:Program):Seq[GenericTypeDecl] =
		getSrcTypes(program).filter(_.isGenericType).map(_.asInstanceOf[GenericTypeDecl])

	def getSrcGenericsSortedByName(program:Program):SortedSet[GenericTypeDecl] =
	{
		val ordering = new Ordering[GenericTypeDecl] {
			def compare(x:GenericTypeDecl, y:GenericTypeDecl):Int =
				x.fullName compare y.fullName
		}
		TreeSet()(ordering) ++ getSrcGenerics(program)
	}

	def getGeneric(program:Program, name:String):Option[GenericTypeDecl] =
		getGenerics(program).find(gtd => gtd.fullName.equals(name))
}

package ui
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import AST.TypeDecl
import AST.GenericTypeDecl
// importing names COVARIANT, CONTRAVARIANT, ...
import AST.ASTNode._

object ComputeStats
{

	def computeStats(typeDecls:Seq[TypeDecl]):LibStats = {
		val libstats = new LibStats
		val startTime = System.currentTimeMillis
		for { typ <- typeDecls
		      if(typ.isClassDecl || typ.isInterfaceDecl)
		}
		{
			val vstats = 
				if(typ.isClassDecl) libstats.clsStats
				else libstats.intStats
			vstats.totalUselessWildcards += typ.uselessWildCardsInSig
			vstats.totalWildCardActuals  += typ.numWildCardActualsInSig
			vstats.totalOverSpecified    += typ.overSpecifiedActualsInSig
			vstats.totalArgActuals       += typ.numMethArgTypeActualsInSig
			
			vstats.totalPDecls            +=  typ.numPDeclsInSig
			vstats.totalRewritablePDecls  +=  typ.numRewritablePDeclsInSig
			vstats.totalRewritten         +=  typ.numRewrittenInSig
			vstats.totalFlowsTo           +=  typ.numFlowsToInSig
			vstats.totalRewritableFlowsTo +=  typ.numRewritableFlowsToInSig
			vstats.totalVDecls            +=  typ.numVDeclsInSig
			vstats.totalRewritableVDecls  +=  typ.numRewritableVDeclsInSig
			vstats.totalRewrittenVDecls   +=  typ.numRewrittenVDeclsInSig
			
			if(!typ.isGenericType) {
				vstats.totalMonoTypes += 1
			}
			else {
				val gtd = typ.asInstanceOf[GenericTypeDecl]
				var isInvariant = false
				var isCovariant = false
				var isContravariant = false
				var isBivariant = false
				var isRecVar = false
				val numParams = gtd.getNumTypeParameter
				
				for(index <- 0 until numParams) {
					val dvar = gtd getDVar index
					val variance = dvar.eval
					if(variance equals INVARIANT) {
						vstats.totalInVarParams += 1
						isInvariant = true
					}
					else if(variance equals COVARIANT) {
						vstats.totalCoVarParams += 1
						isCovariant = true
					}
					else if(variance equals CONTRAVARIANT) {
						vstats.totalContraVarParams += 1
						isContravariant = true
					}
					else if(variance equals BIVARIANT) {
						vstats.totalBiVarParams += 1
						isBivariant = true
					}
					
					if(dvar.isRecursivelyBounded) {
						isRecVar = true
						vstats.totalRecVarParams += 1 
					}
				}
				if(isInvariant)     vstats.totalInVar += 1
				if(isCovariant)     vstats.totalCoVar += 1
				if(isContravariant) vstats.totalContraVar += 1
				if(isBivariant)     vstats.totalBiVar += 1
				if(isRecVar)        vstats.totalRecVar += 1
			}
		}
		val endTime = System.currentTimeMillis
		// # of milliseconds to analyze library
		libstats.runningTime = endTime - startTime
		libstats
	}
	
	/*
	def main(args:Array[String]):Unit = {
		val vf = new VarFrontend
		val params = new FilesParams
		BaseParams.processArgsAndCompile(args, vf, params, "ui.ComputeStats")
		val typeDecls = IterSeq getSrcTypes vf.getProgram
		val libstats = computeStats(typeDecls)
		val allstats = new AllStats(List(libstats))
		print(Table1.texTable(allstats))
	}
	*/
}

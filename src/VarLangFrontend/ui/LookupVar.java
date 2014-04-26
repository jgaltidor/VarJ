package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import AST.Program;
import AST.ProgramQuery;
import AST.ProgramQueryImpl;
import AST.GenericTypeDecl;

public class LookupVar extends BaseCLParser
{
	@Parameter(names = {"-g", "--generic"}, required = true,
						 description = "Name of generic to look up dvars")
	String genericName;
	
	public static GenericTypeDecl
		lookupGeneric(Program program, String genericName)
	{
		ProgramQuery query = ProgramQueryImpl.getInstance();
		return query.getGeneric(program, genericName);
	}
	
	public static void printGenericInfo(Program program, String genericName)
	{
		GenericTypeDecl gtd = lookupGeneric(program, genericName);
		if(gtd != null) {
			AST.VarFrontend.printGenericInfo(gtd);
		}
		else {
			System.err.printf("No generic named %s found", genericName);
			System.err.println();
		}
	}
	
	public static void main(String[] args) {
  	LookupVar parser = new LookupVar();
  	JCommander jc = new JCommander(parser);
  	jc.setProgramName(LookupVar.class.getName());
  	Program program =
  		parser
  		  .setJCommander(jc)
  		  .parseArgs(args)
  		  .buildAnalysisSettings()
  		  .compile()
  		  .getProgram();
  	System.out.println("Looking up variance info for generic: " +
  		parser.genericName);
  	printGenericInfo(program, parser.genericName);
	}
}

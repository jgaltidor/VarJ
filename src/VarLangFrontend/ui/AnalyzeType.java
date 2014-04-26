package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import AST.*;

public class AnalyzeType extends FilesCLParser
{
	@Parameter(names = {"-t", "--type"}, required = true,
						 description = "Name of class/interface to analyze")
	java.util.List<String> typeNames = AST.ASTUtils.createList();
	
	@Override
	protected boolean optionsAreOK() {
		if(!super.optionsAreOK())
			return false;
		if(typeNames.isEmpty()) {
			System.err.println("No classes/interfaces to analyze are given");
			return false;
		}
		return true;
	}
	
	public static void analyzeTypeDec(TypeDecl typeDecl) {
		if(typeDecl.isGenericType()) {
			AST.VarFrontend.printGenericInfo((GenericTypeDecl) typeDecl);
		}
		System.out.println("type: " + typeDecl.fullName());
		System.out.println("outputting assigned to analysis");
		typeDecl.logSuccessor();
		System.out.println("outputting flows to analysis");
		typeDecl.logFlowsTo();
		System.out.println("outputting rewritability analysis");
		typeDecl.logRewritability();
	}

	public static TypeDecl lookupType(Program program, String typeName)
	{
		ProgramQuery query = AST.ASTUtils.getDefaultProgramQuery();
		return query.getType(program, typeName);
	}
	
	public static void printTypeInfo(Program program, String typeName)
	{
		TypeDecl typeDecl = lookupType(program, typeName);
		if(typeDecl != null) {
			analyzeTypeDec(typeDecl);
		}
		else {
			System.err.printf("No type named %s found", typeName);
			System.err.println();
		}
	}
	
	public static void main(String[] args) {
  	AnalyzeType parser = new AnalyzeType();
  	JCommander jc = new JCommander(parser);
  	jc.setProgramName(LookupVar.class.getName());
  	Program program =
  		parser
  		  .setJCommander(jc)
  		  .parseArgs(args)
  		  .buildAnalysisSettings()
  		  .compile()
  		  .getProgram();
  	for(String typeName : parser.typeNames)
  		printTypeInfo(program, typeName);
	}
}

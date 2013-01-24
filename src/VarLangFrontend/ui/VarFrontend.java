package ui;

import AST.Frontend;
import AST.Program;
import AST.CompilationUnit;
import AST.BytecodeParser;
import AST.JavaParser;


public class VarFrontend extends Frontend
{
	public Program getProgram() { return program; }
	
	// Making printUsage public
	public void printUsage() { super.printUsage(); }


	public static boolean compile(Frontend front, String args[]) {
		return front.process(args, new BytecodeParser(), new JavaParser()
		{
			public CompilationUnit parse(java.io.InputStream is, String fileName)
				throws java.io.IOException, beaver.Parser.Exception
			{
				return new parser.JavaParser().parse(is, fileName);
			}
		});
	}

	// For testing purposes
	public static void main(String args[]) {
  	VarFrontend vf = new VarFrontend();
  	BaseParams params = new FilesParams();
  	BaseParams.processArgsAndCompile(args, vf, params, "ui.VarFrontend");
	}
}

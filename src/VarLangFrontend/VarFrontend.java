package tame;

/*
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.ParameterException;
*/

import AST.Frontend;
import AST.Program;
import AST.CompilationUnit;
import AST.BytecodeParser;
import AST.JavaParser;

/*
import AST.Options;
import AST.ASTNode;
import AST.AnalysisStrategy;
*/

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


	/*	
	public static void main(String args[])
	{
  	VarFrontend vf = new VarFrontend();
  	JCommander jc = new JCommander(vf);
  	jc.setProgramName(vf.getClass().getName());
		try {
			jc.parse(args);
			if(vf.help) {
				jc.usage();
				vf.printUsage();
				System.exit(0);
			}
			else {
				String[] newArgs = vf.preprocessArgs(args);
				VarFrontend.compile(vf, newArgs);
			}
		}
		catch (ParameterException e)
		{
			System.err.println(e.getMessage());
			jc.usage();
			vf.printUsage();
			System.exit(1);
		}
	}
	*/
}

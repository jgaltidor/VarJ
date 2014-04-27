package ui;

public class PrintFBoundInfo
{
	public static void main(String[] args) {
		AST.Program program = FilesCLParser.typicalCompile(
		  args, PrintFBoundInfo.class.getName());
		program.logFBoundInfo();
	}
}

package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.List;

public class FilesCLParser extends BaseCLParser
{
	/** Main Parameter */
  @Parameter(description = "source files/directories")
  protected List<String> sourcePaths = AST.ASTUtils.createList();

  public List<String> getSourcePaths() { return sourcePaths; }
  
	@Override
	protected boolean optionsAreOK() {
		if(!super.optionsAreOK())
			return false;
		if(noSourcePathsSpecified()) {
			System.err.println("No source files/directories are given");
			return false;
		}
		return true;
	}

	@Override
	public FilesCLParser setJCommander(JCommander jcommander) {
		super.setJCommander(jcommander);
		return this;
	}
	
	@Override
	public FilesCLParser parseArgs(String[] args) {
		super.parseArgs(args);
		return this;
	}
  
  protected boolean noSourcePathsSpecified() {
  	return getSourcePaths().isEmpty();
  }
  
  public AST.AnalysisSettings buildAnalysisSettings() {
  	return buildAnalysisSettings(getSourcePaths());
  }
  
  public static void main(String[] args) {
  	FilesCLParser parser = new FilesCLParser();
  	JCommander jc = new JCommander(parser);
  	jc.setProgramName(FilesCLParser.class.getName());
  	AST.Program program =
  		parser
  		  .setJCommander(jc)
  		  .parseArgs(args)
  		  .buildAnalysisSettings()
  		  .compile()
  		  .getProgram();
  	AST.VarFrontend.printGenericsInfo(program);
  }
}
	
package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import AST.AnalysisSettings;
import AST.Program;
import AST.ProgramQuery;
import AST.ProgramQueryImpl;
import AST.GenericTypeDecl;

import java.util.List;
import java.util.Set;

/** Base class specifying shared command-line options parameters
  * used for frontends of the compiler/program analysis tool
  * Since there can be only one main parameter for each frontend,
  * this base class does not define a main parameter, as defined
  * in JCommander, which would be fixed for all subclasses.
  *
  */
public class BaseCLParser
{
	// Main Parameter
  @Parameter(description = "source files/directories")
  protected List<String> sourcePaths = AST.ASTUtils.createList();

	@Parameter(names = {"-h", "--help"}, help = true,
	           description = "Print this help message and exit")
	protected boolean help;

	@Parameter(names = {"-v", "--verbose"}, description = "Level of verbosity (1-3)")
  protected int verbosity = 1;

	@Parameter(names = "--nologcolor", description = "No colored output in log messages")
  protected boolean nologcolor = false;

	@Parameter(names = "--allprivate",
	           description = "Analyze all private instance members")
  protected boolean analyzeAllPrivate = false;

	@Parameter(names = "--bodies", description = "Analyze method bodies")
  protected boolean analyzeBodies = false;
  
  @Parameter(names = {"-j", "--jastaddj"}, description = "Argument to pass to JastAddJ")
  protected List<String> jastaddjArgs = AST.ASTUtils.createList();
  
  String programName;
  JCommander jcommander;
  
  public BaseCLParser() { this(BaseCLParser.class.getName()); }
  
  public BaseCLParser(String programName) {
  	this.programName = programName;
  	this.jcommander = createJCommander();
  }
  
  protected JCommander createJCommander() {
  	JCommander jc = new JCommander(this);
  	jc.setProgramName(programName);
  	return jc;
  }
  
  protected BaseCLParser parseArgs(String[] args)
  {
		try {
			jcommander.parse(args);
			if(shouldPrintUsage()) {
				printUsage();
				System.exit(0);
			}
		}
		catch(ParameterException e) {
			printUsage();
			System.exit(1);
		}
		return this;
  }
  
  protected boolean shouldPrintUsage() {
  	return helpOptionSpecified() || noSourcePathsSpecified();
  }
  
  protected boolean helpOptionSpecified() { return help; }
  
  protected boolean noSourcePathsSpecified() {
  	return sourcePaths.isEmpty();
  }
  
  protected void printUsage() {
  	jcommander.usage();
  	AST.VarFrontend.printJastAddUsage();
  }
  
  public AnalysisSettings buildAnalysisSettings()
  {
  	AnalysisSettings settings = null;
  	try {
  	  settings =
  	    new AnalysisSettings.Builder(sourcePaths)
		     .analyzeMethodBodies(analyzeBodies)
		     .analyzeAllPrivateMembers(analyzeAllPrivate)
		     .logVerbosityLevel(verbosity)
		     .colorLogMessages(!nologcolor)
		     .build();
		}
  	catch(AST.VarAnalysisException e) {
  		System.err.println(e.getMessage());
  		printUsage();
  		System.exit(1);
  	}
  	return settings;
  }
  
  public static void main(String[] args) {
  	Program program =
  		new BaseCLParser()
  		  .parseArgs(args)
  		  .buildAnalysisSettings()
  		  .compile()
  		  .getProgram();
		ProgramQuery query = ProgramQueryImpl.getInstance();
		Set<GenericTypeDecl> generics =
			query.getSrcGenericsSortedByName(program);
		for(GenericTypeDecl gtd : generics)
			query.printGenericInfo(gtd);
  }
}

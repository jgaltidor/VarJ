package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import AST.AnalysisSettings;

import java.util.List;

/** Base class specifying shared command-line options parameters
  * used for frontends of the compiler/program analysis tool
  * Since there can be only one main parameter for each frontend,
  * this base class does not define a main parameter, as defined
  * in JCommander, which would be fixed for all subclasses.
  *
  */
public class BaseCLParser
{
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

  private JCommander jcommander;
  
  public BaseCLParser setJCommander(JCommander jcommander) {
  	this.jcommander = jcommander;
  	return this;
  }
  
  public JCommander getJCommander() { return jcommander; }

  public BaseCLParser parseArgs(String[] args)
  {
		try {
			getJCommander().parse(args);
			if(helpOptionSpecified()) {
				printUsage();
				System.exit(0);
			}
			if(!optionsAreOK()) {
				printUsage();
				System.exit(1);
			}
		}
		catch(ParameterException e) {
			printUsage();
			System.exit(1);
		}
		return this;
  }
  
  protected boolean optionsAreOK() {
  	return true;
  }
  
  protected boolean helpOptionSpecified() { return help; }
  
  protected void printUsage() {
  	getJCommander().usage();
  	AST.VarFrontend.printJastAddUsage();
  }
  
  public AnalysisSettings buildAnalysisSettings(List<String> sourcePaths)
  {
  	AnalysisSettings settings = null;
  	try {
  	  settings =
  	    createSettingsBuilder(sourcePaths)
  	     .jastAddOptions(jastaddjArgs)
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
  
  /** This method enables setting more options in the
    * builder before actually building the Analysis settings
    */
  public AnalysisSettings.Builder
  	createSettingsBuilder(List<String> sourcePaths)
  {
  	return new AnalysisSettings.Builder(sourcePaths);
  }
}

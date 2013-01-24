package ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import AST.ASTNode;
import AST.AnalysisStrategy;

import java.util.List;
import java.util.LinkedList;

import logutil.LogUtil;

import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.StreamHandler;


/** Base class specifying shared command-line options parameters
  * used for frontends of the compiler/program analysis tool
  * Since there can be only one main parameter for each frontend,
  * this base class does not define a main parameter, as defined
  * in JCommander, which would be fixed for all subclasses.
  *
  */
public class BaseParams
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
  protected List<String> jastaddjArgs = new LinkedList<String>();


	public void preprocessArgs(String[] args) throws ParameterException {
		// process options from the command line
		// setup logging
		Level loglevel = LogUtil.getLogLevel(verbosity);
		if(loglevel == null) {
			throw new ParameterException(
				"No log level associated input verbosity level: " + verbosity);
		}
		// setup log formatter
		Formatter formatter = (!nologcolor && LogUtil.canHandleColor()) ?
			new logutil.ColorFormatter() :
			new logutil.NoColorFormatter();
		// Have logging printed to standard err
		Handler handler = new StreamHandler(System.err, formatter);
		// set global logger
		ASTNode.LOG = LogUtil.getGlobalLogger();
		// initialize logger using the settings above
		LogUtil.initLogger(ASTNode.LOG, handler);
		ASTNode.LOG.setLevel(loglevel);
		
		// Set analysis strategy
		AnalysisStrategy.Visibility visibility = analyzeAllPrivate ?
			AnalysisStrategy.Visibility.ALL_PRIVATE :
			AnalysisStrategy.Visibility.MINIMAL;
		
		ASTNode.strategy = analyzeBodies ?
			// new AST.MethBodyAnalysis(visibility) :
			null :
			new AST.OnlySigAnalysis(visibility);
	}
	
	/** Create new command line arguments array to pass to
		* JastAddJ Frontend compile method.
		* Should be called after calling preprocessArgs on args
		*/
	public List<String> createCompilerArgs() {
		List<String> newArgs = new LinkedList<String>();
		// Add specified JastAddJ arguments
		newArgs.addAll(jastaddjArgs);
		return newArgs;
	}


	/** Utility method for frontends (main methods).
	  * Its purpose is to reduce boilerplate code in main methods.
		* This method parses and preprocesses command line arguments.
		* It will print the help display, if the help options is specified.
		* It will exit if the command-line args can't be parsed.
		* This method should suffice for most frontends.
	  */
	public static void parseAndPreProcess(String[] args, VarFrontend vf,
		BaseParams params)
	{
		parseAndPreProcess(args, vf, params, "<main class>");
	}
	
	public static void parseAndPreProcess(String[] args, VarFrontend vf,
		BaseParams params, String programName)
	{
		JCommander jc = new JCommander(params);
		jc.setProgramName(programName);
		try {
			jc.parse(args);
			if(params.help) {
				jc.usage();
				vf.printUsage();
				System.exit(0);
			}
			else {
				params.preprocessArgs(args);
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

	// Method for reducing boilerplate code
	
	public static void processArgsAndCompile(String[] args, VarFrontend vf,
		BaseParams params)
	{
		processArgsAndCompile(args, vf, params, "<main class>");
	}
	
	public static void processArgsAndCompile(String[] args, VarFrontend vf,
		BaseParams params, String programName)
	{
		parseAndPreProcess(args, vf, params, programName);
		compile(vf, params);
	}

	public static void compile(VarFrontend vf, BaseParams params) {
		String[] compilerArgs =
			params.createCompilerArgs().toArray(new String[0]);
		VarFrontend.compile(vf, compilerArgs);
	}
}

package tame;
import AST.Program;
import AST.ASTNode;
import AST.CompilationUnit;
import AST.Frontend;
import AST.BytecodeParser;
import AST.JavaParser;
import AST.Options;
import AST.AnalysisStrategy;
import AST.OnlySigAnalysis;
import AST.MethBodyAnalysis;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collection;
import java.io.File;
import logutil.LogUtil;
import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.StreamHandler;

public class VarFrontend extends Frontend
{	
	public void printUsage() {
		super.printUsage();
		System.out.println("VarFrontend options:");
		System.out.println(
			"  -verbosity                Logging verbosity level (1-3). Default is 1");
		System.out.println(
    	"  -nologcolor               No colored output in log messages");
		System.out.println(
    	"  -allprivate               Analyze all private instance members");
		System.out.println(
    	"  -bodies                   Analyze method bodies");
    System.out.println();
	}

	public Program getProgram() { return program; }

	protected Options options() { return program.options(); }
	

	protected void initOptions() {
		super.initOptions();
		// Logging verbosity level (1-3). Default level is 1
		options().addKeyValueOption("-verbosity");
		options().addKeyOption("-nologcolor");
		options().addKeyOption("-allprivate");
		options().addKeyOption("-bodies");
	}

	
	/** This method should be called after calling initOptions()
	  * and adding any other options to options()
	  */
	protected void processArgs(String[] args) {
		// parse options on command line
		super.processArgs(args);
		
		// setup logging
		Level loglevel;
		if(options().hasOption("-verbosity")) {
			String vlevelstr = options().getValueForOption("-verbosity");
			int vlevel = Integer.parseInt(vlevelstr);
			loglevel = LogUtil.getLogLevel(vlevel);
		}
		else { // Assign default log level
			loglevel = LogUtil.ERROR;
		}
		// setup log formatter
		Formatter formatter;
		if(!options().hasOption("-nologcolor") && LogUtil.canHandleColor()) {
			formatter = new logutil.ColorFormatter();
		}
		else {
			formatter = new logutil.NoColorFormatter();
		}
		Handler handler = new StreamHandler(System.err, formatter);
		// set global logger
		ASTNode.LOG = LogUtil.getGlobalLogger();
		// initialize logger with appropriate settings
		LogUtil.initLogger(ASTNode.LOG, handler);
		ASTNode.LOG.setLevel(loglevel);
		
		// Set analysis strategy
		AnalysisStrategy.Visibility visibility =
			options().hasOption("-allprivate") ?
				AnalysisStrategy.Visibility.ALL_PRIVATE :
				AnalysisStrategy.Visibility.MINIMAL;
		
		ASTNode.strategy = options().hasOption("-bodies") ?
			new MethBodyAnalysis(visibility) :
			new OnlySigAnalysis(visibility);
	}


	/** Get value of specified option as String
	  * If the option value is not specified,
	  * then this method will terminate the program
	  * with an error code.
	  */
	public String getValueForRequiredOption(String optName) {
		if(options().hasOption(optName)) {
			return options().getValueForOption(optName);
		}
		else {
			System.err.println("Required option not specified: " + optName);
			System.exit(1);
		}
		throw new IllegalStateException();
	}


	/** Returns new command line arguments with all
	  * where given directory paths were transformed
	  * to a list of paths to the source files that
	  * were contained in the directory.
		* Unfortunately, the instance method, initOptions,
		* needs to be called before we use the JastAddJ
		* options parser to extract the files part,
		* which is performed with "options().addOptions(args);"
		* Hence, this preprocessing method cannot be static.
	  */
	public String[] getNewArgsRecursively(String[] args) {
		OptFilesPair optFiles = getOptFilesPair(args);
		LinkedList<String> newArgs = new LinkedList<String>();
		// Add original options to newArgs
		newArgs.addAll(optFiles.options);
		// Add new class path option
		newArgs.add("-classpath");
		newArgs.add(optFiles.getNewClassPath());
		newArgs.addAll(optFiles.getSrcFilesRecursively());
		return newArgs.toArray(new String[0]);
	}


	public static boolean compile(VarFrontend vf, String args[]) {
		return vf.process(args, new BytecodeParser(), new JavaParser()
		{
			public CompilationUnit parse(java.io.InputStream is, String fileName)
				throws java.io.IOException, beaver.Parser.Exception
			{
				return new parser.JavaParser().parse(is, fileName);
			}
		});
	}

	public OptFilesPair getOptFilesPair(String[] args) {
		initOptions(); // initialize options
		options().addOptions(args);
		return new OptFilesPair(options());
	}

	/** Represents a pair of option and roots,
	  * where the file(s) specified by each root path
	  * should be compiled separately.
	  *
	  */
	public static final class OptFilesPair
	{
		public final java.util.List<String> files = new LinkedList<String>();
		public final java.util.List<String> options = new LinkedList<String>();
		
		public OptFilesPair(Options opt) {
			this(opt.files(), opt.options());
		}
		
		public OptFilesPair(Collection<?> pathCollec, Map<?,?> optionMap) {
			for(Object path : pathCollec) {
				files.add(path.toString());
			}
			for(Map.Entry<?,?> entry : optionMap.entrySet()) {
				options.add(entry.getKey().toString());
				Object val = entry.getValue();
				if(val != null) {
					options.add(val.toString());
				}
			}
		}
		
		public java.util.List<String> getSrcFilesRecursively() {
			LinkedList<String> filepaths = new LinkedList<String>();
			// Add source files to newArgs
			for(String rootPath : files)
				filepaths.addAll(collectSourceFiles(rootPath));
			return filepaths;
		}
		
		public String getNewClassPath() {
			StringBuilder sb = new StringBuilder(128);
			for(String rootPath : files) {
				File file = new File(rootPath);
				if(file.isDirectory()) {
					sb.append(rootPath).append(':');
				}
			}
			sb.append("$CLASSPATH");
			return sb.toString();
		}
	}
	
	public static java.util.List<String> collectSourceFiles(String path) {
		LinkedList<String> srcfiles = new LinkedList<String>();
		getFiles(path, srcfiles);
		return srcfiles;
	}
	
	
	public static java.util.List<String> collectSourceFiles(Collection<?> paths) {
		LinkedList<String> srcfiles = new LinkedList<String>();
		collectSourceFiles(paths, srcfiles);
		return srcfiles;
	}
	
	
	public static void collectSourceFiles(Collection<?> paths,
		java.util.List<String> srcfiles)
	{
		for(Object path : paths)
			getFiles(path.toString(), srcfiles);
	}
	
	private static void getFiles(String path, java.util.List<String> srcfiles) {
		File f = new File(path);
		if(f.isFile() && path.endsWith(".java"))
			srcfiles.add(path);
		else if(f.isDirectory()) {
			for(File child : f.listFiles())
				getFiles(child.getPath(), srcfiles);
		}
	}
	
	public static void main(String args[]) {
		VarFrontend vf = new VarFrontend();
		if(args.length == 0) {
			vf.printUsage();
			System.exit(1);
		}
		String[] newArgs = vf.getNewArgsRecursively(args);
		VarFrontend.compile(vf, newArgs);
	}
}

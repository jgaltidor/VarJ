package backend.logging;
import java.util.logging.*;
import java.io.OutputStream;
import backend.logging.format.RenameLevelFormatter;

public class VarLogger
{
	public final static Level DEBUG = new DebugLevel();
	public final static Level INFO = Level.INFO;
	public final static Level ERROR = new ErrorLevel();

	public static Level getLogLevel(int vlevel) {
		return verbose2level.get(vlevel);
	}

	private static final java.util.Map<Integer, Level> verbose2level
		= new java.util.HashMap<Integer, Level>();
	
	static {
		verbose2level.put(3, DEBUG);
		verbose2level.put(2, INFO);
		verbose2level.put(1, ERROR);
		verbose2level.put(0, Level.OFF);
	}

	private final Logger logger;

	private VarLogger(HandlerBuilder builder) {
		this.logger = builder.logger;
		initLogger(logger, builder.handler, builder.level);
	}
	
	private static void initLogger(Logger logger, Handler handler,
	                               Level level)
	{
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		logger.setLevel(level);
		handler.setLevel(Level.ALL);
	}

	public Logger getLogger() { return logger; }
	
	public static Level getDefaultLogLevel() {
		return INFO;
	}
	private static Logger getDefaultLogger() {
		return getGlobalLogger();
	}
	private static Logger getGlobalLogger() {
		return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}

	public static class HandlerBuilder
	{
		private final Handler handler;
		
		private Logger logger = getDefaultLogger();
		private Level level = getDefaultLogLevel();
		
		public HandlerBuilder(Handler handler) {
			this.handler = handler;
		}
		public HandlerBuilder level(Level level) {
			this.level = level;
			return this;
		}
		public HandlerBuilder logger(Logger logger) {
			this.logger = logger;
			return this;
		}
		public VarLogger build() {
			return new VarLogger(this);
		}
	}	
	
	public static class FormatterBuilder
	{
		// Optional args
		private Formatter formatter = new SimpleFormatter();
		private OutputStream out = System.err;
		private Level level = getDefaultLogLevel();
		
		public FormatterBuilder formatter(Formatter formatter) {
			this.formatter = formatter;
			return this;
		}
		public FormatterBuilder formatter(OutputStream out) {
			this.out = out;
			return this;
		}
		public FormatterBuilder level(Level level) {
			this.level = level;
			return this;
		}
		public VarLogger build() {
			Handler handler = new StreamHandler(out, formatter);
			return new HandlerBuilder(handler)
				.level(level)
				.build();
		}
	}
	
	// Custom Log Levels
	private static class DebugLevel extends Level {
		DebugLevel() {
			super("DEBUG", Level.CONFIG.intValue());
		}
	}

	private static class ErrorLevel extends Level {
		ErrorLevel() {
			super("ERROR", Level.WARNING.intValue());
		}
	}

	// Utility methods

	/** Determine if current output console would print out
	  * colored text for ANSI color codes
	  */
	public static boolean canHandleColor() {
		if(System.console() == null)
			return false;
		String osname = System.getProperty("os.name");
		if(osname != null) {
			osname = osname.toLowerCase();
			if(osname.startsWith("windows"))
				return false;
		}
		return System.getenv().containsKey("TERM");
	}
	
	public static final String linesep = System.getProperty("line.separator");
	
	/** Main method for testing purposes */
	public static void main(String[] args)
	{
		if (args.length < 1) {
			System.err.println("usage: java backend.logging.VarLogger <verbosity level>");
			System.exit(1);
		}
		int vlevel = Integer.parseInt(args[0]);
		Level level = VarLogger.getLogLevel(vlevel);
		Formatter formatter = VarLogger.canHandleColor() ?
			new RenameLevelFormatter(
				RenameLevelFormatter.ColorNameStrategy.getInstance())
			: new SimpleFormatter();
		Logger LOG = new VarLogger.FormatterBuilder()
		                 .formatter(formatter)
		                 .level(level)
		                 .build()
		                 .getLogger();
    System.out.println("Log level: " + LOG.getLevel());
		LOG.log(ERROR, "A error message;");
		LOG.log(INFO,  "An info message;");
		LOG.log(DEBUG, "A debug message;");
	}
	
	
	/*
	
	public static void initLogger(Logger logger) {
		initLogger(logger, new SimpleFormatter());
	}

	public static void initLogger(Logger logger, Formatter formatter) {
		initLogger(logger, new StreamHandler(System.err, formatter));
	}
	
	public static void setVerbosity(Logger logger, int vlevel) {
		logger.setLevel(verbose2level.get(vlevel));
	}

	private static final int maxLevel = 3;
	*/
}

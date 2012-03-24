package logutil;
import java.util.logging.*;
import java.util.HashMap;

public final class LogUtil
{
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
	
	public final static Level DEBUG = new DebugLevel();
	public final static Level INFO = Level.INFO;
	public final static Level ERROR = new ErrorLevel();

	private static final HashMap<Integer, Level> verbose2level
		= new HashMap<Integer, Level>();
	
	static {
		verbose2level.put(3, DEBUG);
		verbose2level.put(2, INFO);
		verbose2level.put(1, ERROR);
		verbose2level.put(0, Level.OFF);
	}

	private static final int maxLevel = 3;
	
	public static final String linesep = System.getProperty("line.separator");
	
	public static Logger getGlobalLogger() {
		return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}
	
	public static void initLogger(Logger logger) {
		initLogger(logger, new SimpleFormatter());
	}

	public static void initLogger(Logger logger, Formatter formatter) {
		initLogger(logger, new StreamHandler(System.err, formatter));
	}
	
	public static void initLogger(Logger logger, Handler handler) {
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	
	public static Level getLogLevel(int vlevel) {
		return verbose2level.get(vlevel);
	}
	
	public static void setVerbosity(Logger logger, int vlevel) {
		logger.setLevel(verbose2level.get(vlevel));
	}
	

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
}

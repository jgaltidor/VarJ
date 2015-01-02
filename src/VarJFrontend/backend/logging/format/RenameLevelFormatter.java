package backend.logging.format;
import java.util.logging.*;
import static backend.logging.VarLogger.*;

/**
 * This formatter allows printing out logs with the predefined
 * level names *renamed to custom level names
 * The levels in descending order are: 
 *   SEVERE (highest value)
 *   WARNING
 *   INFO
 *   CONFIG
 *   FINE
 *   FINER
 *   FINEST (lowest value) 
*/
public class RenameLevelFormatter extends SimpleFormatter
{
	private final LevelNameStrategy lnstrategy;
	
	public RenameLevelFormatter() {
		this(IdentityNameStrategy.getInstance());
	}
	
	public RenameLevelFormatter(LevelNameStrategy lnstrategy) {
		this.lnstrategy = lnstrategy;
	}

	// This method is called for every log records
	public String format(LogRecord rec)
	{
		StringBuilder sb = new StringBuilder(64)
			.append('[')
			.append(rec.getSourceClassName())
			.append('.')
			.append(rec.getSourceMethodName())
			.append(' ')
			.append(lnstrategy.toLevelName(rec.getLevel()))
			.append("]: ")
			.append(rec.getMessage())
			.append(linesep);
		return sb.toString();
	}
	
	public static interface LevelNameStrategy
	{
		public String toLevelName(Level level);
	}
	
	public static class IdentityNameStrategy implements LevelNameStrategy
	{
		public static IdentityNameStrategy getInstance() { return INSTANCE; }
		private static IdentityNameStrategy INSTANCE = new IdentityNameStrategy();
		private IdentityNameStrategy() { }
	
		public String toLevelName(Level level) { return level.getName(); }
	}
	
	public static class ColorNameStrategy implements LevelNameStrategy
	{
		public static ColorNameStrategy getInstance() { return INSTANCE; }
		private static ColorNameStrategy INSTANCE = new ColorNameStrategy();
		private ColorNameStrategy() { }
		
		public String toLevelName(Level level) {
			String name = lev2name.get(level);
			return (name != null) ? name : level.getName();
		}
		
		// ANSI Color codes
		static final String BLUE = "\033[34m";
		static final String GREEN = "\033[32m";
		static final String RED = "\033[31m";
		// static final String MAGNETA = "\033[35m";
		// static final String YELLOW = "\033[33m";
		static final String RESET = "\033[0m";
		static final String BOLD = "\033[1m";
		
		static final java.util.Map<Level,String> lev2name =
			new java.util.HashMap<Level,String>();
		
		static {
			lev2name.put(DEBUG, colorBoldStr(BLUE, "DEBUG"));
			lev2name.put(INFO,  colorBoldStr(GREEN, "INFO"));
			lev2name.put(ERROR, colorBoldStr(RED, "ERROR"));
		}

		static String colorBoldStr(String color, String str) {
			return BOLD + color + str + RESET;
		}
	}
}

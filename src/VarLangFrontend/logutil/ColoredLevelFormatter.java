package logutil;
import java.util.logging.*;
import java.util.HashMap;
import static logutil.LogUtil.*;

/** Custom Log Formatter based on log levels of LogUtil class
	*/
public class ColoredLevelFormatter extends RenameLevelFormatter
{
	static final HashMap<Level,String> lev2name =
		new HashMap<Level,String>();

	// ANSI Color codes
	static final String BLUE = "\033[34m";
	static final String GREEN = "\033[32m";
	static final String RED = "\033[31m";
	// static final String MAGNETA = "\033[35m";
	// static final String YELLOW = "\033[33m";
	static final String RESET = "\033[0m";
	static final String BOLD = "\033[1m";
	
	static {
		lev2name.put(DEBUG, colorBoldStr(BLUE, "DEBUG"));
		lev2name.put(INFO,  colorBoldStr(GREEN, "INFO"));
		lev2name.put(ERROR, colorBoldStr(RED, "ERROR"));
	}

	static String colorBoldStr(String color, String str) {
		return BOLD + color + str + RESET;
	}


	public String getLevelNameStr(Level level) {
		return lev2name.get(level);
	}
}

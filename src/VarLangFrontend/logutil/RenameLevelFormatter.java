package logutil;
import java.util.logging.*;

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
	// This method is called for every log records
	public String format(LogRecord rec)
	{
		StringBuilder sb = new StringBuilder(64)
			.append('[')
			.append(rec.getSourceClassName())
			.append('.')
			.append(rec.getSourceMethodName())
			.append(' ')
			.append(getLevelNameStr(rec.getLevel()))
			.append("]: ")
			.append(rec.getMessage())
			.append(LogUtil.linesep);
		return sb.toString();
	}
	
	public String getLevelNameStr(Level level) {
		return level.getName();
	}
}

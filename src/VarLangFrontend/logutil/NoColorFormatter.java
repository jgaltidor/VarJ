package logutil;
import java.util.logging.*;
import java.util.HashMap;
import static logutil.LogUtil.*;

/** Custom Log Formatter based on log levels of LogUtil class
	*/
public class NoColorFormatter extends SimpleFormatter
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
			.append(rec.getLevel().getName())
			.append("]: ")
			.append(rec.getMessage())
			.append(LogUtil.linesep);
		return sb.toString();
	}
	
}

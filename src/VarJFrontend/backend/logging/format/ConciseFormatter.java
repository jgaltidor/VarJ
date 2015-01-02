package backend.logging.format;
import java.util.logging.*;
import backend.logging.VarLogger;

public class ConciseFormatter extends SimpleFormatter
{
	// This method is called for every log records
	public String format(LogRecord rec)
	{
		StringBuilder sb = new StringBuilder(64)
			.append('[')
			.append(rec.getLevel().getName())
			.append("]: ")
			.append(rec.getMessage())
			.append(VarLogger.linesep);
		return sb.toString();
	}
}

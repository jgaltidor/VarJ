package logutil;
import java.util.logging.*;

public class ConciseFormatter extends SimpleFormatter
{
	private static final String linesep = System.getProperty("line.separator");

	// This method is called for every log records
	public String format(LogRecord rec)
	{
		StringBuilder sb = new StringBuilder(64)
			.append('[')
			.append(rec.getLevel().getName())
			.append("]: ")
			.append(rec.getMessage())
			.append(linesep);
		return sb.toString();
	}
}
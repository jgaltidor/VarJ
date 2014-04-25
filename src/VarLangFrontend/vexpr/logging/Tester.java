package vexpr.logging;
import java.util.logging.Logger;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import static vexpr.logging.VarLogger.ERROR;
import static vexpr.logging.VarLogger.INFO;
import static vexpr.logging.VarLogger.DEBUG;
import vexpr.logging.format.RenameLevelFormatter;

public class Tester
{

	public static void main(String[] args)
	{
		if (args.length < 1) {
			System.err.println("usage: java vexpr.logging.Tester <verbosity level>");
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
}

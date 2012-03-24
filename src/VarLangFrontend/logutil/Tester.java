package logutil;
import java.util.logging.Logger;
import static logutil.LogUtil.*;

public class Tester
{

	public static void main(String[] args)
	{
		if (args.length < 1) {
			System.err.println("usage: java TestLogger <verbosity level>");
			System.exit(1);
		}
		int vlevel = Integer.parseInt(args[0]);
		// LogUtil log = LogUtil.getDefaultLogUtil();
		Logger LOG = LogUtil.getGlobalLogger();
		LogUtil.initLogger(LOG, new ColoredLevelFormatter());
		LogUtil.setVerbosity(LOG, vlevel);
		LOG.log(ERROR, "A error message;");
		LOG.log(INFO, "An info message;");
		LOG.log(DEBUG, "A debug message;");
	}
}

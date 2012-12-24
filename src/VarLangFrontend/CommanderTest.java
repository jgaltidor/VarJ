import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class CommanderTest
{
	@Parameter(names = {"-h", "--help"}, help = true, description = "Print this help message and exit")
  private boolean help;
  
  public static void main(String[] args)
  {
  	CommanderTest ct = new CommanderTest();
  	JCommander jc = new JCommander(ct);
  	jc.setProgramName(ct.getClass().getName());
		try {
			jc.parse(args);
			if(ct.help) {
				jc.usage();
				System.exit(0);
			}
			else {
				System.out.println("help not specified");
			}
		}
		catch (ParameterException e)
		{
			System.err.println(e.getMessage());
			jc.usage();
			System.exit(1);
		}
  }
}

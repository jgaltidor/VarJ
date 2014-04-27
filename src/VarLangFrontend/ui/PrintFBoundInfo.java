package ui;
import com.beust.jcommander.JCommander;

public class PrintFBoundInfo
{
	public static void main(String[] args) {
  	FilesCLParser parser = new FilesCLParser();
  	JCommander jc = new JCommander(parser);
  	jc.setProgramName(PrintFBoundInfo.class.getName());
  	parser
	    .setJCommander(jc)
  	  .parseArgs(args)
  	  .buildAnalysisSettings()
  	  .compile()
  	  .getProgram()
  	  .logFBoundInfo();
	}
}

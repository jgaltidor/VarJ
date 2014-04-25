package vexpr;
import java.util.*;
import java.io.OutputStream;

public class AnalysisSettings
{
	private final List<String> sourceFiles;
	private final List<String> javacOptions;
	private final boolean analyzeMethodBodies;
	
	private final int logVerbosityLevel;
	private final PrintStream logOutputStream;
	private final boolean colorLogMessages;

	private AnalysisSettings(Builder b) {
		this.sourceFiles = b.sourceFiles;
		this.javacOptions = b.javacOptions;
		this.analyzeMethodBodies = b.analyzeMethodBodies;
		this.logVerbosityLevel = b.logVerbosityLevel;
		this.logOutputStream = b.logOutputStream;
		this.colorLogMessages = b.colorLogMessages;
	}


	public static class Builder
	{
		// Require parameters
		private final List<String> sourceFiles;
	
		// Optional parameters
		private final List<String> javacOptions = new Vector<String>();
		private boolean analyzeMethodBodies = false;
		private boolean analyzeAllPrivateMembers = false;
		
		private int logVerbosityLevel = 0;
		private PrintStream logOutputStream = System.out;
		private boolean colorLogMessages = false;
		
		public Builder(List<String> sourceFiles) {
			this.sourceFiles = sourceFiles;
		}
		
		public Builder analyzeMethodBodies(boolean analyzeMethodBodies) {
			this.analyzeMethodBodies = analyzeMethodBodies;
			return this;
		}
		public Builder analyzeAllPrivateMembers(boolean analyzeAllPrivateMembers) {
			this.analyzeAllPrivateMembers = analyzeAllPrivateMembers;
			return this;
		}
		public Builder logVerbosityLevel(int logVerbosityLevel) {
			this.logVerbosityLevel = logVerbosityLevel;
			return this;
		}
		public Builder logOutputStream(PrintStream logOutputStream) {
			this.logOutputStream = logOutputStream;
			return this;
		}
		public Builder colorLogMessages(boolean colorLogMessages) {
			this.colorLogMessages = colorLogMessages;
			return this;
		}
	}
}

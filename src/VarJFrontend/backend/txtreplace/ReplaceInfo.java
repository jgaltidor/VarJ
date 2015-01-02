package backend.txtreplace;

public class ReplaceInfo
{
	final String filename;
	final int startLine;
	final int startCol;
	final int endLine;
	final int endCol;
	final String oldText;
	final String newText;

	public ReplaceInfo(
		String filename,
		int startLine,
		int startCol,
		int endLine,
		int endCol,
		String oldText,
		String newText
		)
	{
		this.filename  = filename;
		this.startLine = startLine;
		this.startCol = startCol;
		this.endLine  = endLine;
		this.endCol   = endCol;
		this.oldText  = oldText;
		this.newText  = newText;
	}
	
	public String toString() {
		return String.format("%s|%d|%d|%d|%d|%s|%s",
			filename,
			startLine,
			startCol,
			endLine,
			endCol,
			oldText,
			newText);
	}
}

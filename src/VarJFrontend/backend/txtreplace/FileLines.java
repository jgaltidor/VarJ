package backend.txtreplace;
import java.util.List;
import java.util.Iterator;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class FileLines
{
	private List<String> lines;
	private final File infile;
	
	public FileLines(String filename) throws IOException {
		this(new File(filename));
	}
	
	public FileLines(File file) throws IOException {
		lines = readLines(file);
		infile = file;
	}

	public void replace(ReplaceInfo info) {
		replace(info.startLine, info.startCol, info.endLine,
			info.endCol, info.newText, info.oldText);
	}
	
	public void replace(int startLine, int startCol,
		int endLine, int endCol, String newText, String oldText)
	{
		if(isFakeReplacement(startLine))
			return;
		boolean isSingleLineReplacement =
			(startLine == endLine) && !containsLineTerminator(newText);
		if(isSingleLineReplacement)
			singlelineReplace(startLine, startCol, endCol, newText, oldText);
		else
			multilineReplace(startLine, startCol, endLine, endCol, newText);
	}
	
	public static boolean isFakeReplacement(int startLine) {
		return startLine < 0;
	}
	
	public void multilineReplace(int startLine, int startCol,
		int endLine, int endCol, String newText)
	{
		// remember that indicies in lines List are shifted one less
		// than corresponding line numbers
		// e.g., lines.get(startLine-1) retrieves text at line number startLine
		// Similarly, for column numbers
		List<String> prefix =
			copyList(lines.subList(0, startLine-1));
		String startLinePrefix =
			lines.get(startLine-1).substring(0, startCol-1);
		prefix.add(startLinePrefix);
	
		List<String> suffix = createList();
		String endLineSuffix =
			lines.get(endLine-1).substring(endCol);
		suffix.add(endLineSuffix);
		suffix.addAll(lines.subList(endLine, lines.size()));
		
		// newLines from newText
		List<String> newLines = splitIntoLines(newText);
		
		// construct resulting lines
		List<String> result = combineLines(prefix, newLines);
		result = combineLines(result, suffix);
		// update lines field to result
		lines = result;
	}

	public static final Pattern newLinePat = Pattern.compile("[\\n\\r]+");

	public static List<String> splitIntoLines(String str) {
		List<String> newLines = createList();
		Matcher m = newLinePat.matcher(str);
		int index = 0;
		while(m.find()) {
			int lineStart = index;
			int lineEnd = m.start();
			String currentLine = str.substring(lineStart, lineEnd);
			newLines.add(currentLine);
			index = m.end();
		}
		// Add text after last line terminator
		if(index < str.length()) {
			newLines.add(str.substring(index, str.length()));
		}
		return newLines;
	}

	public static List<String> combineLines(List<String> prefix, List<String> suffix)
	{
		if(prefix.isEmpty())
			return copyList(suffix);
		if(suffix.isEmpty())
			return copyList(prefix);
		// else both prefix and suffix are non-empty
		int prefixSize = prefix.size();
		// constructing resulting List
		List<String> result =
			copyList(prefix.subList(0, prefixSize-1));
		// last line in prefix is prepended to first in suffix
		Iterator<String> suffixItr = suffix.iterator();
		String middleLine = prefix.get(prefixSize-1) + suffixItr.next();
		result.add(middleLine);
		// add remaining lines in suffix
		while(suffixItr.hasNext()) {
			result.add(suffixItr.next());
		}
		return result;
	}
	
	
	public void singlelineReplace(int linenum, int startCol, int endCol, String newText, String oldText)
	{
		// remember that indicies in lines List are shifted one less
		// than corresponding line numbers
		// e.g., lines.get(startLine-1) retrieves text at line number startLine
		// Similarly, for column numbers
		
		String line = lines.get(linenum-1);
		
		// Error checking
		// First have to handle very rare case of when JastAdd reports the wrong line number
		int lineLen = line.length();
		if(startCol < 0 || startCol > lineLen) {
			System.err.printf("ERROR: startCol %d not within line length: %d%n", startCol, lineLen);
			System.err.println("line from source: " + line);
			return;
		}
		if(endCol < 0 || endCol > lineLen) {
			System.err.printf("ERROR: endCol %d not within line length: %d%n", endCol, lineLen);
			System.err.println("line from source: " + line);
			return;
		}
		/*
		String txtInRegion = line.substring(startCol-1, endCol);
		if(!txtInRegion.equals(oldText)) {
			System.err.println("WARNING: Text in region does not match old text");
			System.err.println("Text in region: " + txtInRegion);
			System.err.println("old text: " + oldText);
		}
		*/
		// done with error checking

		String prefix = line.substring(0, startCol-1);
		String suffix = line.substring(endCol, line.length());
		String newline = prefix + newText + suffix;
		lines.set(linenum-1, newline);
	}

	public void writeFile(File outfile) throws IOException {
		PrintStream out = new PrintStream(outfile);
		for(String line : lines) {
			out.println(line);
		}
		out.close();
	}


	public static boolean containsLineTerminator(String s) {
		return newLinePat.matcher(s).find();
	}
	
	public static List<String> readLines(File infile) throws IOException {
		List<String> fileLines = createList();
		BufferedReader br = new BufferedReader(new FileReader(infile));
		String line;
		while((line = br.readLine()) != null)
			fileLines.add(line);
		br.close();
		return fileLines;
	}

	public static <E> List<E> copyList(List<E> list) {
		List<E> newList = createList();
		newList.addAll(list);
		return newList;
	}
	
	public static <E> List<E> createList() {
		return new java.util.LinkedList<E>();
	}
}

package backend.txtreplace;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;


public class ReplaceText
{
	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.err.println(
				"usage: java backend.txtreplace.ReplaceText <replace spec file> <output directory>");
			System.exit(1);
		}
		File replaceSpecFile = new File(args[0]);
		File outputDir = new File(args[1]);
		if(!replaceSpecFile.isFile()) {
			System.err.printf("%s not an existing file", replaceSpecFile);
			System.err.println();
			System.exit(1);
		}
		if(outputDir.isFile()) {
			System.err.printf("%s is an existing file", outputDir);
			System.err.println();
			System.exit(1);
		}
		if(!outputDir.isDirectory()) {
			System.out.println("Creating directory: " + outputDir.mkdirs());
		}
		System.out.println("Performing rewrites specified in: " + replaceSpecFile);
		rewriteFiles(replaceSpecFile, outputDir);
	}

	public static void rewriteFiles(File replaceSpecFile, File outputDir)
		throws IOException
	{
		Collection<FileModification> fileMods =
			getFileModifications(replaceSpecFile);
		for(FileModification fileMod : fileMods) {
			File sourceFile = new File(fileMod.filename);
			File outputFile = join(outputDir, new File(pathWithOnlyNames(sourceFile)));
			ensureParentDirExists(outputFile);
			System.out.printf("Rewrites of %s written to %s",
			 fileMod.filename, outputFile);
			System.out.println();
			fileMod.rewriteFile(outputFile);
		}
	}

	public static Collection<FileModification> getFileModifications(File replaceSpec)
		throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(replaceSpec));
		// map from file names to replacement specifications
		Map<String, List<ReplaceInfo>> replaceMap =
			new HashMap<String, List<ReplaceInfo>>();
		String line;
		while((line = br.readLine()) != null) {
			// skip blank lines
			if(line.equals(""))
				continue;
			ReplaceInfo replaceInfo = parseReplaceInfo(line);
			String filename = replaceInfo.filename;
			// get list of replacements for file
			List<ReplaceInfo> replacements;
			if(replaceMap.containsKey(replaceInfo.filename))
				replacements = replaceMap.get(filename);
			else {
				replacements = new LinkedList<ReplaceInfo>();
				replaceMap.put(filename, replacements);
			}
			// add replaceInfo to list
			replacements.add(replaceInfo);
		}
		br.close(); // close replaceSpec file
		// constructing FileModification
		Set<Map.Entry<String, List<ReplaceInfo>>> entries =
			replaceMap.entrySet();
		List<FileModification> fileMods =
			new ArrayList<FileModification>(entries.size());
		for(Map.Entry<String, List<ReplaceInfo>> replaceEntry : entries) {
			String filename = replaceEntry.getKey();
			List<ReplaceInfo> replacements = replaceEntry.getValue();
			FileModification fileMod = new FileModification(filename);
			fileMod.addAll(replacements);
			fileMods.add(fileMod);
		}
		return fileMods;
	}
	
	final static Pattern replaceInfoDelimiterPat = Pattern.compile("\\|");
	
	public static ReplaceInfo parseReplaceInfo(String line) {
		String[] tokens = replaceInfoDelimiterPat.split(line);
		assert tokens.length == 7;
		String filename = tokens[0];
		int startLine = Integer.parseInt(tokens[1]),
		    startCol  = Integer.parseInt(tokens[2]),
		    endLine   = Integer.parseInt(tokens[3]),
		    endCol    = Integer.parseInt(tokens[4]);
		String oldText = tokens[5];
	  String newText = tokens[6];
	  return new ReplaceInfo(
	  	filename,
	  	startLine,
	  	startCol,
	  	endLine,
	  	endCol,
	  	oldText,
	  	newText);
	}
	
	public static File join(File prefix, File suffix) {
		String prefixPath = prefix.toString().trim();
		return prefixPath.equals("") ?
			suffix :
			new File(prefixPath + File.separator + suffix.toString());
	}

	/** Removes ancestor dirs so that no dots are included in the path
	  * Example:
	  * pathWithOnlyNames('../../tmp/../gnu/trove/TIntStack.java')
	  *   = 'gnu/trove/TIntStack.java'
	  */
	public static String pathWithOnlyNames(File file) {
		String path = file.getName();
		File currentFile = file;
		while(true) {
			File parentDir = currentFile.getParentFile();
			if(parentDir == null) {
				return path;
			}
			String dirname = parentDir.getName();
			if(dirname.length() == 0 || !Character.isLetter(dirname.charAt(0))) {
				return path;
			}
			else {
				path = parentDir.getName() + File.separator + path;
				currentFile = parentDir;
			}
		}
	}

	public static void ensureParentDirExists(File file) {
		File parent = file.getParentFile();
		if(parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
}


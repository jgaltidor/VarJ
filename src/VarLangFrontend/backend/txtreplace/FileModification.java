package backend.txtreplace;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collection;

import java.io.File;

public class FileModification
{
	public final String filename;
	
	private final SortedSet<ReplaceInfo> replacements =
		new TreeSet<ReplaceInfo>(new DescendingOrder());	
	
	public static class DescendingOrder implements Comparator<ReplaceInfo>
	{
		public int compare(ReplaceInfo r1, ReplaceInfo r2) {
			int lineComparison =
				(new Integer(r2.startLine)).compareTo(r1.startLine);
			return (lineComparison != 0) ?
				lineComparison :
				(new Integer(r2.startCol)).compareTo(r1.startCol);
		}
	}
	
	public FileModification(String filename) {
		this.filename = filename;
	}
	
	public boolean add(ReplaceInfo info) {
		boolean replacementAdded = replacements.add(info);
		if(!replacementAdded) {
			System.err.println("Replacement not added: " + info);
		}
		return replacementAdded;
	}

	public boolean addAll(Collection<? extends ReplaceInfo> otherReplacements) {
		boolean replacementAdded = false;
		for(ReplaceInfo info : otherReplacements) {
			replacementAdded |= this.add(info);
		}
		return replacementAdded;
	}

	public void rewriteFile(File outFile) throws java.io.IOException
	{
		FileLines filelines = new FileLines(filename);
		for(ReplaceInfo replaceInfo : replacements) {
			filelines.replace(replaceInfo);
		}
		filelines.writeFile(outFile);
	}
}

package tame;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.io.File;


/** Extends BaseParams with a main parameter
  * for specifying a list of file paths.
  * Directories specified on the input paths
  * will be traversed, recursively, to collect
  * a list of paths to the source files that
  * were contained in the directory.
  */
public class FilesParams extends BaseParams
{
	// Main Parameter
  @Parameter(description = "source files/directories")
  protected List<String> filepaths;

  /** Will store list of Java source files to analyze
    * after calling preprocessArgs()
    */
  public List<String> sourceFiles = null;


	public void preprocessArgs(String[] args) throws ParameterException {
		super.preprocessArgs(args);
		// Get source file paths
		if(filepaths != null) {
			sourceFiles = collectSourceFiles(filepaths);
			if(sourceFiles.isEmpty()) {
				throw new ParameterException("No source files found in paths");
			}
		}
		else {
			sourceFiles = java.util.Collections.emptyList();
		}
		if(filepaths == null && jastaddjArgs.isEmpty()) {
			throw new ParameterException("No arguments specified");
		}
	}


	/** Returns new command line arguments with where
	  * given directory paths were transformed
	  * to a list of paths to the source files that
	  * were contained in the directory.
	  */
	public List<String> createCompilerArgs() {
		List<String> newArgs = super.createCompilerArgs();
		// Add collected source files to args
		newArgs.addAll(sourceFiles);
		return newArgs;
	}
	

	public static List<String> collectSourceFiles(Collection<?> paths) {
		LinkedList<String> srcfiles = new LinkedList<String>();
		collectSourceFiles(paths, srcfiles);
		return srcfiles;
	}


	public static void collectSourceFiles(Collection<?> paths,
		List<String> srcfiles)
	{
		for(Object path : paths)
			getFiles(path.toString(), srcfiles);
	}

	public static void getFiles(String path, List<String> srcfiles) {
		File f = new File(path);
		if(f.isFile() && path.endsWith(".java")) {
			srcfiles.add(path);
		}
		else if(f.isDirectory()) {
			for(File child : f.listFiles())
				getFiles(child.getPath(), srcfiles);
		}
	}

	public static java.util.List<String> collectSourceFiles(String path) {
		LinkedList<String> srcfiles = new LinkedList<String>();
		getFiles(path, srcfiles);
		return srcfiles;
	}
	
	public static void main(String[] args) {
		VarFrontend vf = new VarFrontend();
		FilesParams params = new FilesParams();
		parseAndPreProcess(args, vf, params, "tame.FilesParams");
		compile(vf, params);
	}
}

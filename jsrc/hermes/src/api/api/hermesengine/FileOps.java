package api.hermesengine;

import java.io.File;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public class FileOps {
	public static String ProperPath(String path) {
		path = path.replaceAll("/",File.separator);
		path = path.replaceAll("\\",File.separator);
		path = path.replaceAll("\\\\",File.separator);
		return path;
	}
}

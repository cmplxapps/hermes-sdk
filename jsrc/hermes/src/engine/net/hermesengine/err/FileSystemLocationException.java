
/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

package net.hermesengine.err ; public class FileSystemLocationException extends Exception {
	private static final long serialVersionUID = 1L;
	public FileSystemLocationException(String label) { super(label); }}
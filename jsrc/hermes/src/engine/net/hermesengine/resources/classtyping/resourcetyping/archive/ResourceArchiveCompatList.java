package net.hermesengine.resources.classtyping.resourcetyping.archive;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

/* 
 * This type will detail what archiving and compression formats a resource can ingest.
 * The non-static final `FORMATS` array contains the compatible formats ({".tar", ".gz", ".xz", ...}).
 * Use "*" to include all formats, "*#compress" for all compression formats, and "*#archive" for
 * all archive formats.
 */

public class ResourceArchiveCompatList {
	
	public final String[] FORMATS = {
		"*"
	};
	
	public ResourceArchiveCompatList() {}
}

package net.hermesengine.resources.classtyping.resourcetyping;

import net.hermesengine.resources.classtyping.Resource;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public interface ResourceSubtype<R extends Resource> {
	public Object getParentPointer();
}

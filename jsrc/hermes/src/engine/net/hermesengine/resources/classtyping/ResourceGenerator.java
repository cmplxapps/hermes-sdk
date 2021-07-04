package net.hermesengine.resources.classtyping;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public class ResourceGenerator {
	
	public interface ResourceManifestGenerator {
		
		public void generateResourceManifest(Resource resource, String resource_file);
		
	}
	
	public static void generateVehicleResource(String name, String placedir) {}
	
}
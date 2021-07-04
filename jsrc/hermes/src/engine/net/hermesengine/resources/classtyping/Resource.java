package net.hermesengine.resources.classtyping;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import engine.hermesengine.ManifestableResource;
import net.hermesengine.resources.classtyping.resourcetyping.archive.ResourceArchiveCompatList;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public interface Resource extends ManifestableResource {
	public static interface WithDirSupport extends Resource {
		
	}
	
	public static interface WithArchiveSupport<C extends ResourceArchiveCompatList> {
		public Resource ingestArchive(String path);
	}
	
	public static class ResTypeRegistry {
		private Map<String, String> reg = new HashMap<String, String>();
		
		public ResTypeRegistry(Map<String, String> startdata) { for ( String i : startdata.keySet() ) { this.reg.put( i, startdata.get(i) ); }
		} public ResTypeRegistry() {}
		
		public Map<String, String> getData() { return this.reg; }
	}
	
	public String ID = "hres";
	
	public ManifestBuild build() throws Exception;
	
	public boolean generate(); // this is the method that will be used to generate a manifest file of the resource
	
	public static String getSessionResourcesDir() { // returns the directory of session resources
		String s = File.separator;switch ( System.getProperty("os.name").charAt(0) ) {
		case'W':case'w':return System.getProperty("tmpdir")+s+"hermesengine"+s+".sessionres"+s;
		case'L':case'l':case'M':case'm':return s+"tmp"+s+"hermesengine"+s+".sessionres"+s;
		default:return (String)null;}}
	
	public String getManifest(); // this will return the raw text of the resource manifest
	
	public boolean hasManifested(); // this will return whether or not the resource has already manifested
	
	public Resource ingest(String resfile); // this will return a resource created from the data of a given manifest
}

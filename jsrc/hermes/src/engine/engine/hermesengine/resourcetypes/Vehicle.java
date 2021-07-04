package engine.hermesengine.resourcetypes;

import java.io.File;

import net.hermesengine.propclass.Controllable;
import net.hermesengine.resources.classtyping.Resource;
import net.hermesengine.resources.classtyping.resourcetyping.NewResourceType;
import net.hermesengine.resources.classtyping.resourcetyping.ResourceType;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

/* 
 * This is a generic vehicle. It is a resource, among other things.
 */

@NewResourceType(id="hermes:vehicle;")
public class Vehicle extends ResourceType implements Resource, Controllable {
	protected boolean asDir = false;
	protected boolean asFile = false;
	protected String cwd = Resource.getSessionResourcesDir();
	
	public static String FileExtension = "car";
	
	public String Name;

	public Vehicle(String name) throws Exception { super(name); }
	
	public void init(String name) throws Exception {
		if ( name.charAt( name.length() - 1 ) == File.separatorChar ) { this.constructForDir(name); }
		else { this.constructForFile(name); }} // constructs the resource
	
	protected void constructForFile(String name) {
		this.asFile = true;
		this.Name = name;
	}
	
	protected void constructForDir(String name) {
		this.asDir = true;
		if ( name.charAt(name.length()) != File.separatorChar ) { name = name + File.separatorChar; }
		this.cwd += name ; this.Name = "_index_.hres" ;
	}
}

package net.hermesengine.resources;

import java.io.File;

import engine.hermesengine.resourcetypes.Vehicle;
import net.hermesengine.resources.classtyping.Resource;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public class StockCar1 extends Vehicle {
	protected String cwd=Resource.getSessionResourcesDir()+File.separatorChar+"cars"+File.separatorChar;
	
	public StockCar1(String name) throws Exception { super(name); }
	
	
}

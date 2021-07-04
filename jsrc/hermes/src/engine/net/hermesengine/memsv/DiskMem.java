package net.hermesengine.memsv;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.tools.ToolProvider;

public class DiskMem<O> {
	public String v;
	public String l;
	
	public DiskMem(String v, String l) { this.v = v ; this.l = l ; }
	
	public O get() throws FileNotFoundException {
		String objcontent = (String) null;
		Scanner sc = new Scanner(new File(l));
		while ( sc.hasNextLine() ) { objcontent += sc.nextLine(); }
		ToolProvider.getSystemJavaCompiler().run(null,null,null,objcontent);
		return (O) null;
	}
}

package net.hermesengine.resources.classtyping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ManifestBuild {
	protected boolean manifested = false;
	public ManifestBuild(Resource res, String id, String path) throws Exception {
		// creates the file objects
		File f = new File(path) ; f.setWritable(true) ;
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		// makes sure the file exists
		f.mkdirs() ; if ( !f.exists() ) { f.createNewFile(); }
		// makes sure the file is empty
		PrintWriter p = new PrintWriter(path);
		p.print("") ; p.close() ;
		// stamps the id and attempts to generate the manifest
		bw.write("<!!> " + id);
		this.manifested = res.generate();
		f.delete() ; fw.close() ; bw.close() ; }
	
	public boolean manifestedSuccessfully() { return this.manifested; } // returns whether the build was successful
}

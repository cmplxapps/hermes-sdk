package net.hermesengine.resources.classtyping.resourcetyping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import api.hermesengine.FileOps;
import net.hermesengine.err.FileSystemLocationException;
import net.hermesengine.resources.classtyping.ManifestBuild;
import net.hermesengine.resources.classtyping.Resource;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`

/* 
 * This class will be the parent type of many different "res-types" or "restypes",
 * which are a type of Resource, with the Resource interface being a derivative of
 * the EngineResource interface, a supertype for many, many engine objects. Resources
 * will create their own manifests which detail, in essence, how to recreate that
 * specific instance of the restype. You are completely free to do whatever you want
 * with these derivatives, but not the conventions. The class traditionally will need
 * to be able to parse a manifest and create an instance of itself from that manifest
 * and that manifest by itself, provide a file extension (without the '.' at the
 * beginning) that all manifests of this type will use (you pass the same string into
 * the `NewResourceType` annotation to register the extension with the restype, so pick
 * a unique one.), return Manifest content as a string, return whether or not the
 * resource has manifested, and other things, all of which are placed inside the
 * `net.hermesengine.resources.Resource` interface.
 */

@NewResourceType(id="hermes:@restype;")
public class ResourceType implements Resource {
	public String cwd = Resource.getSessionResourcesDir();
	public String Name;
	
	public final String ID = "hermes:@restype;";
	
	public ResourceType(String name) throws Exception { this.init( constructCWD(name) ); }
	
	public void init(String name) throws Exception {
		if ( name.charAt( name.length() - 1 ) == File.separatorChar ) {
			throw new FileSystemLocationException("This res-type cannot manifest as a directory."); } // throws error if dir
		else { this.constructForFile(name); }} // constructs if file
	
	protected String constructCWD(String name) {
		name = FileOps.ProperPath(name);
		int lastMark = 0;
		for ( int i = 0 ; i < name.length() ; i++ ) {
			if ( name.charAt(i) == File.separatorChar && i != (name.length() - 1) ) {
				i++;
				this.cwd += name.substring(lastMark,i);
				lastMark = i; }
		} return name.substring(lastMark, name.length() - 1); }
	
	protected void constructForFile(String name) { this.Name = name; }
	
	@Override public ManifestBuild build() throws Exception {
		return new ManifestBuild(this, this.ID, this.cwd + this.Name);
	}
	
	@Override public boolean generate() {
		File manifest = new File( this.cwd + this.Name ) ; manifest.mkdirs() ;
		
		if ( !manifest.exists() ) { // creates the files if it doesnt already exist
			try { manifest.createNewFile();
			} catch (Exception e) { return false; }} // the creation of the file failed
		
		manifest.setWritable(true);
		FileWriter f; try { f = new FileWriter(manifest); } catch ( Exception e ) { return false; } // tries to initialize the FileWriter
		BufferedWriter b = new BufferedWriter(f);

		try { b.write( this.getManifest() ); // tries to write this restype's manifest content
		} catch ( Exception e ) { try { f.close() ; b.close() ; // tries to close the writers before returning failure
		} catch ( Exception err ) {} return false ; }
		try{ f.close() ; b.close() ; } catch ( Exception error ) {} // tries to close the writers before returning success
		return true; }

	@Override public String getManifest() { return "<NULL-MANIFEST>\n[this-is-a-placeholder-restype]\n@NULL-MNFST;"; }

	/* 
	 * THIS METHOD WILL RETURN WHETHER A GIVEN INSTANCE OF THIS RES-TYPE
	 * HAS A VALID AND UP-TO-DATE MANIFEST IN THE APPROPRIATE LOCATION
	 * BY FIRST CHECKING IF IT EXISTS, AND THEN CHECKING IF ITS TEXT
	 * EQUALS THAT OF A VALID MANIFEST OF THE INSTANCE.
	 */
	@Override @SuppressWarnings("null") public boolean hasManifested() {
		File manifest = new File (
				Resource.getSessionResourcesDir() + this.Name + ".hres" );
		Scanner f = (Scanner) null;
		
		if ( manifest.exists() ) {
			
			try { f = new Scanner( manifest ); // tries to create a `Scanner` object that reads the proper resource manifest
			} catch ( Exception e ) { f.close() ; return false ; } // method is unable to verify the file, and thus returns `false` to be safe
			
			String content = ""; // the String that will eventually contain all of the text inside of the current manifest
			
			while ( f.hasNextLine() ) { content += f.nextLine(); }
			
			if ( content == this.getManifest() ) { f.close() ; return true ; // the resource has manifested; `true` should be returned
			} else { f.close() ; return false ; } // the file exists, but is not the correct manifest
		} else { // the file does not exist; `false` should be returned
			try { f.close(); } catch ( Exception e ) {} // attempts to close the scanner before returning `false`
			return false; }}

	@Override public Resource ingest(String resfile) { return (Resource) null ; }
}

/*
 * Yes, this is actually how I write Java code. -Jim
 */

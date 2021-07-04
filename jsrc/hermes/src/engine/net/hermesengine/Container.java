package net.hermesengine;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

public class Container<T> {
	private T value;
	
	public Container(T val) {
		this.value = val;
	}
	
	public T get() {return this.value;}
	public void set(T val) {this.value = val;}
}

package net.hermesengine.tables;

import java.util.Collection;
import java.util.HashSet;

import net.hermesengine.Container;

/* REMEMBER TO USE `File.separatorChar` OR `File.separator` INSTEAD OF `\\` OR `/`
 * WHEN WRITING OUT ANY PATHS INSIDE OF CONSTANTS OR METHODS SO THAT THE END BUILD
 * WILL BE BOTH UNIX AND NT COMPATIBLE (Linux/MacOS and Windows respectively). */

@SuppressWarnings("unused")
public class GenericTable<T> implements Table {
	private Container<T>[] table;
	private final int X;
	private final int Y;
	
	@SuppressWarnings("unchecked")
	public GenericTable(int x, int y) {
		this.table = new Container[x * y];
		this.X = x;
		this.Y = y;
	}
	
	public T get(int xcoord, int ycoord) { return this.table[xcoord+(this.X*ycoord)].get();}
	public T get() { return this.table[0].get();}
	
	public void set(int xcoord, int ycoord, T NewValue) { this.table[xcoord+(this.X*ycoord)] = new Container<T>(NewValue);}
	public void set(int xcoord, T NewValue) { this.table[xcoord] = new Container<T>(NewValue);}
	
	public Collection<T> getRow(int ycoord) {
		Collection<T> CurrentData = new HashSet<T>();
		int Index = ycoord * this.X;
		for ( int i = Index; i < Index + this.X; i++ ) { CurrentData.add(this.table[i].get()); }
		return CurrentData;
	} public Collection<T> getRow() { return this.getRow(0); }
	
	public Collection<T> getColumn(int xcoord) {
		Collection<T> CurrentData = new HashSet<T>();
		for ( int i = xcoord; i <= this.X * this.Y; i+=5 ) { CurrentData.add(this.table[i].get()); }
		return CurrentData;
	} public Collection<T> getColumn() { return this.getColumn(0); }
}
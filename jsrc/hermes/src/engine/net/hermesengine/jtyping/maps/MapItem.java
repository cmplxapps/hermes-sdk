
package net.hermesengine.jtyping.maps;

public class MapItem<O> {
	protected O item;
	public int x;
	public int y;
	
	public MapItem(O item, int setx, int sety) {
		this.item = item;
		x = setx ; y = sety ;
	}
	
	public O get() {
		return this.item;
	} public boolean set(O newitem) {
		this.item = newitem;
		return true ; }
}

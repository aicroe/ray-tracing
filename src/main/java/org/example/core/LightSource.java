package org.example.core;

import org.example.matlib.Point;

/**
 * 
 * @author diegogarcia
 *
 */
public class LightSource {
	
	private NColor il;
	private Point position;
	
	public LightSource(Point pos, NColor c) {
		
		position = pos;
		il = c;
	}
	
	public NColor getIl() {
		
		return il;
	}
	
	public Point getPosition() {
		
		return position;
	}

}

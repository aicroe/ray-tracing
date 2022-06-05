package org.example.core;

import org.example.matlib.Point;
import org.example.matlib.Vector;
import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Point.Z;

/**
 * 
 * @author diegogarcia
 *
 */
public class Ray {
	
	private Point ro;
	private Vector rd;
	
	public Ray(Point po, Vector v) {
		
		ro = po;
		rd = v;
	}
	
	public Vector getRD() {
		
		return rd;
	}
	
	public Point getRO() {
		
		return ro;
	}
	
	public Point getPoint(double t) {
		
		double x = ro.get(X) + t * rd.get(X);
		double y = ro.get(Y) + t * rd.get(Y);
		double z = ro.get(Z) + t * rd.get(Z);
		return new Point(x, y, z);
	}
	

}

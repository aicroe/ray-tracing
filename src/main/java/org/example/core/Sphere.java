package org.example.core;

import org.example.matlib.Point;
import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Point.Z;
import static java.lang.Math.sqrt;

/**
 * 
 * @author diegogarcia
 *
 */
public class Sphere extends Object {
	
	private Point center;
	private double r;
	
	public Sphere(Point c, double R, int type) {
		
		super(type);
		setNb(1.50f);
		center = c;
		r = R;
	}
	
	public Point getCenter() {
		
		return center;
	}
	
	public double getRadix() {
		
		return r;
	}
	
	@Override
	public Intersection calcIntersectn(Ray rp) {
		
		double xc = center.get(X);
		double yc = center.get(Y);
		double zc = center.get(Z);
		double xo = rp.getRO().get(X);
		double yo = rp.getRO().get(Y);
		double zo = rp.getRO().get(Z);
		double xd = rp.getRD().get(X);
		double yd = rp.getRD().get(Y);
		double zd = rp.getRD().get(Z);
		
		double A = pow2(xd) + pow2(yd) + pow2(zd);
		double B = xd * (xo-xc) + yd * (yo-yc) + zd * (zo-zc);
		double C = pow2(xo-xc) + pow2(yo-yc) + pow2(zo-zc) -pow2(r);
		double d = pow2(B) - C * A;
		if (d < 0) {
			return null;
		} else if (d < 0.001){ // d = 0
			return new Intersection(-B/A);
		} else { // d > 0
			double sqrtd = sqrt(d);
			double t1 = (-B + sqrtd) / A;
			double t2 = (-B - sqrtd) / A;
			return new Intersection(t1, t2);
		}
		
	}
	
	public double pow2(double x) {
		
		return x * x;
	}

}

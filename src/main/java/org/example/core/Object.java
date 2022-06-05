package org.example.core;

/**
 * 
 * @author diegogarcia
 *
 */
public abstract class Object {

	public static final int SOLID = 0;
	public static final int MIRROR = 1;
	public static final int GLASS = 2;
	
	private NColor od;
	private float kd;
	private float ks;
	private int n;
	private int type;
	private float nb;
	
	public Object(int _type) {
		
		kd = 0.6f;
		ks = 0.4f;
		n = 5;
		type = _type;
		nb = Float.NaN;
	}
	
	public float getNb() {
		
		return nb;
	}
	
	public void setNb(float v) {
		
		nb = v;
	}
	
	public int getType() {
		
		return type;
	}
	
	public int getN() {
		
		return n;
	}
	
	public void setN(int _n) {
		
		n = _n;
	}

	public void setOd(NColor c) {
		
		od = c;
	}
	
	public NColor getOd() {
		
		return od;
	}
	
	public void setKs(float v) {
		
		ks = v;
	}
	
	public void setKd(float v) {
		
		kd = v;
	}
	
	public float getKs() {
		
		return ks;
	}
	
	public float getKd() {
		
		return kd;
	}
	
	public abstract Intersection calcIntersectn(Ray ray);
}

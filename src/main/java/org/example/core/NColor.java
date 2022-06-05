package org.example.core;

/**
 * 
 * @author diegogarcia
 *
 */
public class NColor {
	
	private float[] values; 
	
	public static final NColor WHITE = new NColor(1, 1, 1);
	public static final NColor LIGHT_GRAY = new NColor(0.752941176f, 0.752941176f, 0.752941176f);
	public static final NColor GRAY = new NColor(0.501960784f, 0.501960784f, 0.501960784f);
	public static final NColor DARK_GRAY = new NColor(0.250980392f, 0.250980392f, 0.250980392f);
	public static final NColor BLACK = new NColor(0, 0, 0);
	public static final NColor RED = new NColor(1, 0, 0);
	public static final NColor GREEN = new NColor(0, 1, 0);
	public static final NColor BLUE = new NColor(0, 0, 1);
	public static final NColor PINK = new NColor(1, 0.68627451f, 0.68627451f);
	public static final NColor ORANGE = new NColor(1, 0.784313725f, 0);
	public static final NColor YELLOW = new NColor(1, 1, 0);
	public static final NColor MAGENTA = new NColor(1, 0, 1);
	public static final NColor CYAN = new NColor(0, 1, 1);
	
	
	public NColor(float _r, float _g, float _b) {
		
		values = new float[3];
		values[0] = _r;
		values[1] = _g;
		values[2] = _b;
		
	}
	
	public float[] values() {
		
		return values;
	}
	
	public float getR() {
		
		return values[0];
	}
	
	public float getG() {
		
		return values[1];
	}
	
	public float getB() {
		
		return values[2];
	}

}

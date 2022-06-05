package org.example.matlib;

/**
 * 
 * @author diegogarcia
 *
 */
public class Point extends Matrix {
	
	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;
	public static final int W = 3;
	
	public Point(double... v) {
		
		super(1, v.length + 1);
		for (int i = 0; i< v.length; i++) set(0, i, v[i]);
		set(0, v.length, 1);
	}
	
	public double get(int i) {
		
		return get(0, i);
	}
	
	public void set(int i, double v) {
		
		set(0, i, v);
	}
	
	@Override
	public Matrix add(Matrix m) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Matrix sub(Matrix m) {

		throw new UnsupportedOperationException();
	}
	
	public Vector sub(Point p) {
		
		Vector vector = new Vector(cols() - 1);
		sub(this, p, vector);
		return vector;
	}
	
	@Override
	public Point mul(Matrix m) {
		
		Point result = new Point(cols() - 1);
		mul(this, m, result);
		return result;
	}

}

package org.example.matlib;

/**
 * 
 * @author diegogarcia
 *
 */
public class Vector extends Matrix {
	
	private int dim;
	
	public Vector(int dim) {
		
		super(1, dim + 1);
		this.dim = dim;
	}
	
	public Vector(double... v) {
		
		this(v.length);
		for (int i = 0; i< v.length; i++) set(0, i, v[i]);
		set(0, v.length, 0);
	}
	
	public double get(int i) {
		
		return get(0, i);
	}
	
	public void set(int i, double v) {
		
		set(0, i, v);
	}
	
	public int dim() {
		
		return dim;
	}
	
	@Override
	public Vector add(Matrix m) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Vector sub(Matrix m) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Vector mul(Matrix m) {
		
		Vector result = new Vector(m.cols() - 1);
		mul(this, m, result);
		return result;
	}
	
	public double dot(Vector v) {
		
		Matrix t = v.transpose();
		return this.mul(t).get(0);
	}
	
	public Vector add(Vector v) {
		
		Vector result = new Vector(dim);
		add(this, v, result);
		return result;
	}
	
	public Vector sub(Vector v) {
		
		Vector result = new Vector(dim);
		sub(this, v, result);
		return result;
	}
	
	public double norm() {
		
		double sum = 0;
		for (int i = 0; i< dim; i++) {
			sum += get(i) * get(i);
		}
		return Math.sqrt(sum);
	}

	public Vector normalize() {
		
		return mul(1.0 / norm());
	}
	
	public Vector mul(double k) {

		Vector n = new Vector(dim);
		for (int i = 0; i< dim; i++) {
			n.set(i, get(i) * k);
		}
		return n;
	}

}

package org.example.matlib;;

/**
 * 
 * @author diegogarcia
 *
 */
public class Transformations {
	
	public static Matrix R3D(Vector n, double theta) {
		
		Matrix m = new Matrix(4, 4);
		double nx = n.get(0);
		double ny = n.get(1);
		double nz = n.get(2);
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		m.set(0, 0, nx * nx * (1 - cos) + cos);
		m.set(0, 1, nx * ny * (1 - cos) + nz * sin);
		m.set(0, 2, nx * nz * (1 - cos) - ny * sin);
		m.set(1, 0, nx * ny * (1 - cos) - nz * sin);
		m.set(1, 1, ny * ny * (1 - cos) + cos);
		m.set(1, 2, ny * nz * (1 - cos) + nx * sin);
		m.set(2, 0, nx * nz * (1 - cos) + ny * sin);
		m.set(2, 1, ny * nz * (1 - cos) - nx * sin);
		m.set(2, 2, nz * nz * (1 - cos) + cos);
		m.set(3, 3, 1.0);
		return m;
	}
	
	public static Matrix S3D(Vector n, double k) {
		
		Matrix m = new Matrix(4, 4);
		double nx = n.get(0);
		double ny = n.get(1);
		double nz = n.get(2);
		m.set(0, 0, 1 + (k - 1) * nx * nx);
		m.set(0, 1, (k - 1) * nx * ny);
		m.set(0, 2, (k - 1) * nx * nz);
		m.set(1, 0, (k - 1) * nx * ny);
		m.set(1, 1, 1 + (k - 1) * ny * ny);
		m.set(1, 2, (k - 1) * ny * nz);
		m.set(2, 0, (k - 1) * nx * nz);
		m.set(2, 1, (k - 1) * ny * nz);
		m.set(2, 2, 1 + (k - 1) * nz * nz);
		m.set(3, 3, 1.0);
		return m;
	}
	
	public static Matrix P3D(Vector n) {
		
		return S3D(n, 0);
	}
	
	public static Matrix Ref3D(Vector n) {
		
		return S3D(n, -1);
	}
	
	public static Matrix Sh3D(double s, double t) {
		
		Matrix m = new Matrix(4, 4);
		m.set(0, 0, 1.0);
		m.set(1, 1, 1.0);
		m.set(2, 2, 1.0);
		m.set(3, 3, 1.0);
		m.set(1, 0, s);
		m.set(1, 2, t);
		return m;
	}
	
	public static Matrix T3D(double dx, double dy, double dz) {
		
		Matrix m = new Matrix(4, 4);
		m.set(0, 0, 1.0);
		m.set(1, 1, 1.0);
		m.set(2, 2, 1.0);
		m.set(3, 3, 1.0);
		m.set(3, 0, dx);
		m.set(3, 1, dy);
		m.set(3, 2, dz);
		return m;
	}
	
	public static Matrix PP(double d) {
		
		Matrix m = new Matrix(4, 4);
		m.set(0, 0, 1.0);
		m.set(1, 1, 1.0);
		m.set(2, 2, 1.0);
		m.set(2, 3, 1.0 / d);
		return m;
	}

}

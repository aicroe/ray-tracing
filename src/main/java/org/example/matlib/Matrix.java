package org.example.matlib;
import java.util.Arrays;

/**
 * 
 * @author diegogarcia
 *
 */
public class Matrix implements Cloneable {

	private double[][] mat;
	
	public Matrix(double[][] _mat) {
		
		mat = _mat;
	}
	
	public Matrix(int rows, int cols) {

		mat = new double[rows][cols];
	}
	
	public double get(int i, int j) {
		
		return mat[i][j];
	}
	
	public void set(int i, int j, double v) {
		
		mat[i][j] = v;
	}
	
	public int rows() {
		
		return mat.length;
	}
	
	public int cols() {
		
		return mat[0].length;
	}
	
	public Matrix add(Matrix m) {

		if (rows() != m.rows() || cols() != m.cols()) {
			throw new UnsupportedOperationException();
		}
		Matrix result = new Matrix(rows(), cols());
		add(this, m, result);
		return result;
	}

	//m1.rows == m2.rows == result.rows && m1.cols == m2.cols == result.cols 
	public void add(Matrix m1, Matrix m2, Matrix result) {
		
		for (int i = 0; i< result.rows(); i++) {
			for (int j = 0; j< result.cols(); j++) {
				result.set(i, j, m1.get(i, j) + m2.get(i, j));
			}
		}
	}
	
	public Matrix sub(Matrix m) {
		
		if (rows() != m.rows() || cols() != m.cols()) {
			throw new UnsupportedOperationException();
		}
		Matrix result = new Matrix(rows(), cols());
		sub(this, m, result);
		return result;
	}

	//m1.rows == m2.rows == result.rows && m1.cols == m2.cols == result.cols
	public void sub(Matrix m1, Matrix m2, Matrix result) {
		
		for (int i = 0; i< result.rows(); i++) {
			for (int j = 0; j< result.cols(); j++) {
				result.set(i, j, m1.get(i, j) - m2.get(i, j));
			}
		}
	}
	
	public Matrix mul(Matrix m) {

		if (rows() != m.cols() || cols() != m.rows()) {
			throw new UnsupportedOperationException();
		}
		Matrix result = new Matrix(rows(), m.cols());
		mul(this, m, result);
		return result;
	}
	
	//result.rows == m1.cols && result.cols == m2.rows
	public void mul(Matrix m1, Matrix m2, Matrix result) {
		
		for (int i = 0; i< result.rows(); i++) {
			for (int j = 0; j< result.cols(); j++) {
				result.set(i, j, row_x_col(i, m1, j, m2));
			}
		}
	}
	
	//m1.rows == m2.cols && m1.cols == m2.rows
	private double row_x_col(int row, Matrix m1, int col, Matrix m2) {
		
		double sum = 0;
		for (int i = 0; i< m1.cols(); i++) {
			sum += m1.get(row, i) * m2.get(i, col);
		}
		return sum;
	}
	
	public Matrix transpose() {
		
		Matrix t = new Matrix(cols(), rows());
		for (int i = 0; i< rows(); i++) {
			for (int j = 0; j< cols(); j++) {
				t.set(j, i, get(i, j));
			}
		}
		return t;	
	}
	
	public void scalarMul(double scalar) {
		
		for (int i = 0; i< rows(); i++) {
			for (int j = 0; j< cols(); j++) {
				mat[i][j] *= scalar;
			}
		}
	}
	
	public String toString() {
		
		String str = "";
		for (int i = 0; i< rows(); i++) {
			str += Arrays.toString(mat[i]) + "\n";
		}
		return str;
	}
	
	public static String toString(int[][] mat) {
		
		String str = "";
		for (int i = 0; i< mat.length; i++) {
			str += Arrays.toString(mat[i]) + "\n";
		}
		return str;
	}
	
	public Object clone() {
		
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
}

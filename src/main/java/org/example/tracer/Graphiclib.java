package org.example.tracer;


import java.awt.Graphics;

/**
 * 
 * @author diegogarcia
 *
 */

public class Graphiclib {
	
	
	public static void putPixel(Graphics g, int x, int y) {
		
		g.drawLine(x, y, x, y);
	}
	
	public static void fillRect(
			Graphics g, int x0, int y0, int width, int height) {
		
		for (int x = x0; x <= x0 + width; x++) {
			for (int y = y0; y <= y0 + height; y++) {
				putPixel(g, x, y);
			}
		}
	}
	
	public static void drawLine(
			Graphics g, int x0, int y0, int x1, int y1) {
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		if (dx == 0 && dy == 0) {
			putPixel(g, x0, y0);
		} else if (dy == 0) {
			int xstep = (int)Math.signum(dx);
			int x = x0;
			while (x * xstep < x1 * xstep) {
				putPixel(g, x, y0);
				x += xstep;
			}
		} else if (dx == 0) {
			int ystep = (int)Math.signum(dy);
			int y = y0;
			while (y * ystep < y1 * ystep) {
				putPixel(g, x0, y);
				y += ystep;
			}
		} else if ((dy > 0 && dx > 0 && dx >= dy) || 
				(dy < 0 && dx < 0 && dx <= dy)) {
			// 0 < m <= 1
			drawLine_E_NE(g, x0, y0, x1, y1);
			
		} else if ((dx > 0 && dy < 0 && dx >= Math.abs(dy)) || 
				(dx < 0 && dy > 0 &&  Math.abs(dx) >= dy)) {
			// -1 <= m < 0
			drawLine_E_SE(g, x0, y0, x1, y1);
			
		} else if ((dx > 0 && dy > 0 && dy > dx) || 
				(dx < 0 && dy < 0 && dy < dx)) {
			// 1 < m
			drawLine_N_NE(g, x0, y0, x1, y1);
			
		} else if ((dx > 0 && dy < 0 && Math.abs(dy) > dx) || 
				(dx < 0 && dy > 0 && dy > Math.abs(dx))) {
			// -1 > m
			drawLine_S_SE(g, x0, y0, x1, y1);
		}
		
	}
	
	private static void drawLine_E_NE(
			Graphics g, int x0, int y0, int x1, int y1) {
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xstep = (int)Math.signum(dx);
		int ystep = (int)Math.signum(dy);
		int d = xstep * (2 * dy - dx); // d = 2 * a + b
		int de = xstep * 2 * dy; // de = 2 * a
		int dne = xstep * 2 * (dy - dx); // dne = 2 * (a + b)
		int x = x0;
		int y = y0;
		putPixel(g, x, y);
		while (x * xstep < x1 * xstep) {
			if (d <= 0) { // e
				d = d + de;
				x += xstep;
			} else { // ne
				d = d + dne;
				x += xstep;
				y += ystep;
			}
			putPixel(g, x, y);
		}
		
	}
	
	private static void drawLine_E_SE(
			Graphics g, int x0, int y0, int x1, int y1) {
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xstep = (int)Math.signum(dx);
		int ystep = (int)Math.signum(dy);
		int d = xstep * (2 * dy + dx); // d = 2 * a - b
		int de = xstep * 2 * dy; // de = 2 * a
		int dse = xstep * 2 * (dy + dx); // dse = 2 * (a - b)
		int x = x0;
		int y = y0;
		putPixel(g, x, y);
		while (x * xstep < x1 * xstep) {
			if (d >= 0) { // e
				d = d + de;
				x += xstep;
			} else { // se
				d = d + dse;
				x += xstep;
				y += ystep;
			}
			putPixel(g, x, y);
		}
		
	}
	
	private static void drawLine_N_NE(
			Graphics g, int x0, int y0, int x1, int y1) {
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xstep = (int)Math.signum(dx);
		int ystep = (int)Math.signum(dy);
		int d = xstep * (dy - 2 * dx); // d = a + 2 * b
		int dn = xstep * 2 * (-dx); // dn = 2 * b
		int dne = xstep * 2 * (dy - dx); // dne = 2 * (a + b)
		int x = x0;
		int y = y0;
		putPixel(g, x, y);
		while (y * ystep < y1 * ystep) {
			if (d >= 0) { // n
				d = d + dn;
				y += ystep;
			} else { // ne
				d = d + dne;
				y += ystep;
				x += xstep;
			}
			putPixel(g, x, y);
		}
		
	}
	
	private static void drawLine_S_SE(
			Graphics g, int x0, int y0, int x1, int y1) {
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xstep = (int)Math.signum(dx);
		int ystep = (int)Math.signum(dy);
		int d = xstep * (dy + 2 * dx); // d = a - 2 * b
		int ds = xstep * 2 * (dx); // ds = 2 * -b
		int dse = xstep * 2 * (dy + dx); // dse = 2 * (a - b) 
		int y = y0;
		int x = x0;
		putPixel(g, x, y);
		while (y * ystep < y1 * ystep) {
			if (d <= 0) { // s
				d = d + ds;
				y += ystep;
			} else { // se
				d = d + dse;
				y += ystep;
				x += xstep;
			}
			putPixel(g, x, y);
		}
		
	}
	
	public static void drawCircle(Graphics g, int x0, int y0, int r) {
		
		int x = 0;
		int y = r;
		int d = 1 - r;
		int de = 3;
		int dse = -2 * r + 5;
		drawCirclePoints(g, x0, y0, x, y);
		while (y > x) {
			if (d < 0) { // e
				d = d + de;
				de = de + 2;
				dse = dse + 2;
				x = x + 1;
			} else { // se
				d = d + dse;
				de = de + 2;
				dse = dse + 4;
				x = x + 1;
				y = y - 1;
			}
			drawCirclePoints(g, x0, y0, x, y);
		}
		
	}
	
	private static void drawCirclePoints(
			Graphics g, int x0, int y0, int x, int y) {

		putPixel(g, x0 + x, y0 + y);
		putPixel(g, x0 - x, y0 - y);
		putPixel(g, x0 - x, y0 + y);
		putPixel(g, x0 + x, y0 - y);
		putPixel(g, x0 + y, y0 + x);
		putPixel(g, x0 - y, y0 - x);
		putPixel(g, x0 + y, y0 - x);
		putPixel(g, x0 - y, y0 + x);
		
	}
	
	public static void drawEllipse(
			Graphics g, int x0, int y0, int rx, int ry) {
		
		int x = 0;
		int y = ry;
		int rx2 = rx * rx;
		int ry2 = ry * ry;
		int _2rx2 = 2 * rx2;
		int _2ry2 = 2 * ry2;
		int d = ry2 - rx2 * (ry); // d = b^2 - a^2 * (b - 0.25) 
		int de = 3 * ry2; // de = 3 * b^2
		int dse = 3 * ry2 - _2rx2 * (ry - 1); //dse = 3 * b^2 - 2 * a^2 * (b - 1)
		int diffx = ry2 * (x + 1); // gradiente componente x
		int diffy = (int)Math.round(rx2 * (y - 0.5)); // gradiente componente y
		drawEllipsePoints(g, x0, y0, x, y);
		// region 1
		while (diffy > diffx) {
			if (d <= 0) { // e
				d = d + de;
				de = de + _2ry2;
				dse = dse + _2ry2;
				diffx = diffx + ry2;
				//diffy = diffy;
				x = x + 1;
			} else { // se
				d = d + dse;
				de = de + _2ry2;
				dse = dse + _2ry2 + _2rx2;
				diffx = diffx + ry2;
				diffy = diffy - rx2;
				x = x + 1;
				y = y - 1;
			}
			drawEllipsePoints(g, x0, y0, x, y);
		}
		// d = b^2 * (x + 0.25) - a^2 * (2 * y - 1)
		d = ry2 * (x) - rx2 * (2 * y - 1);
		// ds = a^2 * (-2 * y + 3)
		int ds = rx2 * (-2 * y + 3);
		// dse = 2 * b^2 * (x + 1) - a^2 * (2 * y + 3)
		dse = _2ry2 * (x + 1) - rx2 * (2 * y - 3);
		// region 2
		while (y > 0) {
			if (d >= 0) { // s
				d = d + ds;
				ds = ds + _2rx2;
				dse = dse + _2rx2;
				y = y - 1;
			} else { // se
				d = d + dse;
				ds = ds + _2rx2;
				dse = dse + _2ry2 + _2rx2;
				x = x + 1;
				y = y - 1;
			}
			drawEllipsePoints(g, x0, y0, x, y);
		}
		
	}
	
	private static void drawEllipsePoints(
			Graphics g, int x0, int y0, int x, int y) {
		
		putPixel(g, x0 + x, y0 + y);
		putPixel(g, x0 - x, y0 - y);
		putPixel(g, x0 + x, y0 - y);
		putPixel(g, x0 - x, y0 + y);
		
	}
	
	public static void drawRect(
			Graphics g, int x0, int y0, int width, int height) {

		drawLine(g, x0, y0, x0 + width, y0);
		drawLine(g, x0, y0, x0, y0 + height);
		drawLine(g, x0 + width, y0, x0 + width, y0 + height);
		drawLine(g, x0, y0 + height, x0 + width, y0 + height);
		putPixel(g, x0 + width, y0 + height);
		
	}
	
	public static void drawTriangle(
			Graphics g,
			int x1, int y1,
			int x2, int y2,
			int x3, int y3) {

		drawLine(g, x1, y1, x2, y2);
		drawLine(g, x2, y2, x3, y3);
		drawLine(g, x3, y3, x1, y1);
		
	}
	
}

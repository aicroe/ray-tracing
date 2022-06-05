package org.example.tracer;
import static org.example.tracer.Graphiclib.putPixel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.example.core.NColor;
import org.example.core.Ray;
import org.example.core.Scene;
import org.example.matlib.Point;

/**
 * 
 * @author diegogarcia
 *
 */
public class RayTracer extends JFrame {
	
	public static final int TITLEBAR_LEN = 40;
	public static final int XGAP = 320;
	public static final int YGAP = 240;
	public static final int MARGIN = 2;
	
	private Window window;
	private Point observer;
	private double d = 500.0;
	private Scene scene;
	
	public RayTracer() {

		setSize(640 + MARGIN * 2, 480 + TITLEBAR_LEN + MARGIN);
		setResizable(false);

		observer = new Point(0, 0, -d);
		window = new Window(-320, 240, 320, -240);
		scene = new Scene();
	}
	
	public void init() {
		
		scene.init();
	}

	@Override
	public void paint(Graphics g) {
		
		for (int y = window.ymin; y > window.ymax; y--) {
			for (int x = window.xmin; x < window.xmax; x++) {
				Ray rp = new Ray((Point)observer.clone(), 
						new Point(x, y, 0).sub(observer).normalize());
				NColor c = scene.rayTracing(rp, 2, null);
				g.setColor(new Color(c.getR(), c.getG(), c.getB()));
				putPixel(g, getXViewport(x), getYViewport(y));
				//_sleep(0);
			}
		}

	}
	
	private int getXViewport(int x) {
		
		return x + XGAP + MARGIN;
	}
	
	private int getYViewport(int y) {
		
		return YGAP - y + TITLEBAR_LEN;
	}
	
	private void _sleep(int m) {
		
		try {
			Thread.sleep(m);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

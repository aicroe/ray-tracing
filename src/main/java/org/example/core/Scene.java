package org.example.core;

import java.util.ArrayList;
import java.util.List;

import org.example.matlib.Point;
import org.example.matlib.Vector;

import static java.lang.Math.pow;

/**
 * 
 * @author diegogarcia
 *
 */
public class Scene {
	
	private List<Object> objects;
	private List<LightSource> lights;
	private float ka;
	private NColor ia;
	private float na;
	
	public Scene() {
		
		objects = new ArrayList<>();
		lights = new ArrayList<>();
		ia = new NColor(.1f, .1f, .1f);
		ka = 0.1f;
		na = 1.0003f;
	}
	
	public void setKa(float ka) {
		
		this.ka = ka;
	}
	
	public void setIa(NColor c) {
		
		ia = c;
	}
	
	public void init() {
		int t = Sphere.SOLID;
		// int t = Sphere.MIRROR;
		// int t = Sphere.GLASS;
		Sphere s1 = new Sphere(new Point(-500, 400, 1400), 80, t);
		Sphere s2 = new Sphere(new Point(200, -300, 700), 65, t);
		Sphere s3 = new Sphere(new Point(180, 250, 680), 65, t);
		Sphere s4 = new Sphere(new Point(100, 50, 600), 50, t);
		Sphere s5 = new Sphere(new Point(-400, -500, 700), 75, t);
		Sphere s6 = new Sphere(new Point(-420, 10, 650), 60, t);
		Sphere s7 = new Sphere(new Point(700, 20, 710), 80, t);
		Sphere s8 = new Sphere(new Point(-100, -50, 600), 50, t);
		Sphere s9 = new Sphere(new Point(270, 140, 640), 20, t);
		Sphere s10 = new Sphere(new Point(-320, -90, 640), 20, t);
		Sphere s11 = new Sphere(new Point(480, 360, 680), 30, t);
		Sphere s12 = new Sphere(new Point(660, 160, 680), 30, t);
		Sphere s13 = new Sphere(new Point(-580, -260, 680), 30, t);
		Sphere s14 = new Sphere(new Point(0, 0, 1000), 400, Sphere.MIRROR);
		
		s1.setOd(NColor.LIGHT_GRAY);
		s2.setOd(NColor.ORANGE);
		s3.setOd(NColor.GREEN);
		s4.setOd(NColor.YELLOW);
		s5.setOd(NColor.CYAN);
		s6.setOd(NColor.RED);
		s8.setOd(NColor.YELLOW);
		s7.setOd(NColor.GRAY);
		s9.setOd(NColor.BLUE);
		s10.setOd(NColor.ORANGE);
		s11.setOd(NColor.DARK_GRAY);
		s12.setOd(NColor.PINK);
		s13.setOd(NColor.MAGENTA);
		s14.setOd(new NColor(.74f,.84f,.3f));
		
		objects.add(s1);
		objects.add(s2);
		objects.add(s3);
		objects.add(s4);
		objects.add(s5);
		objects.add(s6);
		objects.add(s7);
		objects.add(s9);
		objects.add(s10);
		objects.add(s11);
		objects.add(s12);
		objects.add(s13);
		objects.add(s14);
		
		LightSource l0 = new LightSource(
				new Point(0, 0, 0), new NColor(1f,1f,1f));
		LightSource l1 = new LightSource(
				new Point(500, 400, 300), new NColor(.9f,.9f,.9f));
		LightSource l2 = new LightSource(
				new Point(0, 0, 1500), new NColor(1f,1f,1f));
		
		lights.add(l0);
		lights.add(l1);
		//lights.add(l2);
	}
	
	public NColor rayTracing(Ray rp, int deep, Object avoid) {

		if (deep == 0) {
			return NColor.BLACK;
		}
		double tmin = Double.MAX_VALUE;
		Sphere sphere = null;
		for (Object object: objects) {
			if (object == avoid) continue;
			double t = closest(object.calcIntersectn(rp)); 
			if (!Double.isNaN(t) && tmin > t) {
				tmin = t;
				sphere = (Sphere) object;
			}
		}
		if (sphere != null) {
			NColor localColor = phong(rp, sphere, tmin);
			if (sphere.getType() == Sphere.SOLID) {
				return localColor;
			}
			
			Point pi = rp.getPoint(tmin);
			Vector N = pi.sub(sphere.getCenter()).normalize();
			Vector I = rp.getRD();
			if (sphere.getType() == Sphere.MIRROR) {
				
				double beta = 2 * N.dot(I);
				Vector R = I.sub(N.mul(beta));
				Ray mirrorRay = new Ray(pi, R);
				NColor mirrorColor = rayTracing(mirrorRay, deep - 1, sphere);
				return add(localColor, mirrorColor);
						
			} else if (sphere.getType() == Sphere.GLASS) {
				
				//improvement
				float nit = na / sphere.getNb();
				double nit_2 = nit * nit;
				double cosi = N.dot(I.mul(-1));
				double cosi_2 = cosi * cosi;
				
				double d = nit_2 * (cosi_2 - 1) + 1;
				if (d < 0) {
					double beta = 2 * N.dot(I);
					Vector R = I.sub(N.mul(beta));
					Ray mirrorRay = new Ray(pi, R);
					NColor mirrorColor = rayTracing(mirrorRay, deep - 1, sphere);
					return add(localColor, mirrorColor);
				}
				
				double beta = nit * cosi - Math.sqrt(d);
				Vector T = I.mul(nit).add(N.mul(beta));
				Ray transRay = new Ray(pi, T);
				NColor transColor = rayTracing(transRay, deep - 1, sphere);
				return add(localColor, transColor);
			}
			
		}
		return NColor.BLACK;
	}
	
	private NColor phong(Ray rp, Sphere sphere, double tmin) {

		//if (tmin < 0) throw new UnsupportedOperationException();
		Point pi = rp.getPoint(tmin);
		Vector N = pi.sub(sphere.getCenter()).normalize();
		Vector V = rp.getRD().mul(-1);
		
		float odr = sphere.getOd().getR();
		float odg = sphere.getOd().getG();
		float odb = sphere.getOd().getB();
		float ks = sphere.getKs();
		float kd = sphere.getKd();
		int n = sphere.getN();

		double ir = 0;
		double ig = 0;
		double ib = 0;
		for (LightSource li: lights) {
			Vector l = li.getPosition().sub(pi);
			Vector L = l.normalize();
			double NxL = N.dot(L);
			if (NxL > 0 && !existShadow(sphere, new Ray(pi, l))) {
				Vector I = L.mul(-1);
				double beta = 2 * N.dot(I);
				Vector R = I.sub(N.mul(beta));
				
				float ilr = li.getIl().getR();
				float ilg = li.getIl().getG();
				float ilb = li.getIl().getB();
				
				double RxV = R.dot(V);
				if (RxV < 0) RxV = 0.1;
				double RxV_n = pow(RxV, n);
				ir += ilr*kd*odr*NxL + ilr*ks*RxV_n;
				ig += ilg*kd*odg*NxL + ilg*ks*RxV_n;
				ib += ilb*kd*odb*NxL + ilb*ks*RxV_n;	
			}
		}
		
		ir += ia.getR() * ka * odr;
		ig += ia.getG() * ka * odg;
		ib += ia.getB() * ka * odb;
		
		return new NColor(floor(ir), floor(ig), floor(ib));
	}
	
	public float floor(double x) {
		
		if (x < 0) {
			throw new IllegalArgumentException();
		} else {
			return x > 1.0 ? 1.0f : (float)x;
		}
	}
	
	public boolean existShadow(Sphere sphere, Ray rs) {
		
		for (Object object : objects) {
			if (object != sphere) {
				double t = closest(object.calcIntersectn(rs));
				if (!Double.isNaN(t) && 0 < t && t < 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public double closest(Intersection i) {

		if (i == null){
			return Double.NaN;
		} else {
			if (i.roots().length > 2) 
				throw new UnsupportedOperationException();
			Double x = null;
			for (double r: i.roots()) {
				if (r >= 0) {
					if (x == null || r < x) {
						x = r;
					}
				}
			}
			return (x == null)? Double.NaN : x;
		}
	}
	
	public double abs(double x) {
		
		return (x < 0)? -x : x;
	}
	
	public double pow2(double x) {
		
		return x * x;
	}
	
	public NColor add(NColor a, NColor b) {
		
		return new NColor(
				floor(a.getR() + b.getR()),
				floor(a.getG() + b.getG()),
				floor(a.getB() + b.getB()));
	}
}

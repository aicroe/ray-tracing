package org.example;

import org.example.tracer.RayTracer;

public class Main {
    public static void main(String[] args) {
        RayTracer rt = new RayTracer();
        rt.init();
        rt.setTitle("Ray Tracing - Spheres");
        rt.setDefaultCloseOperation(RayTracer.EXIT_ON_CLOSE);
        rt.setVisible(true);
    }
}
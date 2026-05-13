package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.noise;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NoiseGenerator {

    private final Random random = new Random();

    public double addGaussianNoise(double value, double stdDev) {
        return value + (random.nextGaussian() * stdDev);
    }

    public double addPerlinNoise(double value, double amplitude, long seed) {
        double noise = simplePerlin(seed * 0.01);
        return value + (noise * amplitude);
    }

    private double simplePerlin(double x) {
        int xi = (int) Math.floor(x);
        double xf = x - xi;

        double u = fade(xf);

        int a = hash(xi);
        int b = hash(xi + 1);

        return lerp(u, grad(a, xf), grad(b, xf - 1));
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private double grad(int hash, double x) {
        return (hash & 1) == 0 ? x : -x;
    }

    private int hash(int x) {
        x = ((x >> 16) ^ x) * 0x45d9f3b;
        x = ((x >> 16) ^ x) * 0x45d9f3b;
        x = (x >> 16) ^ x;
        return x;
    }
    
}
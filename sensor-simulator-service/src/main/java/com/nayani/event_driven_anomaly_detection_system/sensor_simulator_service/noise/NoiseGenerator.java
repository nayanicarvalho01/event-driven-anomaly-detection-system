package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.noise;

import org.springframework.stereotype.Component;

@Component
public class NoiseGenerator {


    public double addGaussianNoise(double value, double stdDev) {
        return value + (random.nextGaussian() * stdDev);
    }

    public double addPerlinNoise(double value, double amplitude, long seed) {
        return value + (noise * amplitude);

    }
}
    
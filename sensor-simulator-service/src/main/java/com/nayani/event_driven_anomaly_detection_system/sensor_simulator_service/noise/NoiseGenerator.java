package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.noise;

import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.pipeline.JNoise;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NoiseGenerator {

    private final Random random;
    private final JNoise perlinNoise;

    public NoiseGenerator() {
        this.perlinNoise = JNoise.newBuilder()
                .perlin(NoiseSourceBuilder.PerlinNoiseBuilder::new)
                .frequency(0.01)
                .seed(1234)
                .build();
    }

    //Adiciona variação aleatória seguindo uma distribuição normal (curva de sino)
    public double addGaussianNoise(double value, double stdDev) {
        return value + (random.nextGaussian() * stdDev);
    }

    //Gera ruído suave e contínuo (não tem saltos bruscos)
    public double addPerlinNoise(double value, double amplitude, long seed) {
        double noise = perlinNoise.evaluateNoise(seed); // retorna -1 a 1
        return value + (noise * amplitude);

    }
}

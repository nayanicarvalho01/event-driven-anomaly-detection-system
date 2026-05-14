package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl;

import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.Scenario;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorFailureScenario implements Scenario {

    private boolean active = false;
    private FailureType failureType = FailureType.STUCK;
    private double stuckValue;
    private final Random random = new Random();

    public enum FailureType {
        STUCK,           // Valor travado
        ERRATIC,         // Oscilação violenta
        OUT_OF_RANGE,    // Valores absurdos
        TIMEOUT          // Não responde (implementado em outro lugar)
    }

    public void start() {
        this.active = true;
    }

    public void start(FailureType type) {
        this.active = true;
        this.failureType = type;
    }

    public void stop() {
        this.active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public double applyToPressure(double baseline) {
        if (!active) return baseline;

        switch (failureType) {
            case STUCK:
                if (stuckValue == 0) {
                    stuckValue = baseline;
                }
                return stuckValue;

            case ERRATIC:
                double erraticNoise = random.nextGaussian() * (baseline * 0.5);
                return baseline + erraticNoise;

            case OUT_OF_RANGE:
                int choice = random.nextInt(3);
                switch (choice) {
                    case 0: return -999.0;
                    case 1: return Double.NaN;
                    case 2: return 999999.0;
                }

            default:
                return baseline;
        }
    }

    @Override
    public double applyToFlow(double baseline) {
        if (!active) return baseline;

        switch (failureType) {
            case STUCK:
                if (stuckValue == 0) {
                    stuckValue = baseline;
                }
                return stuckValue;

            case ERRATIC:
                double erraticNoise = random.nextGaussian() * (baseline * 0.5);
                return baseline + erraticNoise;

            case OUT_OF_RANGE:
                int choice = random.nextInt(3);
                switch (choice) {
                    case 0: return -999.0;
                    case 1: return Double.NaN;
                    case 2: return 999999.0;
                }

            default:
                return baseline;
        }
    }

    @Override
    public double applyToTemperature(double baseline) {
        if (!active) return baseline;

        switch (failureType) {
            case STUCK:
                if (stuckValue == 0) {
                    stuckValue = baseline;
                }
                return stuckValue;

            case ERRATIC:
                double erraticNoise = random.nextGaussian() * (baseline * 0.3);
                return baseline + erraticNoise;

            case OUT_OF_RANGE:
                int choice = random.nextInt(3);
                switch (choice) {
                    case 0: return -273.0;
                    case 1: return Double.NaN;
                    case 2: return 500.0;
                }

            default:
                return baseline;
        }
    }

    public void setFailureType(FailureType type) {
        this.failureType = type;
        this.stuckValue = 0;
    }

    public FailureType getFailureType() {
        return failureType;
    }
}
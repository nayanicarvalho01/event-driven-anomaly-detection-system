package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl;

import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.Scenario;
import org.springframework.stereotype.Component;

@Component
public class LeakScenario implements Scenario {

    private long startTime;
    private boolean active = false;
    private double leakRate = 0.05;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.active = true;
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

        long elapsed = System.currentTimeMillis() - startTime;
        double minutes = elapsed / 60000.0;

        double reduction = 1.0 - (leakRate * minutes);
        return baseline * Math.max(reduction, 0.5);
    }
    @Override
    public double applyToFlow(double baseline){
        if(!active) return baseline;

        return applyToPressure(baseline) * 0.5;
    }
    @Override
    public double applyToTemperature(double baseline) {
        return baseline;
    }

    public void setLeakRate(double rate) {
        this.leakRate = rate;
    }
}

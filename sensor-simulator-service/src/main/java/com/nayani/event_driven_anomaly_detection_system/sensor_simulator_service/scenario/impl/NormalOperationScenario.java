package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl;

import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.Scenario;
import org.springframework.stereotype.Component;

@Component
public class NormalOperationScenario implements Scenario {

    @Override
    public double applyToPressure(double baseline){
        return baseline;
    }

    @Override
    public double applyToFlow(double baseline){
        return baseline;
    }

    @Override
    public double applyToTemperature(double baseline) {
        return baseline;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

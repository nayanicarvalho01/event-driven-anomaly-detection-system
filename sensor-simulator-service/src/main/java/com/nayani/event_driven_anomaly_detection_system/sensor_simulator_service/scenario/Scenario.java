package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario;

public interface Scenario {
    double applyToPressure(double baseline);
    double applyToFlow(double baseline);
    double applyToTemperature(double baseline);
    boolean isActive();
}
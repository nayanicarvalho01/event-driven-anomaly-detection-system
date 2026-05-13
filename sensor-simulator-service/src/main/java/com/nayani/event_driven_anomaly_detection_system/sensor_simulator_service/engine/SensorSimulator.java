package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.engine;

import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.model.PipelineSegment;
import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.model.SensorEvent;
import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.Scenario;

public interface SensorSimulator {
    SensorEvent generateReading(PipelineSegment segment, Scenario scenario);
}

package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SensorEvent {

    private String sensorId;
    private SensorType sensorType;
    private double value;
    private long timestemp;
    private String segment;
    private Map<String, String> metadata;

}

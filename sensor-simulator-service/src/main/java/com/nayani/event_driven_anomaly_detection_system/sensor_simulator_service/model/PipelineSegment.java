package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.model;

import lombok.Data;

@Data
public class PipelineSegment {

    private String segmentId;
    private double baselinePressure;
    private double baselineFlow;
    private double baselineTemp;
}

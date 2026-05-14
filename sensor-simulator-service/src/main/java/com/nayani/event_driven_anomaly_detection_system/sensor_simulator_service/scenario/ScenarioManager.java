package com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario;

import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl.LeakScenario;
import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl.NormalOperationScenario;
import com.nayani.event_driven_anomaly_detection_system.sensor_simulator_service.scenario.impl.SensorFailureScenario;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioManager {

    private static final Logger log = LoggerFactory.getLogger(ScenarioManager.class);

    @Autowired
    private NormalOperationScenario normalScenario;

    @Autowired
    private LeakScenario leakScenario;

    @Autowired
    private SensorFailureScenario sensorFailureScenario;

    private Scenario currentScenario;

    @PostConstruct
    public void init() {
        log.info("Inicializando ScenarioManager com cenário Normal");
        currentScenario = normalScenario;
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }

    public void activateScenario(String scenarioName) {
        log.info("Ativando cenário: {}", scenarioName);

        // Desativa cenário anterior se necessário
        if (currentScenario instanceof LeakScenario) {
            ((LeakScenario) currentScenario).stop();
        }
        if (currentScenario instanceof SensorFailureScenario) {
            ((SensorFailureScenario) currentScenario).stop();
        }

        switch(scenarioName.toLowerCase()) {
            case "normal":
                currentScenario = normalScenario;
                break;

            case "leak":
                leakScenario.start();
                currentScenario = leakScenario;
                break;

            case "sensor-failure":
                sensorFailureScenario.start();
                currentScenario = sensorFailureScenario;
                break;

            default:
                log.warn("Cenário desconhecido: {}. Mantendo cenário atual.", scenarioName);
        }
    }

    public void activateLeakWithRate(double rate) {
        log.info("Ativando cenário de leak com taxa: {}%/min", rate * 100);
        leakScenario.setLeakRate(rate);
        leakScenario.start();
        currentScenario = leakScenario;
    }

    public void resetToNormal() {
        log.info("Resetando para operação normal");

        if (currentScenario instanceof LeakScenario) {
            ((LeakScenario) currentScenario).stop();
        }
        if (currentScenario instanceof SensorFailureScenario) {
            ((SensorFailureScenario) currentScenario).stop();
        }

        currentScenario = normalScenario;
    }

    public String getCurrentScenarioName() {
        if (currentScenario instanceof NormalOperationScenario) {
            return "normal";
        } else if (currentScenario instanceof LeakScenario) {
            return "leak";
        } else if (currentScenario instanceof SensorFailureScenario) {
            return "sensor-failure";
        }
        return "unknown";
    }
}
package common.Managers;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class ScenarioManager {

    private static Scenario currentScenario;
    public static final Logger logger = LogManager.getLogger(ScenarioManager.class);


    private ScenarioManager() {
    }

    public static Scenario getScenario() {
        return currentScenario;
    }

    public static void setScenario(Scenario scenario) {
        currentScenario = scenario;
    }


}
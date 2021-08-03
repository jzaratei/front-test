package utils;

import common.Managers.PlatformManager;
import common.Managers.PropertiesManager;
import common.Managers.ScenarioManager;
import common.exceptions.ExceptionController;
import common.selenium.DriverController;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @BeforeClass
    public static void beforeClass() {
        LOGGER.info("/-----------------------Execution Starts-------------------------------/");
        ExceptionController.regHookFail(Assertions::fail);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        LOGGER.info("/----------------Scenario: " + scenario.getName() + " started-----------------/");
        ScenarioManager.setScenario(scenario);
        PlatformManager.launchBrowser("chrome");
    }

    @AfterStep
    public void afterStep() {
        LOGGER.info("Moving to the next step...");
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOGGER.info("/--------Scenario " + scenario.getName() + " Ended. Test status: "+ scenario.getStatus() +"----/");
        DriverController.instance.stopWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        LOGGER.info("/-------------------------------Execution End--------------------------------/");
        DriverController.instance.stopWebDriver();
    }

}


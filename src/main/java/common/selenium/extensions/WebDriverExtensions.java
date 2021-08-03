package common.selenium.extensions;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import static common.selenium.Driver.browser;


public class WebDriverExtensions {

    private static final Logger Logger = LogManager.getLogger(WebDriverExtensions.class.getName());

    public static void getScreenshot(Scenario scenario) {
        Logger.info("Getting screenshot...");
        try {
            final byte[] screenshot = ((TakesScreenshot) browser()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "");
        } catch (Exception e) {
            Logger.error("It's not possible take an screenshot", e);
        }
    }


}

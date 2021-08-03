package common.selenium;

import common.selenium.drivers.ChromeWebDriver;
import common.selenium.drivers.FirefoxWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverController {

    public static DriverController instance = new DriverController();

    public RemoteWebDriver webDriver;

    private static final Logger Logger = LogManager.getLogger(DriverController.class.getName());

    public void startChrome(String arg) {
        if(instance.webDriver != null) return;
        instance.webDriver = ChromeWebDriver.loadChromeDriver(arg);
    }

    public void startFirefox(String arg) {
        if(instance.webDriver != null) return;
        instance.webDriver = FirefoxWebDriver.loadFirefoxDriver(arg);
    }

    public void stopWebDriver() {
        if (instance.webDriver == null) return;
        try {
            Logger.debug("Quitting web driver...");
            instance.webDriver.quit();
        } catch (Exception e) {
            Logger.error("A problem occurred while trying to stop de driver.", e);
        }
        instance.webDriver = null;
        Logger.debug("WebDriver stopped");
    }
}
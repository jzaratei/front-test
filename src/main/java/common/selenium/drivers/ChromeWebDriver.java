package common.selenium.drivers;


import common.exceptions.ExceptionController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeWebDriver {

    private static RemoteWebDriver driver;

    private static final Logger Logger = LogManager.getLogger(ChromeWebDriver.class.getName());

    private static void setupChromeDriver() {
        Logger.debug("Setting up chrome driver...");
        WebDriverManager.chromedriver().setup();
    }

    public static RemoteWebDriver loadChromeDriver(String chromeArgument) {
        try {
            setupChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments(chromeArgument);
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while trying to load chromedriver: ", e);
        }
        Logger.debug("Window size: " + driver.manage().window().getSize());
        return driver;
    }

}
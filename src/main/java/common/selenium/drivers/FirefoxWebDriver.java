package common.selenium.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxWebDriver {

    private static RemoteWebDriver driver;

    private static void setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    public static RemoteWebDriver loadFirefoxDriver(String firefoxArgument) {
        setupFirefoxDriver();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(firefoxArgument);
        driver = new FirefoxDriver(options);
        return driver;
    }
}
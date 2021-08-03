package common.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

    public static RemoteWebDriver browser() {
        return DriverController.instance.webDriver;
    }
}
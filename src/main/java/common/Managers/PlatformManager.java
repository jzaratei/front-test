package common.Managers;

import common.exceptions.ExceptionController;
import common.selenium.DriverController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlatformManager {


    public static Logger Logger = LogManager.getLogger(PlatformManager.class.getName());

    public static void launchBrowser(String browser){
        Logger.info("Launching browser over " + browser);
        String browserSelected = System.getProperty("browser");
         try {
             if (browserSelected==null) browserSelected = browser;
             assert browserSelected != null;
             if (browser.equalsIgnoreCase("chrome"))
                 DriverController.instance.startChrome("start-maximized");
             else if (browser.equalsIgnoreCase("firefox")) DriverController.instance.startFirefox("");
             else ExceptionController.hookFail("Browser no available: --> " + browserSelected);
         }catch (Exception e){
             ExceptionController.hookFail("A problem occurred while setting up the drivers.", e);
         }
        Logger.info("*********************Browser chosen: " + browserSelected + " **********************");
    }
}

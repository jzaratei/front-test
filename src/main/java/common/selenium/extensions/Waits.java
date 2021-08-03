package common.selenium.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebElementExtensions.getByFromElement;


public class Waits {
    
    private static final Logger logger = LogManager.getLogger(Waits.class.getName());
    private static final int sec = 30;

    public static WebDriverWait weWait(WebElement element, String condition) {
        logger.debug("Waiting " + sec + " seconds until " + condition + " condition expected.");
        WebDriverWait wait = new WebDriverWait(browser(), sec);
        wait.withMessage(condition + " -> " + getByFromElement(element) + "---> " + sec + " Seconds Timeout! <---");
        return wait;
    }

    public static WebDriverWait weWait(int sec, int sleepInMillis) {
        logger.debug("Waiting " + sec + " seconds until condition expected.");
        waitMillis(sleepInMillis);
        return new WebDriverWait(browser(), sec, sleepInMillis);
    }

    public static WebDriverWait weWait(WebElement element, String condition, int sec) {
        logger.debug("Waiting " + sec + " seconds until " + condition + " condition expected.");
        WebDriverWait wait = new WebDriverWait(browser(), sec);
        wait.withMessage(condition + " -> " + getByFromElement(element) + "---> " + sec + " Seconds Timeout! <---");
        return wait;
    }

    public static WebDriverWait weWait(int sec) {
        logger.debug("Waiting " + sec + " seconds until condition expected.");
        return new WebDriverWait(browser(), sec);
    }


    public static void waitMillis(int millis) {
        try {
            logger.debug("Waiting " + millis + " milliseconds.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.trace(e);
        }
    }

}

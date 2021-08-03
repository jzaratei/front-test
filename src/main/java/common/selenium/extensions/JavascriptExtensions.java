package common.selenium.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebElementExtensions.getByFromElement;


public class JavascriptExtensions {

    private static final Logger logger = LogManager.getLogger(JavascriptExtensions.class.getName());

    public static void switchToTab(int tab){
        ArrayList<String> tabs = new ArrayList<>(browser().getWindowHandles());
        browser().switchTo().window(tabs.get(tab));
    }

    public static void weScrollIntoViewElement(WebElement element){
        logger.info("weScrollIntoViewElement: (Element: " + getByFromElement(element) + ")");
        try{
            JavascriptExecutor js = browser();
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);
        }
        catch(Exception e){
            logger.warn("A problem occurred while scrolling into element " + getByFromElement(element) + " Cause: "
                    + e.getMessage().split("\n")[0]);
        }
    }

}

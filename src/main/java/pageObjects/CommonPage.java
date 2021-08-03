package pageObjects;

import common.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebElementExtensions.*;

public class CommonPage extends Page {

    public static Logger logger = LogManager.getLogger(CommonPage.class.getName());

    @FindBy(id = "AcceptReload")
    protected WebElement btnAcceptCookies;

    public void navigateToUrl(URL url) {
        browser().navigate().to(url);
        logger.info("Accessing to " + url);
    }

    public void checkAndClickCookies() {
        logger.info("Allow all cookies...");
        if(isElementVisible(btnAcceptCookies, 5)){
            weClick(btnAcceptCookies);
        }else{
            logger.warn("cookies side bar never appear after 5 seconds waiting.");
        }
    }
}

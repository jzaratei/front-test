package pageObjects;

import common.Page;
import common.selenium.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebElementExtensions.*;

public class CommonPage extends Page {

    public static Logger logger = LogManager.getLogger(CommonPage.class.getName());

    @FindBy(css = "button[title='Aceptar']")
    protected WebElement btnAcceptCookies;

    @FindBy(css = "iframe[title='SP Consent Message']")
    protected WebElement iframeCookies;

    @FindBy(css = "span.iyvn34-0.bYIjtl")
    protected WebElement btnCloseSubscribe;

    public void navigateToUrl(URL url) {
        browser().navigate().to(url);
        logger.info("Accessing to " + url);
    }

    public void checkAndClickCookies() {
        logger.info("Allow all cookies...");
        waitForFrameToBeAvailableAndSwitch(iframeCookies);
        if(isElementVisible(btnAcceptCookies, 5)){
            weClick(btnAcceptCookies);
        }else{
            logger.warn("cookies side bar never appear after 5 seconds waiting.");
        }
        browser().switchTo().defaultContent();
    }

    public void checkAndCloseSubscribe() {
        logger.info("close subscribe message...");
        if(isElementVisible(btnCloseSubscribe, 5)){
            weClick(btnCloseSubscribe);
        }else{
            logger.warn("subscribe message never appear after 5 seconds waiting.");
        }
    }


}

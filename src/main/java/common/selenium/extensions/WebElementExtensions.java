package common.selenium.extensions;

import common.exceptions.ExceptionController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static common.selenium.Driver.browser;
import static common.selenium.Settings.weHighlightedColour;


public class WebElementExtensions {

    private static final Logger logger = LogManager.getLogger(WebElementExtensions.class.getName());

    /************************Actions************************/

    public static void weElementToBeClickable(WebElement element) {
        logger.debug("weElementClickable: (Element: " + getByFromElement(element) + ")");
        Waits.weWait(element, "elementToBeClickable").until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void weHighlightElement(WebElement element) {
        logger.debug("weHighlightElement: (Element: " + getByFromElement(element) + ")");
        JavascriptExecutor js = browser();
        js.executeScript(weHighlightedColour, element);
    }

    public static void weClick(WebElement element) {
        weElementToBeClickable(element);
        weHighlightElement(element);
        logger.info("weClick: (Element: " + getByFromElement(element) + ")");
        element.click();
    }


    public static void weSendKeys(WebElement element, String text, boolean clearFirst) {
        if (clearFirst) {
            weClear(element);
        }else weElementIsVisible(element);
        logger.info("weSendKeys: (Element: " + getByFromElement(element) + " | Keys: " + text + ")");
        element.sendKeys(text);
    }

    public static void weClear(WebElement element){
        logger.info(" weClear: (Element: " + getByFromElement(element) + ")");
        String inputValue;
        weElementIsVisible(element);
        element.clear();
        inputValue = weGetAttribute(element, "value");
        if (!inputValue.equals("")){
            try{
                inputValue = weGetAttribute(element, "value");
                while (!inputValue.equals("")){
                    element.sendKeys(Keys.BACK_SPACE);
                    inputValue = weGetAttribute(element, "value");
                }
            }catch (Exception e){
                ExceptionController.hookFail("A problem occurred while clearing the value of: "
                        + getByFromElement(element), e);
            }
        }

    }

    public static String weGetAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /************************Visibility************************/

    public static void weElementIsVisible(WebElement element) {
        logger.debug("weVisibilityOf: (Element: " + getByFromElement(element) + ")");
        Waits.weWait(element, "visibilityOf").until(ExpectedConditions.visibilityOf(element));
    }

    public static void weElementIsVisible(WebElement element, int sec) {
        logger.debug("weVisibilityOf: (Element: " + getByFromElement(element) + ")");
        Waits.weWait(sec).until(ExpectedConditions.visibilityOf(element));
    }


    public static boolean isElementVisible(WebElement element, int sec) {
        try {
            ExpectedCondition<Boolean> elementIsVisible = arg0 -> {
                try {
                    weElementIsVisible(element, sec);
                    logger.debug("The element: " + getByFromElement(element) + " is visible.");
                    return true;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    logger.warn("The element: " + getByFromElement(element) + " is not visible. Cause: "
                            + e.getMessage().split("\n")[0]);
                    return false;
                }
            };
            Waits.weWait(element, "isDisplayed", sec).until(elementIsVisible);
        }catch (TimeoutException e){
            logger.warn("Expected element not visible. Cause: " + e.getMessage().split("\n")[0]);
            return false;
        }
        return true;
    }

    public static String waitAndGetAttributeChanged(WebElement element, String initialValue) {
        logger.debug("weAttributeIsChanged: (Element: " + getByFromElement(element) + ") -Initial value: " + initialValue);
        ExpectedCondition<Boolean> attributeIsChanged = (arg0) -> {
            try{
                String actualValue = getValue(element);
                logger.debug("weAttributeIsChanged: Actual value: " + actualValue);
                return (!actualValue.equals(initialValue) && !actualValue.equals(""));
            }catch (Exception var3){
                return false;
            }
        };
        Waits.weWait(element, "attributeIsChanged",5).until(attributeIsChanged);
        return getValue(element);
    }

    private static String getValue(WebElement element){
        String newValue = element.getText();
        if (newValue.equals("")) newValue = element.getAttribute("data-value");
        return newValue;
    }

    public static void waitForFrameToBeAvailableAndSwitch(WebElement element) {
        Waits.weWait(5, 3000).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }


    protected static By getByFromElement(WebElement element) {
        By by;
        String[] selectorWithValue;
        try {
            if (element.toString().contains("By.")){
                selectorWithValue= element.toString().split("By.")[1].replace("'", "").split(":");
            }else {
                selectorWithValue= (element.toString().split("->")[1]
                        .replaceFirst("(?s)(.*)]", "$1" + "")).split(":");
            }

            String selector = selectorWithValue[0].trim();
            String value = selectorWithValue[1].trim();

            switch (selector) {
                case "id":
                    by = By.id(value);
                    break;
                case "className":
                case "class name":
                    by = By.className(value);
                    break;
                case "tagName":
                    by = By.tagName(value);
                    break;
                case "xpath":
                    by = By.xpath(value);
                    break;
                case "css selector":
                case "cssSelector":
                    by = By.cssSelector(value);
                    break;
                case "linkText":
                    by = By.linkText(value);
                    break;
                case "name":
                    by = By.name(value);
                    break;
                case "partialLinkText":
                    by = By.partialLinkText(value);
                    break;
                default:
                    throw new IllegalStateException("locator : " + selector + " not found!!!");
            }
        }catch (Exception e){
            logger.warn("A problem occurred while getting By from element. ---> " + e.getLocalizedMessage());
            return By.cssSelector("");
        }
        return by;
    }

}

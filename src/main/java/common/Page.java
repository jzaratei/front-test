package common;

import org.openqa.selenium.support.PageFactory;
import pageObjects.CommonPage;

import static common.selenium.Driver.browser;


public class Page {

    public static <T extends CommonPage> T instanceOf(Class<T> clazz) {
        return PageFactory.initElements(browser(), clazz);
    }

}
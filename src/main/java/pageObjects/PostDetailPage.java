package pageObjects;

import common.exceptions.ExceptionController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebElementExtensions.*;

public class PostDetailPage extends CommonPage {

    @FindBy(css = "iframe.sc-1evupmb-1.isdTrz.js_comments-iframe")
    protected WebElement iframeReply;

    @FindBy(css = "button.Button__ButtonWrapper-j48i5d-2")
    protected WebElement btnReply;


    @FindBy(css = "#uid1")
    protected WebElement txtEditBox;

    public void insertAReply(String text){
        try{
            waitForFrameToBeAvailableAndSwitch(iframeReply);
            weClick(btnReply);
            Thread.sleep(3000);
            weElementIsVisible(txtEditBox);
            weSendKeys(txtEditBox, text, false);
            Thread.sleep(3000);
            browser().switchTo().defaultContent();
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while inserting a reply", e);
        }

    }

}

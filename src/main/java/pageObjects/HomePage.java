package pageObjects;

import common.exceptions.ExceptionController;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.selenium.extensions.WebElementExtensions.weClick;
import static common.selenium.extensions.WebElementExtensions.weSendKeys;

public class HomePage extends CommonPage {

    @FindBy(id = "search-bar")
    protected WebElement txtSearchBar;

    public void Search(String text){
        try {
            weClick(txtSearchBar);
            weSendKeys(txtSearchBar, text, true);
            txtSearchBar.sendKeys(Keys.RETURN);
            Thread.sleep(5000);
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while searching a post", e);
        }

    }

}

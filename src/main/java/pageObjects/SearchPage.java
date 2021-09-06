package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static common.selenium.extensions.WebElementExtensions.weClick;

public class SearchPage extends CommonPage {

    @FindBy(css = "div.sc-1xh12qx-2")
    protected List<WebElement> lnkPostNumberList;


    public void selectPost(int number){
        weClick(lnkPostNumberList.get(number));
    }

}

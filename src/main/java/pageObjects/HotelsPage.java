package pageObjects;

import common.exceptions.ExceptionController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.selenium.extensions.JavascriptExtensions.weScrollIntoViewElement;
import static common.selenium.extensions.WebElementExtensions.weElementIsVisible;

public class HotelsPage extends CommonPage {

    @FindBy(css = "ul.app-breadcrumbs__wrapper li:nth-child(3)")
    protected WebElement lblBreadCrumb;

    @FindBy(css = "h1.app-hero__title.app-heading.app-heading--light")
    protected WebElement lblTitleName;

    @FindBy(css = "h2.app-list__title.app-heading.app-heading--h2")
    protected WebElement lblListHotelsTitle;

    @FindBy(css = "div.app-thumb__address")
    protected WebElement lblThumbAddress;


    public void checkHotelPage(String hotel) {
        try {
                weElementIsVisible(lblTitleName);
                weElementIsVisible(lblBreadCrumb);
                Assertions.assertEquals(hotel.toUpperCase(), lblBreadCrumb.getText());
                weScrollIntoViewElement(lblListHotelsTitle);
                Assertions.assertEquals(hotel.toUpperCase(), lblTitleName.getText());
                Assertions.assertEquals("los mejores hoteles en " + hotel, lblListHotelsTitle.getText());
                weScrollIntoViewElement(lblThumbAddress);
                Assertions.assertTrue(lblThumbAddress.getText().contains(hotel));

        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while checking search results", e, true);
        }
    }

}

package pageObjects;

import common.entities.Offer;
import common.exceptions.ExceptionController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.JavascriptExtensions.weScrollIntoViewElement;
import static common.selenium.extensions.WebElementExtensions.*;

public class HotelDetailPage extends CommonPage {

    @FindBy(id = "iFrameResizer1")
    protected WebElement iFrameRating;

    @FindBy(css = "div.trustscore.size-m div.value")
    protected WebElement lblRating;

    @FindBy(css = "img[data-qa='tile_image_main']")
    protected WebElement imgHotelPhotos;

    @FindBy(css = "h1.lodging-name__name.variant-hotel-name")
    protected WebElement lblHotelName;

    @FindBy(css = "address.lodging-address.detail-header__lodging-address.variant-hotel-address")
    protected WebElement lblHotelLocation;


    @FindBy(css = "span.offer-price__price.variant-text-title-2")
    protected WebElement lblPrice;


    public void checkHotelDetail(Offer hotel) {
        logger.info("Checking hotel details...");
        try {
            isElementVisible(imgHotelPhotos, 10);
            waitForFrameToBeAvailableAndSwitch(iFrameRating);
            weElementIsVisible(lblRating);
            Assertions.assertEquals(hotel.getRating(), Double.parseDouble(lblRating.getText()));
            browser().switchTo().defaultContent();
            Assertions.assertEquals(hotel.getName(), lblHotelName.getText());
            weScrollIntoViewElement(lblPrice);
            Assertions.assertEquals(hotel.getPrice()
                    , Double.parseDouble(lblPrice.getText().replaceAll("[^0-9,]+", "")));

        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while checking search results", e, true);
        }
    }

    public void bookNow() {
    }
}

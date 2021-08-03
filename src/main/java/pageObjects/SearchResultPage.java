package pageObjects;

import common.Managers.ScenarioManager;
import common.exceptions.ExceptionController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static common.selenium.Driver.browser;
import static common.selenium.extensions.WebDriverExtensions.getScreenshot;
import static common.selenium.extensions.WebElementExtensions.*;

public class SearchResultPage extends CommonPage {

    @FindBy(css = "div[data-qa='quick_search_guest_selection']")
    protected WebElement tabGuestSelection;

    @FindBy(css = "h3[data-qa='typography']")
    protected WebElement lblHotelsFound;

    @FindBy(css = "div[data-qa='offer_tile_location']")
    protected WebElement lblOfferLocation;

    @FindBy(css = "#select-result-wrapper-1 h2[data-qa='typography']")
    protected WebElement lblNoHotelFound;


    public void checkResults(String destination, String guest) {
        try {
                Assertions.assertTrue(isHotelFound(), "Hotel not found!");
                weElementIsVisible(lblHotelsFound);
                Assertions.assertTrue(lblHotelsFound.getText().contains("encontrados"));
                weElementIsVisible(tabGuestSelection);
                Assertions.assertEquals(guest + " huéspedes", tabGuestSelection.getText());
                weElementIsVisible(lblOfferLocation);
                Assertions.assertTrue(lblOfferLocation.getText().contains(destination)
                        , "The destination description doesn't contains " + destination);

        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while checking search results", e, true);
        }
    }

    public boolean isHotelFound(){
        try {
            if (isElementVisible(lblNoHotelFound, 5)) {
                getScreenshot(ScenarioManager.getScenario());
                browser().navigate().refresh();
                Thread.sleep(5000);
            }
        }catch (Exception e){
            ExceptionController.hookFail("No hotels found!", true);
        }
        return !isElementVisible(lblNoHotelFound, 5);
    }

}

package stepsDefinition;

import common.Managers.OffersManager;
import common.entities.Offer;
import io.cucumber.java8.En;
import org.junit.jupiter.api.Assertions;
import pageObjects.HotelDetailPage;
import pageObjects.OffersPage;

import static common.Page.instanceOf;
import static common.selenium.extensions.JavascriptExtensions.switchToTab;

public class OffersSteps implements En {
    public OffersSteps() {

        Then("^I check offers page$", () -> {
            instanceOf(OffersPage.class).checkOffersPage();
        });

        And("^I order the offers by (.*)$", (String orderType) -> {
            if (orderType.equals("price") || orderType.equals("rating") || orderType.equals("stars")){
                instanceOf(OffersPage.class).orderBy(orderType);
                Assertions.assertTrue(instanceOf(OffersPage.class).isHotelFound(), "There's no hotels!");
                Assertions.assertTrue(OffersManager.checkListOrdered(instanceOf(OffersPage.class).getOfferList(), orderType),
                        "The offers list is not ordered by " +  orderType + " as expected");
            }
        });

        When("^I make a reservation for a (.*) star hotel$", (String stars) -> {
            Offer actualOffer = instanceOf(OffersPage.class).selectHotelByStars(stars);
            switchToTab(1);
            instanceOf(HotelDetailPage.class).checkHotelDetail(actualOffer);
            instanceOf(HotelDetailPage.class).bookNow();
        });
    }
}

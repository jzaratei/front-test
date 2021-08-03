package stepsDefinition;

import io.cucumber.java8.En;
import pageObjects.HotelsPage;

import static common.Page.instanceOf;

public class BestHotelSteps implements En {
    public BestHotelSteps() {

        Then("^I check (.*) best hotels$", (String destination) -> {
            instanceOf(HotelsPage.class).checkHotelPage(destination);
        });
    }
}

package stepsDefinition;

import common.Page;
import io.cucumber.java8.En;
import pageObjects.ReceptionPage;
import utils.Countries;

public class ReceptionSteps extends Page implements En {
    public ReceptionSteps() {

        When("^I select (.*) as country$", (Countries country) -> {
            instanceOf(ReceptionPage.class).selectCountry(country);
        });
    }
}

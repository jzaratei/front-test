package stepsDefinition;

import common.Page;
import io.cucumber.java8.En;
import pageObjects.HomePage;
import utils.Countries;

public class HomeSteps extends Page implements En {
    public HomeSteps() {

        Then("^I check home page for (.*) user$", (Countries country) -> {
            instanceOf(HomePage.class).checkHomeLanguage(country);
        });

        Then("^I select (.*) as recommended$", (String destination) -> {
            instanceOf(HomePage.class).selectRecommendedDestination(destination);
        });

        Then("^I go to (.*) page$", (String tab) -> {
            instanceOf(HomePage.class).selectHeaderTab(tab);
        });
    }
}

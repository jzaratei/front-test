package stepsDefinition;

import common.Page;
import io.cucumber.java8.En;
import pageObjects.HomePage;

public class HomeSteps extends Page implements En {
    public HomeSteps() {

        Then("^I search for (.*)$", (String text) -> {
            instanceOf(HomePage.class).Search(text);
        });
    }
}

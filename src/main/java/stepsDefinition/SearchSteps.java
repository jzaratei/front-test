package stepsDefinition;

import common.Page;
import io.cucumber.java8.En;
import pageObjects.SearchPage;
import pageObjects.SearchResultPage;

public class SearchSteps extends Page implements En {
    public SearchSteps() {
        Then("^I search for (.*) as destination$", (String destination) -> {
        });

        Then("^I search for (.*) between (.*) and (.*) for (.*) guest",
                (String destination, String dateIni, String dateFin, String number) -> {
            instanceOf(SearchPage.class).doSearch(destination, dateIni, dateFin, number);

        });

        And("^I check the results for (.*) and (.*) guest$", (String destination, String guest) -> {
            instanceOf(SearchResultPage.class).checkResults(destination, guest);
        });
    }
}

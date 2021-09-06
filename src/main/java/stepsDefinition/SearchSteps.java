package stepsDefinition;

import common.Page;
import common.exceptions.ExceptionController;
import io.cucumber.java8.En;
import pageObjects.SearchPage;

public class SearchSteps extends Page implements En {
    public SearchSteps() {

        And("^I select (\\d+) post$", (Integer arg0) -> {
            if (arg0 == 0) ExceptionController.hookFail("The post number must be higher than 1");
            instanceOf(SearchPage.class).selectPost(arg0-1);
        });
    }
}

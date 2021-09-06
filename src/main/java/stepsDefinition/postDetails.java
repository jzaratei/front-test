package stepsDefinition;

import common.Page;
import io.cucumber.java8.En;
import pageObjects.PostDetailPage;

public class postDetails extends Page implements En {
    public postDetails() {
        Then("^I write down a comment$", () -> {
            instanceOf(PostDetailPage.class).insertAReply("This is a test...");
        });
    }
}

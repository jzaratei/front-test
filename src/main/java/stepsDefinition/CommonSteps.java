package stepsDefinition;

import pageObjects.CommonPage;
import common.Managers.PropertiesManager;
import common.Page;
import io.cucumber.java8.En;

import java.net.URL;


public class CommonSteps  extends Page implements En {
    public CommonSteps() {

        Given("^I access to (.*) page$", (String web) -> {
            URL url = new URL(PropertiesManager.getInstance().getProperty(web));
            instanceOf(CommonPage.class).navigateToUrl(url);
            instanceOf(CommonPage.class).checkAndClickCookies();

        });
    }
}

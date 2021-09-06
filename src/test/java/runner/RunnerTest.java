package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import utils.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		plugin = {"junit:target/junit-cucumber-reports/cukejunit.xml", "json:target/json-cucumber-reports/cukejson.json", "rerun:reports/rerun.txt"},
		glue = {"stepsDefinition", "utils"},
		features = "src/test/resources/features",
		tags = "@test and @search"

)

public class RunnerTest extends Hooks {

}
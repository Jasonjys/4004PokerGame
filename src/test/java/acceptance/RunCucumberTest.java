package acceptance;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/acceptance", glue="stepDefinitions")
public class RunCucumberTest {
}

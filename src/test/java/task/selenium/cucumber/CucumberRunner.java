package task.selenium.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:build/cucumber/report"},
        tags = "@shoppingTest"
)
public class CucumberRunner {
}

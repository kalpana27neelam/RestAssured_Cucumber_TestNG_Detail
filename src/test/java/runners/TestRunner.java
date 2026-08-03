package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features="./src/test/resources/features/07_authentication.feature",
        glue = {"stepdefinitions","hooks"},
        monochrome = true,
        dryRun = false,
        plugin = {"pretty","summary"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

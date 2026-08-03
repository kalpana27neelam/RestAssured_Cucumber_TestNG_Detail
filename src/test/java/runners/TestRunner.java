package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features="./src/test/resources/features/10_chained_requests.feature",
        glue = {"stepdefinitions","hooks"},
        monochrome = true,
        dryRun = false,
        plugin = {"pretty","summary"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

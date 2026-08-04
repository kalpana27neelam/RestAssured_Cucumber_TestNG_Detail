package stepdefinitions;

import io.cucumber.java.en.Given;
import utils.TestContext;

public class Logging11Steps {

    private TestContext testContext;

    public Logging11Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("I enable full request and response logging")
    public void i_enable_full_request_and_response_logging() {
      testContext.setRequestSpecification(testContext.getRequestSpecification().log().all());
    }
}

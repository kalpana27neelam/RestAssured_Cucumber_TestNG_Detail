package stepdefinitions;

import io.cucumber.java.en.Given;
import utils.TestContext;

public class Authentication07Steps {

    private TestContext testContext;

    public Authentication07Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("I authenticate with basic auth username {string} and password {string}")
    public void i_authenticate_with_basic_auth_username_and_password(String username, String password) {
        testContext.setRequestSpecification(testContext.getRequestSpecification().auth().preemptive().basic(username, password));
    }

    @Given("I authenticate with bearer token {string}")
    public void i_authenticate_with_bearer_token(String bearer_token) {
       testContext.setRequestSpecification(testContext.getRequestSpecification().auth().oauth2(bearer_token));
    }
}

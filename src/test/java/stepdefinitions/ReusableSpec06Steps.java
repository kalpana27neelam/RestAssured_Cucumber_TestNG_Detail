package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.SpecBuilder;
import utils.TestContext;

import static io.restassured.RestAssured.given;

public class ReusableSpec06Steps {

    private TestContext testContext;

    public  ReusableSpec06Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("I use the reusable JSONPlaceholder spec")
    public void i_use_the_reusable_json_placeholder_spec() {
       testContext.setRequestSpecification(given().spec(SpecBuilder.jsonPlaceHolderSpec()));
    }

    @Given("I use the reusable httpbin spec")
    public void i_use_the_reusable_httpbin_spec() {
       testContext.setRequestSpecification(given().spec(SpecBuilder.httpBinSpec()));
    }

    @Then("the response should satisfy the default response spec")
    public void the_response_should_satisfy_the_default_response_spec() {
      testContext.getResponse().then().spec(SpecBuilder.defaultResponseSpec());
    }

}

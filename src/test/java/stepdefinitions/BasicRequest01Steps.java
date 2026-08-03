package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.TestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class BasicRequest01Steps {

    private TestContext testContext;

    public BasicRequest01Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("the base URI is {string}")
    public void the_base_uri_is(String baseURI) {

        RestAssured.baseURI=baseURI;
        testContext.setRequestSpecification(given().contentType(ContentType.JSON));

    }
    @When("I send a Get request to {string}")
    public void i_send_a_get_request_to(String path) {
        testContext.setResponse(testContext.getRequestSpecification().when().get(path));
        System.out.println("The response :"+testContext.getResponse().asPrettyString());

    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedCode) {
        assertThat(testContext.getResponse().getStatusCode(),equalTo(expectedCode));
        System.out.println("The response :"+testContext.getResponse().asPrettyString());
    }

    @Then("the response should be JSON array")
    public void the_response_should_be_json_array() {
        assertThat(testContext.getResponse().as(Object.class),instanceOf(java.util.List.class));
    }

    @Given("the request body is:")
    public void the_request_body_is(String jsonBody) {
        testContext.setRequestSpecification(testContext.getRequestSpecification().body(jsonBody));
    }
    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String path) {
        testContext.setResponse(testContext.getRequestSpecification().when().post(path));
        System.out.println("The response is :"+testContext.getResponse().asPrettyString());

    }

    @When("I send PUT request to {string}")
    public void i_send_put_request_to(String jsonBody) {

        testContext.setResponse(testContext.getRequestSpecification().when().put(jsonBody));
        System.out.println("The response is:"+testContext.getResponse().asPrettyString());

    }

    @When("I send a PATCH request to {string}")
    public void i_send_a_patch_request_to(String path) {
       testContext.setResponse(testContext.getRequestSpecification().when().patch(path));
       System.out.println("The response is:"+testContext.getResponse().asPrettyString());
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String path) {
        testContext.setResponse(testContext.getRequestSpecification().when().delete(path));
    }
}

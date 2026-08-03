package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class RequestBuilding02Steps {

    private TestContext testContext;

    public RequestBuilding02Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("I set query param {string} to {string}")
    public void i_set_query_param_to(String key, String value) {

        testContext.setRequestSpecification(testContext.getRequestSpecification().queryParam(key,value));

    }
    @Then("every post in the response should have userId {string}")
    public void every_post_in_the_response_should_have_user_id(String expectedUserId) {

        java.util.List<Integer> userIds=testContext.getResponse().jsonPath().getList("userId", Integer.class);
        System.out.println("The response is :"+testContext.getResponse().asPrettyString());
        assertThat(userIds,everyItem(equalTo(Integer.parseInt(expectedUserId))));

    }

    @When("I set path param {string} to {string}")
    public void i_set_path_param_to(String key, String value) {
        testContext.setRequestSpecification(testContext.getRequestSpecification().pathParams(key,value));
    }
    @Then("the json path {string} should equal {string}")
    public void the_json_path_should_equal(String jsonpath, String expectedValue) {

        System.out.println("jsonPath param received: [" + jsonpath + "]");
        String actualValue=testContext.getResponse().jsonPath().getString(jsonpath);
        System.out.println("actualValue :"+actualValue);
        assertThat(actualValue,equalTo(expectedValue));

    }

    @Given("I set header {string} to {string}")
    public void i_set_header_to(String key, String value) {
        testContext.setRequestSpecification(testContext.getRequestSpecification().header(key,value));
    }
}

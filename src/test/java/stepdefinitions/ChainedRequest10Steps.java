package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChainedRequest10Steps {

    private TestContext testContext;

    //local memory : values captured from one response , reused in a later step
    private final Map<String,Object> capturedValues=new HashMap<>();


    public ChainedRequest10Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }


    @Then("I capture the {string} from the response as {string}")
    public void i_capture_the_from_the_response_as(String field, String variableName) {

        capturedValues.put(variableName,testContext.getResponse().jsonPath().get(field));

    }
    @When("I send a GET request to {string} plus the captured value {string}")
    public void i_send_a_get_request_to_plus_the_captured_value(String pathPrefix, String variableName) {

        String fullpath=pathPrefix+capturedValues.get(variableName);
        testContext.setResponse(testContext.getRequestSpecification().when().get(fullpath));

    }
    @Then("the json path {string} should equal the captured value {string}")
    public void the_json_path_should_equal_the_captured_value(String json_path, String variableName) {
        Object actual=testContext.getResponse().jsonPath().get(json_path);
        assertThat(String.valueOf(actual),equalTo(String.valueOf(capturedValues.get(variableName))));
    }
}

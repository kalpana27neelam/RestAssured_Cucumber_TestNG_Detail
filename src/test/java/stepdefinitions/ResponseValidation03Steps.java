package stepdefinitions;

import io.cucumber.java.en.Then;
import utils.TestContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseValidation03Steps {

    private TestContext testContext;

    public ResponseValidation03Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Then("the response status line should contain {string}")
    public void the_response_status_line_should_contain(String expectedStatusCode) {
        System.out.println("The status line is :"+testContext.getResponse().getStatusLine());
        assertThat(testContext.getResponse().getStatusLine(),containsString(expectedStatusCode));
    }
    @Then("the response content type should be {string}")
    public void the_response_content_type_should_be(String expectedContentType) {
        System.out.println("content type is :"+testContext.getResponse().getContentType());
        assertThat(testContext.getResponse().getContentType(),equalTo(expectedContentType));
    }

    @Then("the response header {string} should contain {string}")
    public void the_response_header_should_contain(String headerName, String expectedHeaderValue) {

        System.out.println("The header is: "+testContext.getResponse().getHeader(headerName));
        assertThat(testContext.getResponse().getHeader(headerName),containsString(expectedHeaderValue));

    }

    @Then("the response time should be less than {int} ms")
    public void the_response_time_should_be_less_than_ms(Integer expectedresponseTime) {

        System.out.println("Response time is :"+testContext.getResponse().getTime());
        assertThat(testContext.getResponse().getTime(),lessThan(expectedresponseTime.longValue()));
    }



    @Then("the field {string} should be greater than {string}")
    public void the_field_should_be_greater_than(String json_path, String greaterThanValue) {
        int actualValue=testContext.getResponse().jsonPath().getInt(json_path);
        System.out.println("The field "+json_path+" value is :"+actualValue);
        assertThat(actualValue,greaterThan(Integer.valueOf(greaterThanValue)));
    }

    @Then("the field {string} should not be empty")
    public void the_field_should_not_be_empty(String json_path) {
        String actualValue=testContext.getResponse().jsonPath().getString(json_path);
        System.out.println("The field "+json_path+" value is :"+actualValue);
        assertThat(actualValue,not(emptyOrNullString()));
    }


}

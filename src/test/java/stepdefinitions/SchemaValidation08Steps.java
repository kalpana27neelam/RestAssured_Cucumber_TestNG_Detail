package stepdefinitions;

import io.cucumber.java.en.Then;
import utils.TestContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation08Steps {

    private TestContext testContext;

    public SchemaValidation08Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Then("the response should match the JSON Schema {string}")
    public void the_response_should_match_the_json_schema(String schemaClassPathLocation) {

        testContext.getResponse().then().assertThat().body(matchesJsonSchemaInClasspath(schemaClassPathLocation));

    }
}

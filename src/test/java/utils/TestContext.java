package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {

    private RequestSpecification requestSpecification;
    private Response response;

    /*
     * One instance is created PER SCENARIO by Cucumber's dependency injection.
     * Every step-def class that asks for this in its constructor gets the SAME
     * instance for that scenario - that's how a Given step's request reaches
     * the When step, and the When step's response reaches the Then step.
     */

    public RequestSpecification getRequestSpecification()
    {
        if (requestSpecification==null)
        {
            requestSpecification=io.restassured.RestAssured.given();
        }
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification)
    {
        this.requestSpecification=requestSpecification;
    }

    public Response getResponse()
    {
        return response;
    }

    public void setResponse(Response response)
    {
        this.response=response;
    }
}

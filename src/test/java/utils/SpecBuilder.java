package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

/*
Build baseUri/headers/content-type ONCE here and reuse across many tests ,
instead of repeating given().baseuri(..).contentType(..)  everywhere
 */
public class SpecBuilder {

    public static final String JSONPLACEHOLDER_BASE="https://jsonplaceholder.typicode.com";
    public static final String HTTPBIN_BASE="https://httpbin.org";

    public static RequestSpecification jsonPlaceHolderSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri(JSONPLACEHOLDER_BASE)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
    }

    public static RequestSpecification httpBinSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri(HTTPBIN_BASE)
                .log(LogDetail.URI)
                .build();
    }

    public static ResponseSpecification defaultResponseSpec()
    {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(5000L))
                .build();
    }
}

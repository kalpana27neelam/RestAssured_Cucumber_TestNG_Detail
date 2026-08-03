package stepdefinitions;

import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import utils.TestContext;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonPathExtraction04Steps {

    private TestContext testContext;

    //local store for values extracted in one step ,checked in a later step -
    //same idea as TestContext
    private final Map<String,Object> extractedValues= new HashMap<>();

    public JsonPathExtraction04Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Then("I extract the json path {string} as {string}")
    public void i_extract_the_json_path_as(String json_path, String variableName) {

        Object value=testContext.getResponse().jsonPath().get(json_path);
        extractedValues.put(variableName,value);

    }
    @Then("the stored value {string} should be equal to {string}")
    public void the_stored_value_should_be_equal_to(String variableName, String expectedValue) {

        String actualValue=String.valueOf(extractedValues.get(variableName));
        System.out.println("The variable name is : "+variableName+"The extracted value is:"+actualValue);
        assertThat(actualValue,equalTo(expectedValue));

    }

    @Then("the extracted list of {string} should contain {int} distinct values")
    public void the_extracted_list_of_should_contain_distinct_values(String field, Integer expectedDistinctCount) {

        List<Integer> list_values=testContext.getResponse().jsonPath().getList(field, Integer.class);
        Set<Integer> distinctValues=new HashSet<>(list_values);
        System.out.println("The values in list is :"+list_values.size());
        System.out.println("The values in the set is :"+distinctValues.size());
        assertThat(distinctValues.size(),equalTo(expectedDistinctCount));
    }

    @Then("the first post with userId {string} should be a non-null title")
    public void the_first_post_with_user_id_should_be_a_non_null_title(String userId) {

        JsonPath json_path=testContext.getResponse().jsonPath();
        //GPath filtering : find{} returns the first element matching the condition
        String title=json_path.getString("find{it.userId == "+userId+"}.title");
        System.out.println("the title is :"+title);
        assertThat(title,notNullValue());

    }

    @Then("all posts with userId {string} should number {int}")
    public void all_posts_with_user_id_should_number(String userId, Integer expectedCount) {

        JsonPath json_path=testContext.getResponse().jsonPath();

        //GPath filtering: findAll{} returns EVERY Element matching the condition,
        //not  just the first - here, every post object where userId == 3
        List<Map<String,?>> matchingPosts=json_path.getList("findAll{it.userId=="+userId+"}");
        //System.out.println(matchingPosts);
        assertThat(matchingPosts.size(),equalTo(expectedCount));



    }
}

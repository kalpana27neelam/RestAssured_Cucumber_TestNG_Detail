package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import pojo.Post;
import pojo.User;
import utils.TestContext;

import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class Serialization05Steps {

    private TestContext testContext;

    private Post postToSend;
    private Post deserializedPost;
    private User deserializedUser;
    private List<Post> deserializedPostList;

    public Serialization05Steps(TestContext testContext)
    {
        this.testContext=testContext;
    }

    @Given("I build a Post with userId {int} , title {string} , body {string}")
    public void i_build_a_post_with_user_id_title_body(Integer userId, String title, String body) {
        postToSend=new Post(userId,title,body);
    }
    @When("I POST the post object to {string}")
    public void i_post_the_post_object_to(String path) {
        //SERIALIZATION happens here : REST Assured converts postToSend -> JSON
        testContext.setResponse(testContext.getRequestSpecification().body(postToSend).when().post(path));
    }
    @Then("the deserialized post title should equal {string}")
    public void the_deserialized_post_title_should_equal(String expectedTitle) {
        Post responsePost=testContext.getResponse().as(Post.class);
        assertThat(responsePost.getTitle(),equalTo(expectedTitle));
    }

    @Then("I deserialize the response into a POST")
    public void i_deserialize_the_response_into_a_post() {
       deserializedPost=testContext.getResponse().as(Post.class);
    }
    @Then("the deserialized post should have non-null title")
    public void the_deserialized_post_should_have_non_null_title() {
        assertThat(deserializedPost.getTitle(),notNullValue());
    }

    @Then("I deserialize the response into a USER")
    public void i_deserialize_the_response_into_a_user() {
       deserializedUser=testContext.getResponse().as(User.class);
    }
    @Then("the deserialized user's city should equal {string}")
    public void the_deserialized_user_s_city_should_equal(String expectedCity) {
        assertThat(deserializedUser.getAddress().getCity(),equalTo(expectedCity));
    }

    @Then("I deserialize the response into a list of POST")
    public void i_deserialize_the_response_into_a_list_of_post() {
    //Generic collections need a TypeRef - a plain .as(List.class) would
    // lose the element type at runtime due to Java type erasure.
        deserializedPostList=testContext.getResponse().as(new TypeRef<List<Post>>() {});
    }

    @Then("the deserialized POST should contain {int} items")
    public void the_deserialized_post_should_contain_items(Integer expectedSize) {
        System.out.println("The size of deserialized Post list is:"+deserializedPostList.size());
        assertThat(deserializedPostList,hasSize(expectedSize));
    }
}

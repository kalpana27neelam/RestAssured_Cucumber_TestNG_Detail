Feature: Serialization and deSerialization of POJOs
    #CONCEPT :
    # Serialization = Java Object -> JSON request body (done for you when you pass a POJO to .body()
    #DeSerialization = JSON Response -> JAVA Object, via .as(SameClass.class) or a TypeRef for generics
    # like List<Post>

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: Serialize a POJO as a request body
    Given I build a Post with userId 7 , title "Serialization demo" , body "sent as POJO"
    When I POST the post object to "/posts"
    Then the response status code should be 201
    And the deserialized post title should equal "Serialization demo"


  Scenario: Deserialize a single JSON Object into a POJO
    When I send a Get request to "/posts/1"
    Then I deserialize the response into a POST
    And the deserialized post should have non-null title

  Scenario: Deserialize nested JSON into a nested POJO
    When I send a Get request to "/users/1"
    Then I deserialize the response into a USER
    And the deserialized user's city should equal "Gwenborough"

  Scenario: Deserialize a JSON Array into a generic List<Post>
    When I send a Get request to "/posts"
    Then I deserialize the response into a list of POST
    And the deserialized POST should contain 100 items


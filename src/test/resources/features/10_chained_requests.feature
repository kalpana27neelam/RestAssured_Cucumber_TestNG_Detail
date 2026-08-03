Feature: Chained API calls

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"


  Scenario: Create a post , then fetch the user who owns it
    Given the request body is:
    """
    {
      "title" : "Chained call demo",
       "body" : "create then read" ,
       "userId" : 4
    }
    """
    When I send a POST request to "/posts"
    Then the response status code should be 201
    And I capture the "userId" from the response as "createdUserId"

    When I send a GET request to "/users/" plus the captured value "createdUserId"
    Then the response status code should be 200
    And the json path "id" should equal the captured value "createdUserId"
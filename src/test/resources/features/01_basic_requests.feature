Feature: Basic HTTP requests with REST assured

    # CONCEPT: The Given-When-Then (BDD) syntax is REST Assured's fluent DSL.
  #   Given -> set up the request (headers, body, params)
  #   When  -> fire the HTTP call (get/post/put/patch/delete)
  #   Then  -> assert on the response
  # This maps naturally onto Cucumber's own Given/When/Then, which is why
  # the two pair so well together.

  Background:
      Given the base URI is "https://jsonplaceholder.typicode.com"

    Scenario: Get a single resource
      When I send a Get request to "/posts/1"
      Then the response status code should be 200


    Scenario: Get a collection of resources
      When I send a Get request to "/posts"
      Then the response status code should be 200
      And the response should be JSON array


    Scenario: POST creates a new resource
      Given the request body is:
      """
      {
          "title":"Learning Rest Assured",
          "body" :"Cucumber + TestNG make this readable",
          "userId":1
      }
      """
      When I send a POST request to "/posts"
      Then the response status code should be 201

    Scenario: PUT fully replace a resource
      Given the request body is:
      """
      {
          "id":1,
          "title":"Updated title",
          "body": "Updated body",
          "userId":1
      }
      """
      When I send PUT request to "/posts/1"
      Then the response status code should be 200
      
    Scenario: PATCH partially updates a resource
      Given the request body is:
      """
      {
          "title":"only the title changes"
      }
      """
      When I send a PATCH request to "/posts/1"
      Then the response status code should be 200

    Scenario: DELETE removes a resource
      When I send a DELETE request to "/posts/1"
      Then the response status code should be 200
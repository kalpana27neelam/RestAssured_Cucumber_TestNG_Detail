Feature: Building requests - headers , query parameters , path parameters
  #CONCEPT : Everything that shapes the OUTGOING request before it is sent

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: Query Parameters filter a collection
    Given I set query param "userId" to "1"
    When I send a Get request to "/posts"
    Then the response status code should be 200
    And every post in the response should have userId "1"

  Scenario: Path Paramters build the URL dynamically
    When I set path param "postId" to "5"
    When I send a Get request to "/posts/{postId}"
    Then the response status code should be 200
    And the json path "id" should equal "5"

  Scenario: Custom headers are sent with the request
    Given the base URI is "https://httpbin.org"
    And I set header "X-Test-Suite" to "REST-Assured-Learning"
    When I send a Get request to "/headers"
    Then the response status code should be 200
    And the json path "headers.X-Test-Suite" should equal "REST-Assured-Learning"


  Scenario: Multiple query params combine correctly
    Given I set query param "userId" to "2"
    And I set query param "id" to "16"
    When I send a Get request to "/comments"
    Then the response status code should be 200
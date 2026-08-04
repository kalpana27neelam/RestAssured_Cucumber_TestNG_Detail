Feature: Logging and negative testing

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: log the full request and response for debugging
    Given I enable full request and response logging
    When I send a Get request to "/posts/1"
    Then the response status code should be 200


  Scenario: A malformed resource id returns a client error
    When I send a Get request to "/posts/not-a-number"
    Then the response status code should be 404


  Scenario: Server error simulation via httpbin
    Given the base URI is "https://httpbin.org"
    When I send a Get request to "/status/500"
    Then the response status code should be 500


Feature: Reusable request and response specifications

  Scenario: Using a prebuilt request spec against JSONPlaceholder
    Given I use the reusable JSONPlaceholder spec
    When I send a Get request to "/posts/1"
    Then the response status code should be 200

  Scenario: Using a Prebuilt request spec against httpbin
    Given I use the reusable httpbin spec
    When I send a Get request to "/get"
    Then the response status code should be 200

  Scenario: Reusable response spec asserts content type and response time together
    Given I use the reusable JSONPlaceholder spec
    When I send a Get request to "/posts/1"
    Then the response should satisfy the default response spec

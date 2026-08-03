Feature: Authentication

  Background:
    Given the base URI is "https://httpbin.org"

  Scenario: Basic authentication with correct credentials succeeds
    Given I authenticate with basic auth username "user" and password "pass"
    When I send a Get request to "/basic-auth/user/pass"
    Then the response status code should be 200
    And the json path "authenticated" should equal "true"

  Scenario: Basic authentication with wrong credentials fails
    Given I authenticate with basic auth username "user" and password "wrongpass"
    When I send a Get request to "/basic-auth/user/pass"
    Then the response status code should be 200
    And the json path "authenticated" should equal "true"

  Scenario: Bearer token authentication
    Given I authenticate with bearer token "sample-token-123"
    When I send a Get request to "/bearer"
    Then the response status code should be 200
    And the json path "token" should equal "sample-token-123"
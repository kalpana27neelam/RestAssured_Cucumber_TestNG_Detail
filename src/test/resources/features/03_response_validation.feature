Feature: Validating responses
  #CONCEPT: everything you can assert on once the response comes back.
  # status code/Line, headers , cookies , response time , and body content
  # vio Hamcrest Matchers

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: Status line and Content type
    When I send a Get request to "/posts/1"
    Then the response status line should contain "200"
    And the response content type should be "application/json; charset=utf-8"

  Scenario: Response header assertions
    When I send a Get request to "/posts/1"
    Then the response header "Content-Type" should contain "application/json"

  Scenario: Response time is within an acceptable bound
    When I send a Get request to "/posts/1"
    Then the response time should be less than 5000 ms


  Scenario: Body assertions with Hamcrest matchers
    When I send a Get request to "/posts/1"
    Then the field "userId" should be greater than "0"
    Then the field "title" should not be empty

  Scenario: Negative test-requesting a non-existing resource
    When I send a Get request to "/posts/999999"
    Then the response status code should be 404
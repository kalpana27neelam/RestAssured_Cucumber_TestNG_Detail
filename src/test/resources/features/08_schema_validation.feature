Feature: JSON Schema validation

  Background: 
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: Response matches the expected Post schema
    When I send a Get request to "/posts/1"
    Then the response should match the JSON Schema "schema/post-schema.json"
Feature:Extracting data with JsonPath
  #CONCEPT: JsonPath lets you pull out specific values out of JSON response
  # without deserializing into full POJO Object. GPath syntax also supports
  # filtering collections e.g find{} / findAll{}.

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

  Scenario: Extract a simple scalar field
    When I send a Get request to "/users/1"
    Then I extract the json path "name" as "userName"
    And the stored value "userName" should be equal to "Leanne Graham"

  Scenario: Extract a nested field
    When I send a Get request to "/users/1"
    Then I extract the json path "address.city" as "city"
    And the stored value "city" should be equal to "Gwenborough"

  Scenario: Extract a list of values across an array
    When I send a Get request to "/posts"
    Then the extracted list of "userId" should contain 10 distinct values

  Scenario: Gpath filtering - find th first item matching a condition
    When I send a Get request to "/posts"
    Then the first post with userId "3" should be a non-null title

  Scenario: Gpath Filtering - find all items matching a condition
    When I send a Get request to "/posts"
    Then all posts with userId "3" should number 10
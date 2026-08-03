Feature: Data - driven testing

  Background:
    Given the base URI is "https://jsonplaceholder.typicode.com"

   Scenario Outline: Fetching different posts by id returns the matching id
     When I send a Get request to "/posts/<postId>"
     Then the response status code should be 200
     And the json path "id" should equal "<postId>"

     Examples:
     |postId|
     |1     |
     |15    |
     |42    |
     |100   |

   Scenario Outline: Creating posts with different payloads always returns 201
     Given the request body is:
     """
     { "title" : "<title>" , "body" : "<body>" , "userId" : "<userId>"}
     """
     When I send a POST request to "/posts"
     Then the response status code should be 201

    Examples:
     |   title   |     body    |    userId   |
     |First Post |first content|      1      |
     |Second Post|Second Content|     2      |
     |Third Post |Third Content |     3      |

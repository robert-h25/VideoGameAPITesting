Feature: Get All Video Games In a List

  As a user,
  I want to retrieve a list of all video games from the database,
  so that I can view available options and learn more about each game.

  @Happy
  Scenario: The database contains video games
    Given The API end-point videogame
    When I send GET HTTP request to API videogame
    Then I should receive a list of video games in a list
    And I get 200 status code

  @Sad
  Scenario: Using an Unsupported HTTP Method
    Given The accepted HTTP method is GET
    When I send a POST HTTP request to the videogame endpoint.
    Then I should receive a 405 Method Not Allowed status code.
    And Response  should indicate that the POST method is not supported.

  @Sad

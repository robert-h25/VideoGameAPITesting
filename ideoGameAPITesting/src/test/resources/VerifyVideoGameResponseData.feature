Feature:
  As a user,
  I want to see detailed information about each video game in the response,
  so that I can learn about its category, rating, release date, and review score.

  @Happy
  Scenario: The API provides complete game details.
    Given the database contains detailed game records,
    When the user sends a GET request to /api/videogame,
    Then the API responds with a 200 OK status,
    And the response body includes fields:  id, name, category, rating, releaseDate, and reviewScore.

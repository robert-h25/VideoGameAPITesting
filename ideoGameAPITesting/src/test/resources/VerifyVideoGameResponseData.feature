Feature:
  As a user,
  I want to see detailed information about each video game in the response,
  so that I can learn about its category, rating, release date, and review score.

  @Happy
  Scenario: The API provides complete game details.
    Given I have built a request with a JSON header
    When I send GET HTTP request to API videogame
    Then I should receive a list of video games in a list
    And the response body should contain a list of video games with attributes
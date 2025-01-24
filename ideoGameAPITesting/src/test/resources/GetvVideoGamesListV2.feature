Feature:
  As a user, I want to be able to send a GET request to the v2 controller, so that I can get a list of all videogames on the website

  Scenario: Getting a list of videogames as JSON

    Given I have the endpoint "/api/v2/videogame"
    And I specify the "Accept" header with "application/json"
    When I send a GET request to the endpoint
    Then I should receive a 200 status code
    And the response content type should be "application/json"
    And the response body should contain a list of video games with attributes:
      | key | value |
      | id | (an integer) |
      | title | (a string) |
      | release_date | (a valid date format) |
      | genre | (a string) |
      | platform | (a string) |
    And the response should include at least one video game

  Scenario: Getting a list of videogames as XML

    Given I have the endpoint "/api/v2/videogame"
    And I specify the "Accept" header with "application/xml"
    When I send a GET request to the endpoint
    Then I should receive a 200 status code
    And the response content type should be "application/xml"
    And the response body should contain a list of video games as XML with elements:

    """
    (an integer)
    <title>(a string)</title>
    <release_date>(a valid date format)</release_date>
    (a string)
    (a string)
    """

    And the response should include at least one video game
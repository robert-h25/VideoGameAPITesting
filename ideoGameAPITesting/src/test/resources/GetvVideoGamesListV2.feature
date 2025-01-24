Feature:
  As a user, I want to be able to send a GET request to the v2 controller, so that I can get a list of all videogames on the website

  Scenario: Getting a list of videogames as JSON

    Given I have built a request with a JSON header
    When I send GET HTTP request to API videogame
    Then I get 200 status code
    And the response content type should be application json
    And the response body should contain a list of video games with attributes

  Scenario: Getting a list of videogames as XML

    Given I have built a request with a XML header
    When I send GET HTTP request to API videogame
    Then I get 200 status code
    And the response content type should be application xml
    And the response body should contain a list of video games as XML with elements
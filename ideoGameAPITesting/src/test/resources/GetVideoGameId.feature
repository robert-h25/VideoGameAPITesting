Feature:
  As a: User or system
  I want to: Retrieve details of a specific video game using its ID
  So that I can: View its information, such as name, category, rating, release date, and review score

  @Happy
  Scenario: I provide a valid id of an existing video game JSON:
    Given I have end point videogameid json
    When I specify the accept header with application json
    And I send GET HTTP request for JSON videogame by ID JSON
    Then The API returns a 200 OK response JSON.
    And The response body is in JSON format

  @Happy
  Scenario: I provide a valid id of an existing video game XML
    Given I have end point videogameid xml
    When I specify the Accept header with application xml
    And I send GET HTTP request for JSON videogame by ID XML
    Then The API returns a 200 OK responseXML.
    And The response body is in XML format.
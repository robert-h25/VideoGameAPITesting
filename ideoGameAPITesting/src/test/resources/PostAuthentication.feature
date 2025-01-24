Feature:
  As a user, I want to be able to send a POST request with my password and username, so that I can authenticate

  Scenario: Login with valid credentials
    Given I have the endpoint "/api/authenticate"
    And I provide the following valid credentials:
      | username | password |
      | validUser123 | ValidPassword1 |
    When I send a POST request to the endpoint with the provided credentials
    Then I should receive a 200 status code
    And the response should include a valid authentication token
    And the response body should contain:
      | key | value |
      | token | (a valid JWT token structure) |
      | token_type | Bearer |
    And the token should allow me access to secure endpoints

  Scenario: Login with invalid credentials
    Given I have the endpoint "/api/authenticate"
    And I provide the following invalid credentials:
      | username | password |
      | invalidUser123 | WrongPassword1 |
    When I send a POST request to the endpoint with the provided credentials
    Then I should receive a 401 status code
    And the response should contain an error message:
      | key | value |
      | error | Invalid username/password |
    And no authentication token should be included in the response body
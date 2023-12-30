Feature: Validate token and generate a new token
  This will be used for refreshing token and to validate token

  Scenario: Token is invalid
    Given A chef or service provides a invalid token
    When Tries to get a new token
    Then Returns unauthorized because the token is invalid

  Scenario: Token provided is valid
    Given A chef or a service provides a valid token
    When Tries to get a new token
    Then Returns the new token
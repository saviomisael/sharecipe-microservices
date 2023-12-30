Feature: Validate token and generate a new token
  This will be used for refreshing token and to validate token

  Scenario: Token is invalid
    Given A chef or service provide a invalid token
    When Tries to get a new token
    Then Returns unauthorized because the token is invalid
Feature: Block username for 35 minutes
  This feature enhances security blocking a old username until the old token with this username expires

  Scenario: Some person tries to create an account with an username blocked
    Given A chef is logged-in as spacca
    And Changes his username to pava
    When Another person tries to create an account with spacca username
    Then Returns a 422 status code

  Scenario: A chef changes his username and wants to use the old token
    Given A chef is logged-in as spacca2
    And Changes his username to pava2
    When He tries to get a refreshToken with the old token
    Then Returns a 401 status code because old username does not exist

  Scenario: A chef changes his username and wants to change the password for his old username
    Given A chef is logged-in as spacca3
    And Changes his username to pava3
    When He tries to change his password for his old username
    Then Returns a 401 status code because old username does not exist
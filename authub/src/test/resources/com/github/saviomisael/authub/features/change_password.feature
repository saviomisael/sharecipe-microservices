Feature: Change password for a chef
  As a logged-in chef, I want to be able to change my password

  Scenario: The chef is not logged-in
    Given A user without a token
    When He tries to change his password
    Then Returns unauthorized because he is not logged-in
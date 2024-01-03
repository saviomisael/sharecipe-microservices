Feature: Change chef username
  Any chef will can change his username

  Scenario: Chef is not logged-in
    Given A chef does not have a token
    When He tries to log in
    Then Returns unauthorized because he does not have a token
Feature: Change chef username
  Any chef will can change his username

  Scenario: Chef is not logged-in
    Given A chef does not have a token
    When He tries to change his username
    Then Returns unauthorized because he does not have a token

  Scenario: New username is less than 2 characters
    Given A chef is logged-in in the system
    And Wants to change his username with a username less than 2 characters
    When He tries to change his username
    Then Returns a bad request because new username is invalid

  Scenario: New username is longer than 255 characters
    Given A chef is logged-in in the system
    And Wants to change his username with a username longer than 255 characters
    When He tries to change his username
    Then Returns a bad request because new username is invalid

  Scenario: Chef changed his username successfully
    Given A chef is logged-in in the system
    And Wants to change his username with a valid username
    When He tries to change his username
    Then Returns a ok status code with a new token

  Scenario: A chef tries to change his username with an username that already exists
    Given A chef with username wally is created
    And A chef is logged-in as barry
    When He tries to change his username to wally
    Then Returns 422 status code because someone is using the wally username
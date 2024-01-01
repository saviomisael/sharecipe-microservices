Feature: Change password for a chef
  As a logged-in chef or a new user, I want to be able to change my password

  Scenario: The chef is not logged-in
    Given A user without a token
    When He tries to change his password
    Then Returns unauthorized because he is not logged-in

  Scenario: The new password is fewer than 8 characters
    Given A chef that creates his account
    And Wants to change his password with one that has less than 8 characters
    When He tries to change his password
    Then Returns a bad request for invalid password

  Scenario: The new password has more than 255 characters
    Given A chef that creates his account
    And Wants to change his password with one that has more than 255 characters
    When He tries to change his password
    Then Returns a bad request for invalid password
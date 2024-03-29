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

  Scenario: The new password does not have any uppercase letter
    Given A chef that creates his account
    And Wants to change his password with one that does not have any uppercase letter
    When He tries to change his password
    Then Returns a bad request for invalid password

  Scenario: The new password does not have any lowercase letter
    Given A chef that creates his account
    And Wants to change his password with one that does not have any lowercase letter
    When He tries to change his password
    Then Returns a bad request for invalid password

  Scenario: The new password does not have any digit
    Given A chef that creates his account
    And Wants to change his password with one that does not have any digit
    When He tries to change his password
    Then Returns a bad request for invalid password

  Scenario: The new password does not have any special digit
    Given A chef that creates his account
    And Wants to change his password with one that does not have any special digit
    When He tries to change his password
    Then Returns a bad request for invalid password

  Scenario: A chef created his account and change his password successfully
    Given A chef that creates his account
    And Wants to change his password with a valid new password
    When He tries to change his password
    Then Returns a no content response

  Scenario: A chef already has an account and is logged-in and wants to change his password
    Given A chef that creates his account
    And Is logged-in
    And Wants to change his password with a valid new password
    When He tries to change his password
    Then Returns a no content response
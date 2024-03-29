Feature: Sign In into sharecipe
  With this any chef will can log in to share your recipes into sharecipe

  Scenario: Username does not have minimum length
    Given A chef attempts to log in with a username that is fewer than 2 characters
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Username has more than 255 characters
    Given A chef attempts to log in with a username that exceeds 255 characters
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password does not have minimum length
    Given A chef attempts to log in with a password that is fewer than 8 characters
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password has more than 255 characters
    Given A chef attempts to log in with a password that exceeds 255 characters
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password does not have at least one uppercase letter
    Given A chef attempts to log in with a password without any uppercase letter
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password does not have at least one lowercase letter
    Given A chef attempts to log in with a password without any lowercase letter
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password does not have at least one number
    Given A chef attempts to log in with a password without any number
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Password does not have at least one symbol
    Given A chef attempts to log in with a password without any symbol
    When This chef tries to log in
    Then Returns a bad request

  Scenario: Username does not exist
    Given A chef attempts to log in with an username that does not exist
    When This chef tries to log in
    Then Returns unauthorized

  Scenario: Password is wrong
    Given That a chef already has an account
    And Attempts to log in with a wrong password
    When This chef tries to log in
    Then Returns unauthorized

  Scenario: Chef can log in successfully
    Given That a chef already has an account
    And Attempts to log in with correct credentials
    When This chef tries to log in
    Then Returns ok
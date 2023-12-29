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
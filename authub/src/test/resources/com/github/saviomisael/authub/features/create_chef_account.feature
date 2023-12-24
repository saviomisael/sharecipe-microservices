Feature: Create chef account
  As a chef I want to create my account

  Scenario: Full name is invalid
    Given A person that provide your full name in a invalid way
    When This person try to create an account
    Then The person should get a bad request response

  Scenario: Username provided is invalid
    Given A person that provide an invalid username
    When This person try to create an account
    Then The person should get a bad request response

  Scenario: Password less than 8 characters
    Given A person that provide a password less than 8 characters to create an account
    When This person try to create an account
    Then The person should get a bad request response

  Scenario: Password without uppercase letter
    Given A person that provide a password almost valid but does not have a uppercase letter
    When This person try to create an account
    Then The person should get a bad request response

  Scenario: Password without lowercase letter
    Given A person that provide a password almost valid but does not have a lowercase letter
    When This person try to create an account
    Then The person should get a bad request response

  Scenario: Password provided has more than 255 characters
    Given A person provides a password with more than 255 characters
    When This person try to create an account
    Then The person should get a bad request response
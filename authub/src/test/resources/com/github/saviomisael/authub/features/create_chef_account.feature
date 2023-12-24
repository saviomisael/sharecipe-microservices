Feature: Create chef account
  As a chef I want to create my account

  Scenario: Full name is invalid
    Given A person that provides your full name in a invalid way
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Username provided is invalid
    Given A person that provides an invalid username
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password less than 8 characters
    Given A person that provides a password less than 8 characters to create an account
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password without uppercase letter
    Given A person that provides a password almost valid but does not have a uppercase letter
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password without lowercase letter
    Given A person that provides a password almost valid but does not have a lowercase letter
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password provided has more than 255 characters
    Given A person provides a password with more than 255 characters
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password provided does not have digit
    Given A person provides a password without numbers
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Password provided does not have at least one symbol
    Given A person provides a password without symbols
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Email is invalid
    Given A person provides all the information but the email is invalid
    When This person tries to create an account
    Then The person should get a bad request response

  Scenario: Username already in use
    Given A person provides an username that already is in use
    When This person tries to create an account
    Then The person should get an unprocessable entity response

  Scenario: Email already used
    Given A person provides an email that already is in use
    When This person tries to create an account
    Then The person should get an unprocessable entity response
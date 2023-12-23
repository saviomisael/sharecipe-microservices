Feature: Create chef account
  As a chef I want to create my account

  Scenario: Full name is invalid
    Given A person that provide your full name in a invalid way
    When This person try to create an account
    Then The person should get a bad request response
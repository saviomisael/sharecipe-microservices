Feature: Block username for 35 minutes
  This feature enhances security blocking a old username until the old token with this username expires

  Scenario: Some person tries to create an account with an username blocked
    Given A chef is logged-in
    And Changes his username
    When Another person tries to create an account with the old username
    Then Returns a 422 response
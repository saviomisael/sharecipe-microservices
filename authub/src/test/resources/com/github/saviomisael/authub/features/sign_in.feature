Feature: Sign In into sharecipe
  With this any chef will can log in to share your recipes into sharecipe

  Scenario: Username does not have minimum length
    Given A chef that wants to log in
    When This chef tries to log in
    Then Returns a bad request
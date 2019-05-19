Feature: login

  It should be possible to login with a login and password
  Scenario: login
    Given I am on the page where I can enter my login and password
    When I enter "someone2" in the username field
    And I enter "pw2" in the password passwordfield
    And I press on the Login button
    Then I should be on the home screen
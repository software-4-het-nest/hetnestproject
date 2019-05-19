Feature: Add Aanbieding

  It should be possible to add a aanbieding to the list
  just after adding the aanbieding, the details of the aanbieing should be known
  After the list is shown again, the aanbieding name should be visible

    Scenario: Add one aanbieding
      Given I am on the page where I can enter my login and password
      When I enter "someone2" in the username field
      And I enter "pw2" in the password passwordfield
      And I press on the Login button
      Then I should be on the home screen
      Given I am on the home screen logged in
      When I press on the bekijk aanbiedingen button
      Then I am directed to the aanbiedingen screen
      Given I am on the aanbiedingen screen
      When I press on the nieuwe aanbieding link
      Then I am directed to the page where I can introduce a new aanbieding
      Given I am on the page where I can introduce a new aanbieding
      When I enter "Redouane" in the naam field
      And I enter "goedgekeurd" in the status field
      And I enter "50" in the hoeveelheid field
      And I enter "80" in the prijs field
      And I enter "Appelen" in the type field
      And I press on the Save button
      Then I should see the following on the screen
      | label          | data         |
      | Naam:          | Redouane     |
      | Hoeveelheid:   | 50           |
      | Prijs:         | 80           |
      | Type:          | Appelen      |
      When I click the the Lijst van alle aanbiedingen link
      Then I should see a list containing "Redouane"
      And I close the browser


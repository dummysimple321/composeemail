Feature: This is to test the compose mail of Gmail

  Scenario: Compose email and send using gmail
    Given I open gmail page in browser
    When I have provide mail id "dummy.simple321@gmail.com"
    And I provide the password "Simple#123"
#    When I click on the Next button
    Then user is able to see inbox
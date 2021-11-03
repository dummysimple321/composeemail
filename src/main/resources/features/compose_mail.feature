Feature: This is to test the compose mail of Gmail

  Scenario: Compose email and send using Gmail
    Given I open gmail page in browser
    When I have provide mail id "dummy.simple321@gmail.com"
    And I provide the password "Simple#123"
    When I click on the Next button
    Then user is able to see inbox
    When I click on Compose button
    Then New message window is opened
    When I enter the recipient email address "dummy.simple321@gmail.com"
    And i enter the subject "Incubyte"
    And I enter the body "Automation QA test for Incubyte"
    When I click on Send button
    Then mail should be sent successfully
Feature: ACME Bank

  Scenario: Successful login
    Given the ACME Bank login page is displayed
    When the user enters valid login credentials
    Then the ACME Bank main page is displayed
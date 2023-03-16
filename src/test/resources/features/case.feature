Feature: Test all cases service
  Background:
    Given Call case service

  Scenario: Check for service retrieving all cases
    When Get all cases
    Then There should be a total of 3 cases

  Scenario: Check for service retrieving an existing case
    When Case id is 2
    Then I should get case id 2
    And Having 'description' 'Description 2'
    And Having 'status' 'CLOSED'
    And Having 'lead detective' 'Lionel Messi'
    And Having 'date' '04/01/1990'

  Scenario: Check for lead detective
    When Case id is 3
    Then I should get Crist Ron

  Scenario: Get all OPEN cases
    When Get all cases
    And Case status is OPEN
    Then There should be a total of 2 cases
    And Verify the following values
    | caseId | date       | leadDetective | leadDetectiveRank | status  | description   | detectiveList |
    | 1      | 04/01/2010 | John David    | SERGEANT      | OPEN    | Description 1 | 1, 2          |
    | 3      | 04/01/2018 | Crist Ron     | LIEUTENANT    | OPEN    | Description 3 | 3, 4          |


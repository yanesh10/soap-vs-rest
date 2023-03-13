Feature: Testing detective services
  Background:
    Given Call detective service
  Scenario: Get detective
    When Pass John David
    Then I should get an detective id 1
    And Having detective 'first name' John
    And Having detective 'last name' David
    And Having detective 'rank' Sergeant

  Scenario: Create detective
    When Create the detective Tom Tom as an Officer
    Then I should get an detective id 5
    And Having detective 'first name' Tom
    And Having detective 'last name' Tom
    And Having detective 'rank' Officer

  Scenario: Update detective
    When Update 'first name' for the detective with id 3 to Jack
    And Update 'last name' for the detective with id 3 to Mick
    Then I should get an detective id 3
    And Having detective version 3
    And Having detective 'first name' Jack
    And Having detective 'last name' Mick
    And Having detective 'rank' Lieutenant

  Scenario: Delete detective
    When Delete detective Tom Tom
    Then Check if detective Tom Tom still exist

  Scenario: Get all detectives
    When Get all detectives
    Then There should be a total of 3 detectives

  Scenario: Get for non-existing detective
    Then I should get an error


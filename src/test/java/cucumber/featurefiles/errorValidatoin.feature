@regression
Feature: Validate Login Feature

  Background:
    Given user lands on home webPage


  Scenario Outline: Validate login page with valid and invalid credentials
    Given user logged in with "<username>" and password "<password>"
    Examples:
      | username                | password   |
      | arvindchavan7@gmail.com | Indore@123 |
      | arvindchavan7@gmail.com | Indore@125 |
      | arvindchavan7@email.com | Indore@123 |
      | arvindchavan7@email.com | Indore@125 |

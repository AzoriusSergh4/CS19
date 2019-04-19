Feature: Warship Shot
  In Order To Play
  As a user
  I can use the firing button to shoot with the warship

  Scenario: The user press the firing button
    Given There is a warship on the screen2
    And There is a firing button that the user can press
    When The user press the firing button
    Then The warship shoots
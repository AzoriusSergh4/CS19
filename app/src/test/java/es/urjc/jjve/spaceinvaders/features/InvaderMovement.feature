Feature: Warship Movement
  In Order To Increase Difficult
  As a user
  The invaders move

  Scenario: The invaders move to the right
    Given There is a invader on the screen
    When The game updates
    Then The invader moves

  Scenario: The invaders reverse their movement
    Given There is a invader on the screen
    When The invader is in the screen edge
    Then The invader reverses his movement
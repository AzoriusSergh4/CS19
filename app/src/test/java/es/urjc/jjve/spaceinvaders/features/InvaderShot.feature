Feature: Invader Shot
  In Order To Increase Difficult
  As a user
  The invaders can shoot

  Scenario: The invaders shoot randomly
    Given There are invaders on the screen
    When A random time is spent
    Then One invader shoots
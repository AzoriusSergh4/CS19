Feature: PlayerShip Movement
  In Order To Play
  As a User
  There is a PlayerShip that I handle

  Scenario: PlayerShip right movement
    Given There is a PlayerShip
    When The PlayerShip movement updates to the right
    Then The PlayerShip moves to the right

  Scenario: PlayerShip left movement
    Given There is a PlayerShip
    When The PlayerShip movement updates to the left
    Then The PlayerShip moves to the left

  Scenario: PlayerShip up movement
    Given There is a PlayerShip
    When The PlayerShip movement updates up
    Then The PlayerShip moves up

  Scenario: PlayerShip down movement
    Given There is a PlayerShip
    When The PlayerShip movement updates down
    Then The PlayerShip moves down
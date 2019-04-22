Feature: Warship Movement
  In Order To Play
  As a user
  I can use the joystick to move the warship

  Scenario: The user moves the joystick
    Given There is a warship on the screen
    And There is a joystick that the user can move
    When The user uses the joystick
    Then The warship moves
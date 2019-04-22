Feature: Invader Movement
  In Order To Increase Difficult
  As a User
  There is a Invader that moves

  Scenario: Invader right movement
    Given There is a Invader
    When The Invader movement updates to the right
    Then The Invader moves to the right

  Scenario: Invader left movement
    Given There is a Invader
    When The Invader movement updates to the left
    Then The Invader moves to the left
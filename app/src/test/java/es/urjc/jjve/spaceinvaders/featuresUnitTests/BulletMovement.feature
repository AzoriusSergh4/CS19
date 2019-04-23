Feature: Bullet Movement
  In Order To Increase The Fun
  As a User
  There is a Bullet that moves

  Scenario: Bullet up movement
    Given There is a Bullet
    When The Bullet movement updates up
    Then The Bullet moves up

  Scenario: Bullet down movement
    Given There is a Bullet
    When The Bullet movement updates down
    Then The Bullet moves down
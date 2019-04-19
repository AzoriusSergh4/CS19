package es.urjc.jjve.spaceinvaders.stepDefinitionUnitTests;

import org.junit.Assert;
import org.mockito.Mockito;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.entities.PlayerShip;

public class PlayershipMovement {
    PlayerShip ship;

    @Given("^There is a PlayerShip$")
    public void there_is_a_playership(){
        ship = Mockito.mock(PlayerShip.class);
        Mockito.doCallRealMethod().when(ship).setMovementState(Mockito.anyInt());
        Mockito.doCallRealMethod().when(ship).setShipSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(ship).getX();
        Mockito.doCallRealMethod().when(ship).getY();
        Mockito.doCallRealMethod().when(ship).updateMove(Mockito.anyLong());
        ship.setShipSpeed(5f);
    }

    @When("^The PlayerShip movement updates to the right$")
    public void the_movement_updates_to_the_right(){
        ship.setMovementState(ship.RIGHT);
    }

    @Then("^The PlayerShip moves to the right$")
    public void the_playership_moves_to_the_right(){
        float x1 = this.ship.getX();
        this.ship.updateMove(5);
        float x2 = this.ship.getX();
        boolean right = x2 > x1;

        Assert.assertTrue(right);
    }

    @When("^The PlayerShip movement updates to the left$")
    public void the_movement_updates_to_the_left(){
        ship.setMovementState(ship.LEFT);
    }

    @Then("^The PlayerShip moves to the left$")
    public void the_playership_moves_to_the_left(){
        float x1 = this.ship.getX();
        this.ship.updateMove(5);
        float x2 = this.ship.getX();
        boolean left = x1 > x2;

        Assert.assertTrue(left);
    }

    @When("^The PlayerShip movement updates up$")
    public void the_movement_updates_up(){
        ship.setMovementState(ship.UP);
    }

    @Then("^The PlayerShip moves up$")
    public void the_playership_moves_up(){
        float y1 = this.ship.getY();
        this.ship.updateMove(5);
        float y2 = this.ship.getY();
        boolean up = y1 < y2;

        Assert.assertTrue(up);
    }

    @When("^The PlayerShip movement updates down$")
    public void the_movement_updates_down(){
        ship.setMovementState(ship.DOWN);
    }

    @Then("^The PlayerShip moves down$")
    public void the_playership_moves_down(){
        float y1 = this.ship.getY();
        this.ship.updateMove(5);
        float y2 = this.ship.getY();
        boolean down = y1 > y2;

        Assert.assertTrue(down);
    }
}

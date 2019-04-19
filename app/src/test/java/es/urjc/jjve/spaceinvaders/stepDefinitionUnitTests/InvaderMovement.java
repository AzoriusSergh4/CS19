package es.urjc.jjve.spaceinvaders.stepDefinitionUnitTests;

import org.junit.Assert;
import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.entities.Invader;

public class InvaderMovement {

    Invader invader;

    @Given("^There is a Invader$")
    public void there_is_a_invader(){
        invader = Mockito.mock(Invader.class);
        Mockito.doCallRealMethod().when(invader).setShipMoving(Mockito.anyInt());
        Mockito.doCallRealMethod().when(invader).setShipSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).getX();
        Mockito.doCallRealMethod().when(invader).updateMove(Mockito.anyLong());
        invader.setShipSpeed(5f);
    }

    @When("^The Invader movement updates to the right$")
    public void the_invader_movement_updates_to_the_right(){
        invader.setShipMoving(invader.RIGHT);
    }

    @Then("^The Invader moves to the right$")
    public void the_invader_moves_to_the_right(){
        float x1 = this.invader.getX();
        this.invader.updateMove(5);
        float x2 = this.invader.getX();
        boolean right = x2 > x1;

        Assert.assertTrue(right);
    }

    @When("^The Invader movement updates to the left$")
    public void the_invader_movement_updates_to_the_left(){
        invader.setShipMoving(invader.LEFT);
    }

    @Then("^The Invader moves to the left$")
    public void the_invader_moves_to_the_left(){
        float x1 = this.invader.getX();
        this.invader.updateMove(5);
        float x2 = this.invader.getX();
        boolean left = x1 > x2;

        Assert.assertTrue(left);
    }
}

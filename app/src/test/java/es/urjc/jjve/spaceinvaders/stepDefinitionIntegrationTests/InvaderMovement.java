package es.urjc.jjve.spaceinvaders.stepDefinitionIntegrationTests;

import android.graphics.RectF;

import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.controllers.ViewController;
import es.urjc.jjve.spaceinvaders.entities.Invader;

public class InvaderMovement {
    Invader invader;
    ViewController viewController;
    float x1;

    @Given("^There is a invader on the screen$")
    public void there_ia_a_invader_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        invader = Mockito.mock(Invader.class);
        Mockito.doCallRealMethod().when(invader).setShipMoving(Mockito.anyInt());
        Mockito.doCallRealMethod().when(invader).setVisible();
        Mockito.doCallRealMethod().when(invader).getVisibility();
        Mockito.doCallRealMethod().when(invader).setRect((RectF) Mockito.any());
        Mockito.doCallRealMethod().when(invader).update(Mockito.anyLong());
        Mockito.doCallRealMethod().when(invader).setShipSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).setX(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).getX();
        invader.setRect(Mockito.mock(RectF.class));
        invader.setVisible();
        invader.setShipMoving(2);
        invader.setShipSpeed(40);
        ArrayList<Invader> invaders = new ArrayList<>();
        invaders.add(invader);
        Mockito.doCallRealMethod().when(viewController).setInvaders((ArrayList<Invader>) Mockito.any());
        Mockito.doCallRealMethod().when(viewController).getInvaders();
        Mockito.doCallRealMethod().when(viewController).updateInvadersMovement(Mockito.anyLong());
        viewController.setInvaders(invaders);
    }

    @When("^The game updates$")
    public void the_game_updates(){
        x1 = this.viewController.getInvaders().get(0).getX();
        viewController.updateInvadersMovement(5);
    }

    @Then("^The invader moves$")
    public void the_invader_moves(){
        float x2 = this.viewController.getInvaders().get(0).getX();
        Assert.assertNotEquals(x1, x2);
    }
}

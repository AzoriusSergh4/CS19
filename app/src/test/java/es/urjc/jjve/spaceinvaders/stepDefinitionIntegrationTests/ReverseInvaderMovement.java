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

public class ReverseInvaderMovement {
    Invader invader;
    ViewController viewController;
    int x1;

    @Given("^There is a invader near the screen edge$")
    public void there_ia_a_invader_near_the_screen_edge(){
        viewController = Mockito.mock(ViewController.class);
        invader = Mockito.mock(Invader.class);
        Mockito.doCallRealMethod().when(invader).setShipMoving(Mockito.anyInt());
        Mockito.doCallRealMethod().when(invader).setVisible();
        Mockito.doCallRealMethod().when(invader).getVisibility();
        Mockito.doCallRealMethod().when(invader).setRect((RectF) Mockito.any());
        Mockito.doCallRealMethod().when(invader).update(Mockito.anyLong());
        Mockito.doCallRealMethod().when(invader).setShipSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).getShipMoving();
        Mockito.doCallRealMethod().when(invader).getX();
        Mockito.doCallRealMethod().when(invader).getLength();
        Mockito.doCallRealMethod().when(invader).dropDownAndReverse();
        invader.setRect(Mockito.mock(RectF.class));
        invader.setVisible();
        invader.setShipMoving(2);
        invader.setShipSpeed(40);
        ArrayList<Invader> invaders = new ArrayList<>();
        invaders.add(invader);
        Mockito.doCallRealMethod().when(viewController).setInvaders((ArrayList<Invader>) Mockito.any());
        Mockito.doCallRealMethod().when(viewController).getInvaders();
        Mockito.doCallRealMethod().when(viewController).reverse();
        Mockito.doCallRealMethod().when(viewController).updateInvadersMovement(Mockito.anyLong());
        viewController.setInvaders(invaders);
    }

    @When("^The invader is in the screen edge$")
    public void the_invader_is_in_the_screen_edge(){
        invader.setX(10000);
        ArrayList<Invader> invaders = new ArrayList<>();
        invaders.add(invader);
        viewController.setInvaders(invaders);
        x1 = this.viewController.getInvaders().get(0).getShipMoving();
        viewController.updateInvadersMovement(5);
    }

    @Then("^The invader reverses his movement$")
    public void the_invader_reverses_his_movement(){
        int x2 = this.viewController.getInvaders().get(0).getShipMoving();
        Assert.assertNotEquals(x1, x2);
    }
}

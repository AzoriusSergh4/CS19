package es.urjc.jjve.spaceinvaders.stepDefinition;

import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.controllers.ViewController;
import es.urjc.jjve.spaceinvaders.entities.Bullet;
import es.urjc.jjve.spaceinvaders.entities.PlayerShip;
import es.urjc.jjve.spaceinvaders.view.SpaceInvadersView;

public class WarshipShot {

    PlayerShip ship;
    SpaceInvadersView spaceInvadersView;
    ViewController viewController;

    @Given("^There is a warship on the screen2$")
    public void there_is_a_warship_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        ship = Mockito.mock(PlayerShip.class);
        Mockito.doCallRealMethod().when(viewController).setPlayerShip((PlayerShip)Mockito.any());
        Mockito.doCallRealMethod().when(viewController).shipMovement(Mockito.anyFloat(), Mockito.anyFloat());
        Mockito.doCallRealMethod().when(ship).setMovementState(Mockito.anyInt());
        Mockito.doCallRealMethod().when(ship).getMovement();
        viewController.setPlayerBullets(new ArrayList<Bullet>());
        viewController.setPlayerShip(ship);
    }

    @And("^There is a firing button that the user can press$")
    public void there_is_a_firing_button(){
        spaceInvadersView = Mockito.mock(SpaceInvadersView.class);
        //Mockito.doCallRealMethod().when(spaceInvadersView).onTouchEvent();
    }

    @When("^The user press the firing button$")
    public void the_user_press_the_firing_button(){
        viewController.notifyShoot();
    }

    @Then("^The warship shoots$")
    public void the_warship_shoots(){
        Assert.assertTrue(this.viewController.getPlayerBullets().isEmpty());
    }
}

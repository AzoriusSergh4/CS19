package es.urjc.jjve.spaceinvaders.stepDefinition;

import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.controllers.ViewController;
import es.urjc.jjve.spaceinvaders.entities.Bullet;
import es.urjc.jjve.spaceinvaders.entities.Invader;
import es.urjc.jjve.spaceinvaders.entities.PlayerShip;

public class InvaderMovement {

    PlayerShip ship;
    Invader invader;
    ViewController viewController;
    float x1, x2;

    @Given("^There is a invader on the screen$")
    public void there_ia_a_invader_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        invader = Mockito.mock(Invader.class);
        ArrayList<Invader> invaders = new ArrayList<>();
        invaders.add(invader);
        //Mockito.doCallRealMethod().when(viewController).setInvaders((ArrayList<Invader>) Mockito.any());
        viewController.setInvadersBullets(new ArrayList<Bullet>());
        viewController.setInvaders(invaders);
    }

    @When("^The game updates$")
    public void the_game_updates(){
        x1 = this.viewController.getInvaders().get(0).getX();
        viewController.updateInvaders(1);
    }

    @Then("^The invader moves$")
    public void the_invader_moves(){
        float x2 = this.viewController.getInvaders().get(0).getX();
        Assert.assertNotEquals(x1, x2);
    }
}

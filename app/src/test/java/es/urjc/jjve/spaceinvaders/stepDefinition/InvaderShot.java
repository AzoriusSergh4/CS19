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

public class InvaderShot {
    PlayerShip ship;
    Invader invader;
    ViewController viewController;

    @Given("^There are invaders on the screen$")
    public void there_are_invaders_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        invader = Mockito.mock(Invader.class);
        //Mockito.doCallRealMethod().when(viewController).setPlayerBullets(new ArrayList<Bullet>());
        //Mockito.doCallRealMethod().when(viewController).getInvaders().add((Invader)Mockito.any());
        viewController.setInvadersBullets(new ArrayList<Bullet>());
        viewController.setPlayerShip(ship);
    }

    @When("^A random time is spent$")
    public void a_random_time_is_spent(){
        for(int i = 0; i< 50; i++){
            viewController.updateInvaders(1);
        }
    }

    @Then("^One invader shoots$")
    public void one_invader_shoots(){
        Assert.assertTrue(this.viewController.getInvadersBullets().isEmpty());
    }
}

package es.urjc.jjve.spaceinvaders.stepDefinitionIntegrationTests;

import android.graphics.RectF;

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
    Bullet bullet;

    @Given("^There are invaders on the screen$")
    public void there_are_invaders_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        invader = Mockito.mock(Invader.class);
        bullet = Mockito.mock(Bullet.class);

        Mockito.doCallRealMethod().when(invader).setShipMoving(Mockito.anyInt());
        Mockito.doCallRealMethod().when(invader).setVisible();
        Mockito.doCallRealMethod().when(invader).getVisibility();
        Mockito.doCallRealMethod().when(invader).setRect((RectF) Mockito.any());
        Mockito.doCallRealMethod().when(invader).update(Mockito.anyLong());
        Mockito.doCallRealMethod().when(invader).setShipSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).setX(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(invader).getX();
        invader.setX(10000);
        invader.setRect(Mockito.mock(RectF.class));
        invader.setVisible();
        invader.setShipMoving(2);
        invader.setShipSpeed(40);
        ArrayList<Invader> invaders = new ArrayList<>();
        invaders.add(invader);
        Mockito.doCallRealMethod().when(viewController).setInvaders((ArrayList<Invader>) Mockito.any());
        Mockito.doCallRealMethod().when(viewController).getInvaders();
        Mockito.doCallRealMethod().when(viewController).setInvadersBullets((ArrayList<Bullet>) Mockito.any());
        Mockito.doCallRealMethod().when(viewController).updateInvadersMovement(Mockito.anyLong());
        Mockito.doCallRealMethod().when(viewController).getInvadersBullets();
        Mockito.doCallRealMethod().when(viewController).invaderShoot((Bullet)Mockito.any());

        viewController.setInvadersBullets(new ArrayList<Bullet>());
        viewController.setInvaders(invaders);
    }

    @When("^A random time is spent$")
    public void a_random_time_is_spent(){
        viewController.invaderShoot(bullet);
    }

    @Then("^One invader shoots$")
    public void one_invader_shoots(){
        Assert.assertFalse(this.viewController.getInvadersBullets().isEmpty());
    }
}

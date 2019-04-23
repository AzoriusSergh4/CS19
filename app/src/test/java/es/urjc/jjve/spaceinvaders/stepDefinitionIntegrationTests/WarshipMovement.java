package es.urjc.jjve.spaceinvaders.stepDefinitionIntegrationTests;

import org.junit.Assert;
import org.mockito.Mockito;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.controllers.JoystickController;
import es.urjc.jjve.spaceinvaders.controllers.ViewController;
import es.urjc.jjve.spaceinvaders.entities.Joystick;
import es.urjc.jjve.spaceinvaders.entities.PlayerShip;

public class WarshipMovement {
    PlayerShip ship;
    Joystick joystick;
    ViewController viewController;
    JoystickController joystickController;

    @Given("^There is a warship on the screen$")
    public void there_is_a_warship_on_the_screen(){
        viewController = Mockito.mock(ViewController.class);
        ship = Mockito.mock(PlayerShip.class);
        joystickController = Mockito.mock(JoystickController.class);
        Mockito.doCallRealMethod().when(viewController).setPlayerShip((PlayerShip)Mockito.any());
        Mockito.doCallRealMethod().when(viewController).setJoystickController((JoystickController) Mockito.any());
        Mockito.doCallRealMethod().when(viewController).shipMovement(Mockito.anyFloat(), Mockito.anyFloat());
        Mockito.doCallRealMethod().when(ship).setMovementState(Mockito.anyInt());
        Mockito.doCallRealMethod().when(ship).getMovement();
        Mockito.doCallRealMethod().when(joystickController).shipMovement(Mockito.anyFloat(), Mockito.anyFloat());

        viewController.setJoystickController(joystickController);
        viewController.setPlayerShip(ship);
    }

    @And("^There is a joystick that the user can move$")
    public void there_is_a_joystick(){
        joystick = Mockito.mock(Joystick.class);
        Mockito.doCallRealMethod().when(joystick).setHat(Mockito.anyFloat(), Mockito.anyFloat());
    }

    @When("^The user uses the joystick$")
    public void the_user_uses_the_joystick(){
        viewController.shipMovement(1,3);
    }

    @Then("^The warship moves$")
    public void the_warship_moves(){
        Assert.assertNotEquals(this.ship.STOPPED, this.ship.getMovement());
    }
}

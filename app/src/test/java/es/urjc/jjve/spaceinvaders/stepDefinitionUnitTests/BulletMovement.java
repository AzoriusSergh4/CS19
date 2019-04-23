package es.urjc.jjve.spaceinvaders.stepDefinitionUnitTests;

import org.junit.Assert;
import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.urjc.jjve.spaceinvaders.entities.Bullet;

public class BulletMovement {

    Bullet bullet;

    @Given("^There is a Bullet$")
    public void there_is_a_bullet(){
        bullet = Mockito.mock(Bullet.class);
        Mockito.doCallRealMethod().when(bullet).setHeading(Mockito.anyInt());
        Mockito.doCallRealMethod().when(bullet).setSpeed(Mockito.anyFloat());
        Mockito.doCallRealMethod().when(bullet).getY();
        Mockito.doCallRealMethod().when(bullet).updateMove(Mockito.anyLong());
        bullet.setSpeed(5f);
    }

    @When("^The Bullet movement updates up$")
    public void the_bullet_movement_updates_up(){
        bullet.setHeading(bullet.UP);
    }

    @Then("^The Bullet moves up$")
    public void the_bullet_moves_up(){
        float y1 = this.bullet.getY();
        this.bullet.updateMove(5);
        float y2 = this.bullet.getY();
        boolean up = y2 < y1;

        Assert.assertTrue(up);
    }

    @When("^The Bullet movement updates down$")
    public void the_bullet_movement_updates_down(){
        bullet.setHeading(bullet.DOWN);
    }

    @Then("^The Bullet moves down")
    public void the_bullet_moves_down(){
        float y1 = this.bullet.getY();
        this.bullet.updateMove(5);
        float y2 = this.bullet.getY();
        boolean down = y1 < y2;

        Assert.assertTrue(down);
    }
}

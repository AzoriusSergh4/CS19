package es.urjc.jjve.spaceinvaders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.urjc.jjve.spaceinvaders.entities.PlayerShip;

import static org.junit.Assert.*;

public class InvaderTest {
    SpecialInvader invader;

    @Before
    public void setUp() throws Exception {
        SpaceInvadersActivity spa = new SpaceInvadersActivity();
        invader = new SpecialInvader(spa,1920,1080);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shipTeleport(){
        boolean tp = false;
        for(int i = 0; i<1000; i++){
            float oldX = invader.getX();
            float oldY = invader.getY();
            invader.update(75);
            float newX = invader.getX();
            float newY = invader.getY();
            if((oldY != newY) || (newX == oldX+1)){
                tp = true;
            }
        }
        assertTrue(tp);
    }
}
package es.urjc.jjve.spaceinvaders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.urjc.jjve.spaceinvaders.entities.PlayerShip;

import static org.junit.Assert.*;

public class ShipTest {
    PlayerShip playership;

    @Before
    public void setUp() throws Exception {
        SpaceInvadersActivity sp = new SpaceInvadersActivity();

        playership = new PlayerShip(sp, 1920, 1080);
        playership.setMovementState(1);
    }

    @After
    public void tearDown() throws Exception {
        playership = null;
    }

    @Test
    public void movement(){
        for(int i = 0; i< 50; i++){
            playership.update(1);
        }
        boolean out = playership.getX()<0;
        assertTrue(out);
    }

    @Test
    public void shipTeleport(){
        float oldX = playership.getX();
        float oldY = playership.getY();

        playership.teleport();

        float newX = playership.getX();
        float newY = playership.getY();

        boolean tpConfirmed = (oldX != newX) || (oldY !=newY);

        assertTrue(tpConfirmed);
        
    }
}
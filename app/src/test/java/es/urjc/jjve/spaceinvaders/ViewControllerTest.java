/*package es.urjc.jjve.spaceinvaders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import es.urjc.jjve.spaceinvaders.controllers.ViewController;
import es.urjc.jjve.spaceinvaders.entities.Invader;
import es.urjc.jjve.spaceinvaders.view.SpaceInvadersView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewControllerTest {

    private ViewController viewController;
    private final int SCREEN_X = 1920;
    private final int SCREEN_Y = 1080;



    @Before
    public void setUp() throws Exception {
        SpaceInvadersActivity spa = new SpaceInvadersActivity();
        SpaceInvadersView siv = new SpaceInvadersView(spa, SCREEN_X, SCREEN_Y, false);

        viewController = new ViewController(spa, SCREEN_X, SCREEN_Y, siv);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateInvadersTest(){

        ArrayList<Float> positions = new ArrayList<>();

        for(Invader inv : viewController.getInvaders()){
            float x = inv.getX();
            positions.add(x);
        }

        viewController.updateEntities(100);

        boolean bigger;
        int i = 0;
        for(Invader inv : viewController.getInvaders()){
            float lastX = positions.get(i);
            float newX = inv.getX();
            bigger = lastX < newX;

            assertTrue(bigger);
        }

    }

    @Test
    public void reverseInvadersTest(){

        int direction;

        viewController.reverse();

        for (Invader inv: viewController.getInvaders()){
            direction = inv.getShipMoving();
            assertEquals(1, direction);
        }
    }

}*/

package es.urjc.jjve.spaceinvaders.controllers;

import android.content.Context;
import android.graphics.RectF;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.urjc.jjve.spaceinvaders.entities.Bullet;
import es.urjc.jjve.spaceinvaders.entities.DefenceBrick;
import es.urjc.jjve.spaceinvaders.entities.Invader;
import es.urjc.jjve.spaceinvaders.entities.PlayerShip;
import es.urjc.jjve.spaceinvaders.entities.SpecialInvader;
import es.urjc.jjve.spaceinvaders.view.SpaceInvadersView;

/**
 * Created by Christian on 03/10/2018.
 */

public class ViewController {

    private static final Logger LOGGER = Logger.getLogger(ViewController.class.getName());

    // Up to 60 invaders
    private List<Invader> invaders;
    private int numInvaders = 0;
    private int killedInvaders = 0;
    private boolean underage;
    private SpaceInvadersView view;
    private PlayerShip playerShip;
    private int score;

    // The invaders bullets
    private List<Bullet> invadersBullets;
    private List<Bullet> playerBullets;
    private List<Bullet> godBullets;

    // The player's shelters are built from bricks
    private List<DefenceBrick> bricks;

    private int godMode = 0;

    private Invader specialInvader;


    //Determines the game bounds
    private int screenX;
    private int screenY;

    private JoystickController joystickController = new JoystickController();


    public ViewController(Context context, int x, int y, SpaceInvadersView view) {

        this.screenX = x;
        this.screenY = y;

        this.view = view;
        this.playerBullets = new ArrayList<>();

        this.godBullets = new ArrayList<>();

        this.initGame(context);

    }


    /**
     * This method is used every game tick to update the positions of every entity and the score
     * Should be called when the entity is modified only
     */
    public void updateGame() {

        view.lockCanvas();
        view.drawBackground();
        view.drawJoystick();    //Botón de Joystick
        view.drawButton();      //Botón disparo

        paintInvaders();
        paintBricks();
        paintBullets();
        paintShip();


        view.drawGameObject("Score: " + score, 10, 50);
        view.unlockCanvas();
    }

    private void paintBullets() {

        for (Bullet shipBull : this.playerBullets) {
            view.drawGameObject(shipBull.getBitmapBullet(),shipBull.getX(),shipBull.getY());
        }

        for (Bullet bullet : invadersBullets) {
            if (bullet.getStatus()) {
                view.drawGameObject(bullet.getBitmapBullet(),bullet.getX(),bullet.getY());
            }
        }
        for (Bullet bullet : godBullets) {
            if (bullet.getStatus()) {
                view.drawGameObject(bullet.getBitmapBullet(),bullet.getX(),bullet.getY());
            }
        }
    }

    /**
     * This method is used to update the entity position and the actions they are going to take
     * it checks if the invaders have reached the screen limit, and if they bumped into a brick barrier
     * also it checks if the invader has the opportunity to shoot
     * updates the bullet possition depending on the fps attribute
     */
    public boolean updateEntities(long fps) {

        //moves the spaceship
        playerShip.update(fps);
        shipColisionBrick();

        updateInvaders(fps);

        if(shipColisionInvaders()){
            return false;
        }

        if(specialInvader!= null) {
            specialInvader.update(fps);
        }

        //If its not the underage version, we need to update the bullets
        if(!underage) {
            updateInvadersBullet(fps);
            updatePlayerBullet(fps);
            updateGodBullets(fps);
        }



        // Has the player won
        return (killedInvaders != numInvaders);

    }

    public void reverse() {

        for (Invader inv : invaders) {
            inv.dropDownAndReverse();
        }
    }


    private boolean shipColisionInvaders(){
        boolean lost=false;

        for(Invader inv:invaders) {
            if ((inv.getVisibility()) && (godMode <= 0) && (RectF.intersects(playerShip.getRect(), inv.getRect()))) {
                inv.setInvisible();
                lost= true;
            }
        }
        return lost;
    }

    /**
     *
     * @param inv
     * Checks if inv collides with any defence brick, if so the brick is invisible, the invader dies and the colour of ship and invaders are changed
     */
    private void invaderColisionBrick(Invader inv){
        for (DefenceBrick brick : bricks) {
            if ((brick.getVisibility()) && (RectF.intersects(inv.getRect(), brick.getRect()))) {
                // A collision has occurred
                inv.setInvisible();
                brick.setInvisible();
                changeColors();
            }
        }
    }

    /**
     * Checks if the ship has collided with a shelter brick, if so the brick is destroyed and the colour of ship and invaders changed
     */
    private void shipColisionBrick(){
        for (DefenceBrick brick : bricks) {
            if ((brick.getVisibility()) && (RectF.intersects(playerShip.getRect(), brick.getRect()))) {
                // A collision has occurred
                brick.setInvisible();
                changeColors();
            }
        }
    }

    /**
     *
     * @param brick
     * @param currentBull
     * Checks if currentBull has collided with brick
     */
    private void bulletColisionBrick(DefenceBrick brick, Bullet currentBull) {
        if ((brick.getVisibility()) && (RectF.intersects(currentBull.getRect(), brick.getRect()))) {
            // A collision has occurred
            currentBull.setInactive();
            brick.setInvisible();
            changeColors();
        }
    }

    /**
     *
     * @param inv
     * @param currentBull
     * checks if currentBull collides with inv, if so the score is added depending on the type of invader
     */
    private void bulletCollisionInvader(Invader inv, Bullet currentBull){
        if ((inv.getVisibility()) && (RectF.intersects(currentBull.getRect(), inv.getRect()))) { //Has a bullet hit an invader?
            inv.setInvisible();
            currentBull.setInactive();
            if(inv == specialInvader){
                score = score + 250;
            }else {
                score = score + 100;
            }
            /**
             * If 500 points are scored, the ship teleports
             * giving it a second of invulnerability
             */
            if(score%500==0){
                posicionRandom();
                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        godMode--;
                        if (godMode== 0) {
                            timer.cancel();
                        }
                    }}, 0, 100);
            }
            killedInvaders++;
        }
    }

    /**
     * Changes the colour of the invaders and the ship
     */
    private void changeColors() {
        for (int x = 0; x < numInvaders; x++) {
            invaders.get(x).chColour();
        }
        playerShip.chColour();
    }

    private void updateInvaders(long fps){
        //For each invader, we check if its an active one and then we check if it has the opportunity to shoot
        //if the invader has reached the screen limit, it reverses the direction and goes down
        for (Invader inv : invaders) { //For each visible invader, if there is room in the array,and it wants to shoot
            //The bullet is shot
            if (inv.getVisibility()) {
                inv.update(fps); // Move the next invader

                //If its not the underage version, the invaders shoot, else they don´t
                int maxInvaderBullets = 10;
                if((!underage) && (invadersBullets.size() < maxInvaderBullets) && (inv.takeAim(playerShip.getX(), playerShip.getLength()))) {
                    Bullet newBullet = new Bullet(screenY, this.view.getContext());
                    invadersBullets.add(newBullet);
                    newBullet.shoot(inv.getX() + inv.getLength() / 2, inv.getY(), newBullet.DOWN); // If so try and spawn a bullet// Shot fired, Prepare for the next shot
                    // Loop back to the first one if we have reached the last
                }

                // If that move caused them to bump the screen change bumped to true
                if (inv.getX() > screenX - inv.getLength() || inv.getX() < 0) {
                    reverse();
                }

                invaderColisionBrick(inv);
            }
        }
    }

    //Actualizar balas nave
    private void updatePlayerBullet(long fps){
        for (int i=0;i<playerBullets.size();i++) {
            Bullet currentBull = playerBullets.get(i);
            currentBull.update(fps);
            //Colisión de bala con invader
            if(currentBull.getStatus()) {
                for (Invader inv : invaders) {
                    bulletCollisionInvader(inv, currentBull);
                }
                //Colisión de bala con muros
                for (DefenceBrick brick : bricks) {
                    bulletColisionBrick(brick, currentBull);
                }
                bulletCollisionInvader(specialInvader,currentBull);
                //Colisión de bala con borde de pantalla
                if (currentBull.getImpactPointY() < 0) {

                    currentBull.changeDir();
                    currentBull.setGodBullet();
                    //Recargar bala cuando rebote

                    Bullet nextBull = new Bullet(screenY, this.getView().getContext());
                    playerBullets.add(nextBull);
                }
                if (currentBull.getImpactPointY() > screenY){
                    currentBull.changeDir();
                }
            }

        }
    }

    //Actualizar balas dios
    private void updateGodBullets(long fps){

        for (Bullet bullet : godBullets) {
            if (bullet.getStatus()) {
                bullet.update(fps);

                if (bullet.getImpactPointY() > screenY) {
                    bullet.changeDir();
                }
                if (bullet.getImpactPointY() < 0) {
                    bullet.changeDir();
                }

                //Checks the colision of the god bullet with the defence bricks
                for (DefenceBrick brick : bricks) {
                    bulletColisionBrick(brick, bullet);
                }

                //Checks the colision of the god bullet with the invaders
                for (Invader inv : invaders) {
                    bulletCollisionInvader(inv, bullet);
                }
            }
        }
    }

    /**
     *
     * @param fps
     *
     * Actualiza el movimiento de las balas del invader, si chocan con el límite inferior se transforman en godBullets
     * Si chocan con un muro se cambia el color de los personajes
     * Si chocan con la nave termina el juego
     */
    private void updateInvadersBullet(long fps){
        for (Bullet bullet : invadersBullets) {
            if (bullet.getStatus()) {
                bullet.update(fps);

                if (bullet.getImpactPointY() > screenY) {
                    bullet.changeDir();
                    bullet.setGodBullet();
                }

                //Check the colision with the defence bricks
                for(DefenceBrick brick:bricks) {
                    bulletColisionBrick(brick,bullet);
                }
            }
        }
    }


    // If SpaceInvadersActivity is started then
    // start our thread.

    private void initGame(Context context) {
        // Make a new player space ship
        playerShip = new PlayerShip(context, screenX, screenY);

        this.invaders = new ArrayList<>();
        this.invadersBullets = new ArrayList<>();
        this.bricks = new ArrayList<>();

        // Reset the menace level

        // Build an army of invaders
        numInvaders = 0;
        for (int column = 0; column < 6; column++) {
            for (int row = 0; row < 5; row++) {
                invaders.add(new Invader(context, row, column, screenX, screenY));
                numInvaders++;
            }
        }

        // Build the shelters
        for (int shelterNumber = 0; shelterNumber < 4; shelterNumber++) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    bricks.add(new DefenceBrick(row, column, shelterNumber, screenX, screenY));
                }
            }
        }
    }



    private void paintShip() {
        view.drawGameObject(playerShip.getBitmap(), playerShip.getX(), playerShip.getY());
    }

    private void paintInvaders() {
        for (Invader i : invaders) {
            if (i.getVisibility()) {
                this.view.drawGameObject(i.getBitmap(), i.getX(), i.getY());
            }
        }
        if(specialInvader!=null) {
            this.view.drawGameObject(specialInvader.getBitmap(), specialInvader.getX(), specialInvader.getY());
        }
    }

    private void paintBricks() {
        for (DefenceBrick b : bricks) {
            try {
                if (b.getVisibility()) {
                    view.drawGameObject(b.getRect());
                }
            } catch (RuntimeException e) {
                LOGGER.log(Level.INFO, e.getMessage());
            }
        }
    }

    private SpaceInvadersView getView() {
        return view;
    }

    public void setUnderage(boolean underage) {
        this.underage = underage;
    }

    public void notifyShoot() {

        if(playerBullets.isEmpty()) {
            Bullet newBull = new Bullet(screenY,this.getView().getContext());

            this.playerBullets.add(newBull);
            newBull.shoot((playerShip.getX() + playerShip.getLength()/2), playerShip.getY(), 0);
        }
    }

    /**
     * Remove the inactiveBullets
     */
    public void removeBullets() {
        List<Bullet> inactive = new ArrayList<>();
        for (Bullet b : playerBullets) {
            if (!b.getStatus()) {
                inactive.add(b);
            }
        }
        for(Bullet b:invadersBullets){
            if(!b.getStatus()){
                inactive.add(b);
            }
        }
        for(Bullet godB:godBullets){
            if (!godB.getStatus()){
                inactive.add(godB);
            }
        }
        for (Bullet b : inactive) {
            invadersBullets.remove(b);
            playerBullets.remove(b);
            godBullets.remove(b);
        }
    }


    public void shipMovement(float x, float y) {
        int movement = this.joystickController.shipMovement(x, y);
        playerShip.setMovementState(movement);
    }

    public int getScore() {
        return score;
    }

    public void specialInvader(Context context) {
        this.specialInvader = new SpecialInvader(context,screenX,screenY);
    }

    public void posicionRandom(){
        playerShip.teleport();
        paintShip();
        godMode=15;
    }

    public List<Invader> getInvaders() {
        return invaders;
    }
}

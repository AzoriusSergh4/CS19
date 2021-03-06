package es.urjc.jjve.spaceinvaders.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import es.urjc.jjve.spaceinvaders.R;

public class PlayerShip {

    RectF rect;

    // En qué direcciones se puede mover la nave espacial
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    public final int DOWN = 3;
    public final int UP=4;
    public final int D_DL = 5;
    public final int D_DR = 6;
    public final int D_UL = 7;
    public final int D_UR = 8;

    private int movement;
   //Cantidad de disparos con los que cuenta la nave a la vez

    // La nave espacial del jugador será representada por un Bitmap
    private Bitmap bitmap;

    // Más otro para cambiar el esquema de color
    private Bitmap bitmap2;

    // Establecemos los esquemas de colores que vamos a usar
    private final int ESQUEMA_1 = 1;
    private final int ESQUEMA_2 = 2;

    // Variable para guardar el esquema actual, por defecto el primero de ellos.
    private int seleccionado = ESQUEMA_1;

    private List<Bullet> activeBullets= new ArrayList<>();

    // Que tan ancho y alto puede llegar nuestra nave espacial
    private float length;
    private float height;

    //Tamaño de la pantalla

    private int screenX;
    private int screenY;

    // X es la parte extrema a la izquierda del rectángulo el cual forma nuestra nave espacial
    private float x;

    // Y es la coordenada de a mero arriba
    private float y;

    // Esto va a mantener la rapidez de los pixeles por segundo a la que la nave espacial se moverá
    private float shipSpeed;

    // Se esta moviendo la nave espacial y en que dirección
    private int shipMoving = STOPPED;

    // Este es el método del constructor
    // Cuando creamos un objeto de esta clase daremos
    // la anchura y la altura de la pantalla
    public PlayerShip(Context context, int screenX, int screenY){

        // Inicializa un RectF vacío
        rect = new RectF();

        this.screenX = screenX;
        this.screenY = screenY;

        length = screenX/10;
        height = screenY/10;

        // Inicia la nave en el centro de la pantalla aproximadamente
        x = screenX / 2;
        y = screenY - 20;

        // Inicializa el bitmap
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.playership);

        // Y otro para el color!!!!
        bitmap2 = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.playership2);

        // ajusta el bitmap a un tamaño proporcionado a la resolución de la pantalla
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        // ajusta el bitmap a un tamaño proporcionado a la resolución de la pantalla
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,
                (int) (length),
                (int) (height),
                false);

        // Qué tan rápido va la nave espacial en pixeles por segundo
        shipSpeed = 350;
    }

    public RectF getRect(){
        return rect;
    }

    // Este es un método de "get" para hacer el rectángulo que
    // define nuestra nave espacial disponible en la clase de SpaceInvadersView
    public Bitmap getBitmap(){
        if (seleccionado == ESQUEMA_1) {
            return bitmap;
        } else {
            return bitmap2;
        }
    }

    public float getX(){
        return x;
    }

    public float getLength(){
        return length;
    }

    // Este método será usado para cambiar/establecer si la nave
    // espacial va a la izquierda, la derecha o no se mueve
    public void setMovementState(int movement){

        this.movement=movement;
    }



    public void chColour() {
        if (seleccionado == ESQUEMA_1) {
            seleccionado = ESQUEMA_2;
        } else {
            seleccionado = ESQUEMA_1;

        }
    }




    // Este método de update será llamado desde el update en SpaceInvadersView
    // Determina si la nave espacial del jugador necesita moverse y cambiar las coordenadas
    // que están en x si es necesario
    public void update(long fps){

        switch (this.movement) {
            case LEFT:
                x = x - shipSpeed / fps;
                break;
            case RIGHT:
                x = x + shipSpeed / fps;
                break;
            case UP:
                y = y + shipSpeed / fps;
                break;
            case DOWN:
                y = y - shipSpeed / fps;
                break;
            case D_DL:
                y = y - shipSpeed / fps;
                x = x - shipSpeed / fps;
                break;
            case D_DR:
                y = y - shipSpeed / fps;
                x = x + shipSpeed / fps;
                break;
            case D_UL:
                y = y + shipSpeed / fps;
                x = x - shipSpeed / fps;
                break;
            case D_UR:
                y = y + shipSpeed / fps;
                x = x + shipSpeed / fps;
                break;
            default:
                break;

        }
        // Actualiza rect el cual es usado para detectar impactos
        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }

    public void updateMove(long fps){

        switch (this.movement) {
            case LEFT:
                x = x - shipSpeed / fps;
                break;
            case RIGHT:
                x = x + shipSpeed / fps;
                break;
            case UP:
                y = y + shipSpeed / fps;
                break;
            case DOWN:
                y = y - shipSpeed / fps;
                break;
            case D_DL:
                y = y - shipSpeed / fps;
                x = x - shipSpeed / fps;
                break;
            case D_DR:
                y = y - shipSpeed / fps;
                x = x + shipSpeed / fps;
                break;
            case D_UL:
                y = y + shipSpeed / fps;
                x = x - shipSpeed / fps;
                break;
            case D_UR:
                y = y + shipSpeed / fps;
                x = x + shipSpeed / fps;
                break;
            default:
                break;

        }

    }

    public void teleport(){
        float x =(float) (Math.random()*(screenX))+0;
        float y =(float) (Math.random()*(screenY))+0;
        this.setX(x);
        this.setY(y);
    }



    public float getY() {
        return y;
    }

    public void setY(float y){
        this.y=y;
    }
    public void setX(float x){
        this.x=x;
    }

    public int getMovement() {
        return movement;
    }

    public void setShipSpeed(float f){
        this.shipSpeed = f;
    }
}

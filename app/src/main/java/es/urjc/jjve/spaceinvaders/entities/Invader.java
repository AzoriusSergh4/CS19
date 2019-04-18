package es.urjc.jjve.spaceinvaders.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

import es.urjc.jjve.spaceinvaders.R;

public class Invader {
    RectF rect;

    //-------
    Random generator = new Random();

    // La nave espacial del jugador va a ser representada por un Bitmap
    protected Bitmap bitmap1;
    protected Bitmap bitmap2;

    // Creamos otros dos bitmaps para hacer el cambio de colores
    protected Bitmap bitmap3;
    protected Bitmap bitmap4;

    // Establecemos los esquemas de colores que vamos a usar
    protected final int ESQUEMA_1 = 1;
    protected final int ESQUEMA_2 = 2;

    // Variable para guardar el esquema actual, por defecto el primero de ellos.
    protected int seleccionado = ESQUEMA_1;

    // Qué tan largo y ancho será nuestro Invader
    protected float length;
    protected float height;

    // X es el extremo a la izquierda del rectángulo que le da forma a nuestro invader
    protected float x;

    // Y es la coordenada superior
    protected float y;

    // Esto mantendrá la rapidez de los pixeles por segundo a la que el invader se moverá.
    protected float shipSpeed;

    public final int LEFT = 1;
    public final int RIGHT = 2;

    // Se está moviendo la nave espacial y en qué dirección
    protected int shipMoving = RIGHT;

    boolean isVisible;

    public Invader(Context context, int row, int column, int screenX, int screenY) {

        // Inicializa un RectF vacío
        rect = new RectF();

        length = screenX / 20;
        height = screenY / 20;

        isVisible = true;

        int padding = screenX / 25;

        x = column * (length + padding);
        y = row * (length + padding/4);

        // Inicializa el bitmap
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader2);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader12);
        bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader22);

        // Ajusta el primer bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap1 = Bitmap.createScaledBitmap(bitmap1,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el segundo bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el tercer bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap3 = Bitmap.createScaledBitmap(bitmap3,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el cuarto bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap4 = Bitmap.createScaledBitmap(bitmap4,
                (int) (length),
                (int) (height),
                false);

        // Qué tan rápido va el invader en pixeles por segundo
        shipSpeed = 40;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public void setVisible(){
        this.isVisible = true;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){
        if (seleccionado == ESQUEMA_1) {
            return bitmap1;
        } else {
            return bitmap3;
        }
    }

    public Bitmap getBitmap2(){
        if (seleccionado == ESQUEMA_1) {
            return bitmap2;
        } else {
            return bitmap4;
        }
    }

    public void chColour(){
        if (seleccionado == ESQUEMA_1){
            seleccionado = ESQUEMA_2;
        } else {
            seleccionado = ESQUEMA_1;
        }
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getLength(){
        return length;
    }

    public float getHeight() {
        return height;
    }

    public void update(long fps){
        if(shipMoving == LEFT){
            x = x - shipSpeed / fps;
        }

        if(shipMoving == RIGHT){
            x = x + shipSpeed / fps;
        }

        // Actualiza rect el cual es usado para detectar impactos
        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;

    }

    public void dropDownAndReverse(){
        if(shipMoving == LEFT){
            shipMoving = RIGHT;
        }else{
            shipMoving = LEFT;
        }

        y = y + height;

        shipSpeed = shipSpeed * 1.18f;
    }

    public boolean takeAim(float playerShipX, float playerShipLength){

        int randomNumber = -1;

        // Si está cerca del jugador
        if((playerShipX + playerShipLength > x &&
                playerShipX + playerShipLength < x + length) || (playerShipX > x && playerShipX < x + length)) {

            // Una probabilidad de 1 en 500 chance para disparar
            randomNumber = generator.nextInt(150);
            if(randomNumber == 0) {
                return true;
            }

        }

        // Si está disparando aleatoriamente (sin estar cerca del jugador) una probabilidad de 1 en 5000
        randomNumber = generator.nextInt(2000);
        return randomNumber == 0;

    }

    public void setInvaderSpecial(Context context){
        this.bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_invader);
        this.bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_invader);
        this.bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_invader);
        this.bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_invader);
        bitmap1 = Bitmap.createScaledBitmap(bitmap1,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el segundo bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el tercer bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap3 = Bitmap.createScaledBitmap(bitmap3,
                (int) (length),
                (int) (height),
                false);

        // Ajusta el cuarto bitmap a un tamaño apropiado para la resolución de la pantalla
        bitmap4 = Bitmap.createScaledBitmap(bitmap4,
                (int) (length),
                (int) (height),
                false);
        this.shipSpeed=75;
        this.shipMoving=RIGHT;

    }

    public void setShipMoving(int shipMoving) {
        this.shipMoving = shipMoving;
    }

    public int getShipMoving() {
        return shipMoving;
    }

    public void setRect(RectF r){
        this.rect = r;
    }

    public void setShipSpeed(float shipSpeed) {
        this.shipSpeed = shipSpeed;
    }

    public void setX(float x) {
        this.x = x;
    }
}

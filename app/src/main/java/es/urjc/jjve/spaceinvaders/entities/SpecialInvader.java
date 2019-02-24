package es.urjc.jjve.spaceinvaders.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import es.urjc.jjve.spaceinvaders.R;

public class SpecialInvader extends Invader {

    //Tamaño de la pantalla

    private int screenX;
    private int screenY;

    public SpecialInvader(Context context, int screenX, int screenY) {
        super(context, 0, 0, screenX, screenY);
        this.screenX = screenX;
        this.screenY = screenY;

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

    public void teleport(){
        float x =(float) (Math.random()*(screenX))+0;
        float y =(float) (Math.random()*(screenY))+0;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(long fps) {
        super.update(fps);
        int n =(int) (Math.random()*(50));
        if(n == 5){
            teleport();
        }
    }



}


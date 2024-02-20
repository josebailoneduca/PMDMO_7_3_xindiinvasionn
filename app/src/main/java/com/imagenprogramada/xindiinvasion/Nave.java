package com.imagenprogramada.xindiinvasion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Nave {
    Juego juego;


    int xNave;
    int yNave;
    Bitmap nave;
    public Nave(Juego juego) {
        this.juego = juego;
        nave = BitmapFactory.decodeResource(juego.getResources(),
                R.drawable.starship);
        xNave= (juego.anchoPantalla/2)-(nave.getWidth()/2);//horizontally, in the middle
        yNave= (juego.altoPantalla/5)*4;//fixed vertical position at 4/5 of the screen
    }


    public void render(Canvas canvas){
        canvas.drawBitmap(nave,xNave,yNave,null);
    }

    public int getHeight() {
        return nave.getHeight();
    }

    public int getX() {
        return xNave;
    }
    public void setX(float x){
        xNave=(int)x;
    }
    public int getWidth() {
        return nave.getWidth();
    }

    public void actualizar(boolean izquierda, boolean derecha, boolean derrota) {

        if (!derrota) {
            //aCTUALIZAR NAVE
            if (izquierda) {
                if (xNave > 0)
                    xNave = xNave - juego.VELOCIDAD_HORIZONTAL;
            }
            if (derecha) {
                if (xNave < juego.anchoPantalla - nave.getWidth())
                    xNave = xNave + juego.VELOCIDAD_HORIZONTAL;
            }
        }
    }

    public float getY() {
        return yNave;
    }

    public void fin() {
        nave.recycle();
    }

    public Bitmap getBitmap() {
        return nave;
    }
}

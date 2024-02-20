package com.imagenprogramada.xindiinvasion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class Explosion {
    private int ancho_sprite;
    private int alto_sprite;
    private final int NUMERO_IMAGENES_EN_SECUENCIA=17;
    public float coordenada_x, coordenada_y; //screen coordinates of the explosion
    private Juego juego;
    private int estado;
    private MediaPlayer mediaPlayer; //to reproduce the explosion sound
    /*Constructor with explosion's coordinates*/
    public Explosion(Juego j, float x, float y){
        coordenada_x=x;
        coordenada_y=y;
        juego=j;
        ancho_sprite=juego.explosion.getWidth()/NUMERO_IMAGENES_EN_SECUENCIA;
        alto_sprite=juego.explosion.getHeight();
        estado=-1; //Just created

        mediaPlayer=MediaPlayer.create(j.getContext(),
                R.raw.explosion);//replace for Explosion class
        mediaPlayer.setOnCompletionListener(new
                                                    MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            mp.release();
                                                        }
                                                    });
        mediaPlayer.start();
    }
    public void actualizarEstado(){
        //next frame
        estado++;
    }
    public void dibujar(Canvas c, Paint p){
        int posicionSprite=estado*ancho_sprite;
        if(!haTerminado()) {
            //Calculating the coordinates of the current animation frame
            //in the explosion sequence bitmap
            Rect origen = new Rect(posicionSprite, 0, posicionSprite + ancho_sprite,
                    alto_sprite);
            //Calculating where to render the sprite on the screen
            Rect destino = new Rect((int)coordenada_x,(int) coordenada_y,
                    (int) coordenada_x + ancho_sprite,
                    (int) coordenada_y + juego.explosion.getHeight());
            c.drawBitmap(juego.explosion, origen, destino, p);
        }

    }
    public boolean haTerminado(){
        return estado>=NUMERO_IMAGENES_EN_SECUENCIA;
    }
}
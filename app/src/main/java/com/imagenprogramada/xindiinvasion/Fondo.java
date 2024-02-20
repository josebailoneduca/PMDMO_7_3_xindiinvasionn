package com.imagenprogramada.xindiinvasion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Fondo {
    private Bitmap fondo; //Aux bitmap to load resources into the array
    public static final int MAX_IMAGENES_FONDO= 6; //Number of pictures to cycle through
    Bitmap imagenes[]=new Bitmap[MAX_IMAGENES_FONDO]; //Background picture array
    /*Background resources array*/
    int recursos_imagenes[]={R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,
            R.drawable.bg4,R.drawable.bg5,R.drawable.bg6};
    //Current y coordinates of the current and next background
    int yImgActual,yImgSiguiente;
    /*Indexes of the current and next background in the imagenes[] array*/
    int img_actual=0,img_siguiente=1;

    Juego juego;

    public Fondo(Juego juego){
        this.juego=juego;
        yImgActual=0;
        yImgSiguiente=-juego.altoPantalla;
        cargaBackground();
    }





    public void cargaBackground(){
        for(int i=0;i<6;i++) {
            fondo = BitmapFactory.decodeResource(
                    juego.getResources(), recursos_imagenes[i]);
            if(imagenes[i]==null)
                imagenes[i] = fondo.createScaledBitmap(
                        fondo, juego.anchoPantalla, juego.altoPantalla, true);
            fondo.recycle();
        }
    }


    public void actualizaFondo(){
        //Update y coordinates
        yImgActual++;
        yImgSiguiente++;
        /*If the current picture has disappeared completely*/
        if(yImgActual> juego.altoPantalla){
            //Update img_actual
            if(img_actual==MAX_IMAGENES_FONDO-1)
                img_actual=0;
            else
                img_actual++;
            //Update img_siguiente
            if(img_siguiente==MAX_IMAGENES_FONDO-1)
                img_siguiente=0;
            else
                img_siguiente++;
            //Reset y coordinates
            yImgActual=0;
            yImgSiguiente=-juego.altoPantalla;
        }
    }

    public void renderizar(Canvas canvas){
        canvas.drawBitmap(imagenes[img_actual],0,yImgActual,null);
        canvas.drawBitmap(imagenes[img_siguiente],0,yImgSiguiente,null);

    }

    public void fin() {
        for(int i=0;i<MAX_IMAGENES_FONDO;i++)
            imagenes[i].recycle();
    }
}

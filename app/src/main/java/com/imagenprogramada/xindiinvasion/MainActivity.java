package com.imagenprogramada.xindiinvasion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciaMusicaIntro();
        findViewById(R.id.btnEmpezar).setOnClickListener(view -> {
            mediaPlayer.stop();mediaPlayer.reset();
            this.startActivity(new
                    Intent(this, ActividadJuego.class));
        });
    }
    public void iniciaMusicaIntro(){
        mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.setVolume(0.5f,0.5f);
        mediaPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.start();
                    }
                });
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

}
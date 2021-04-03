package com.example.todoliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


public class splashScreen extends AppCompatActivity {
    TextView logo;
    TextView tvsplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Buat Objek Handler
        Handler handler = new Handler();
//        Pakai handler
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashScreen.this , MainActivity.class));
                finish();
            }
        }, 4000);
// Animation
        logo = (TextView)findViewById(R.id.logo);
        tvsplash = (TextView)findViewById(R.id.tvsplash);
//        Import animasi
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.one);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.two );
//        Eksekusi animasi
        logo.startAnimation(animation);
        tvsplash.startAnimation(animation1);

    }
}
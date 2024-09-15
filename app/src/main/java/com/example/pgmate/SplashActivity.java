package com.example.pgmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView ivlogo;
    TextView tvTitle,tvSubtitle;
    Animation animTranslate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivlogo = findViewById(R.id.ivMainLogo);
        tvTitle = findViewById(R.id.tvMainTitle);
        tvSubtitle = findViewById(R.id.tvsubTitle);

        animTranslate = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.toptobottomtranslate);
        ivlogo.setAnimation(animTranslate);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,WelcomeActivity.class);
                startActivity(i);
            }
        },3000);
    }
}
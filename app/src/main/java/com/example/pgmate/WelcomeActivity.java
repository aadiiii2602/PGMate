package com.example.pgmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {
    Button btnWelcomeLogin , btnWelcomeSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnWelcomeLogin = findViewById(R.id.btnWelcomeLogin);
        btnWelcomeSignUp = findViewById(R.id.btnWelcomeSignUp);

        btnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(WelcomeActivity.this, "Login Page Opened", Toast.LENGTH_SHORT).show();
            }
        });

        btnWelcomeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                startActivity(i);
                Toast.makeText(WelcomeActivity.this, "New User Register Here", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
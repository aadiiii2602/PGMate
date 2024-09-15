package com.example.pgmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername , etPassword;
    TextView tvForgetPassword, tvsignup;
    CheckBox cbShowHidePassword;
    Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etMyProfileUsername);
        etPassword = findViewById(R.id.etMyProfilePassword);
        tvForgetPassword = findViewById(R.id.tvLoginForgotPassword);
        cbShowHidePassword = findViewById(R.id.cbNewUserShowHidePassword);
        btnLogin = findViewById(R.id.btnLoginLogin);
        tvsignup  = findViewById(R.id.tvsignup);


        cbShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Please Enter Your Username");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please Enter Your Password");
                } else if (etUsername.getText().toString().length() < 8) {
                    etUsername.setError("Please Enter 8 Character Username");
                } else if (etPassword.getText().toString().length() < 8) {
                    etPassword.setError("Please Enter 8 Character Password");
                } else if (!etUsername.getText().toString().matches(".*[A-Z].*")) {
                    etUsername.setError("Please Used 1 Uppercase Letter");
                } else if (!etUsername.getText().toString().matches(".*[a-z].*")) {
                    etUsername.setError("Please Used 1 Lowercase Letter");
                } else if (!etUsername.getText().toString().matches(".*[0-9].*")) {
                    etUsername.setError("Please Used 1 number");
                } else if (!etUsername.getText().toString().matches(".*[@,#,$,&,!].*")) {
                    etUsername.setError("Please Used 1 Special Symbol");
                } else if (!etPassword.getText().toString().matches(".*[A-Z].*")) {
                    etPassword.setError("Please Used 1 Uppercase Letter");
                } else if (!etPassword.getText().toString().matches(".*[a-z].*")) {
                    etPassword.setError("Please Used 1 Lowercase Letter");
                } else if (!etPassword.getText().toString().matches(".*[0-9].*")) {
                    etPassword.setError("Please Used 1 number");
                } else if (!etPassword.getText().toString().matches(".*[@,#,$,&,!].*")) {
                    etPassword.setError("Please Used 1 Special Symbol");
                } else {

                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    Toast.makeText(LoginActivity.this, "Successfully Done", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }


        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
                Toast.makeText(LoginActivity.this, "New User Login Here", Toast.LENGTH_SHORT).show();
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, OtpVerificationActivity.class);
                startActivity(i);
                Toast.makeText(LoginActivity.this, "Get OTP Here", Toast.LENGTH_SHORT).show();
            }
        });
    }

}



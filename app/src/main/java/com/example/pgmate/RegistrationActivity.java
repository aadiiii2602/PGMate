package com.example.pgmate;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {
    EditText etName, etMobileno, etEmailid, etUsername, etPassword;
    Button btnRegister;
    CheckBox cbShowHidePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.tvRegistrationName);
        etMobileno = findViewById(R.id.tvRegistrationMobileno);
        etEmailid = findViewById(R.id.tvRegistrationEmail);
        etUsername = findViewById(R.id.tvMyProfileUsername);
        etPassword = findViewById(R.id.tvMyProfilePassword);
        btnRegister = findViewById(R.id.btnLoginLogin);
        cbShowHidePassword = findViewById(R.id.cbNewUserShowHidePassword);

        cbShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty()) {
                    etName.setError("Please Enter Your Name");
                } else if (etMobileno.getText().toString().isEmpty()) {
                    etMobileno.setError("Please Enter Your Mobile Number");
                } else if (etMobileno.getText().toString().length() != 10) {
                    etMobileno.setError("Please Enter valid Mobile Number");
                } else if (etEmailid.getText().toString().isEmpty()) {
                    etEmailid.setError("Please Enter Valid Email ID");
                } else if (!etEmailid.getText().toString().contains(".com") ||
                        !etEmailid.getText().toString().contains("@")) {
                    etEmailid.setError("Please Enter Valid Email ID");
                } else if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Please Enter Your Username");
                } else if (etUsername.getText().toString().length() < 8) {
                    etUsername.setError("Username must be greater than 8");
                } else if (!etUsername.getText().toString().matches(".*[A-Z].*")) {
                    etUsername.setError("Please Used At Least 1 Uppercase Letter");
                } else if (!etUsername.getText().toString().matches(".*[a-z].*")) {
                    etUsername.setError("Please   Used at least 1 Lowercase Letter");
                } else if (!etUsername.getText().toString().matches(".*[0-9].*")) {
                    etUsername.setError("Please  Used at least 1 Number");
                } else if (!etUsername.getText().toString().matches(".*[@,#,$,!].*")) {
                    etUsername.setError("Please  enter At least 1 special symbol");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please Enter Your Password");
                } else if (etPassword.getText().toString().length() < 8) {
                    etPassword.setError("Password must be greater than 8");
                } else {
                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(i);
            }
        }
    });
}
}

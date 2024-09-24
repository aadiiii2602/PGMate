package com.example.pgmate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class OtpVerificationActivity extends AppCompatActivity {

    TextView tvMobileNo, tvResentOTP;
    EditText etOTPVerification1, etOTPVerification2, etOTPVerification3, etOTPVerification4, etOTPVerification5, etOTPVerification6;
    AppCompatButton btnVerify;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverificationactivity);

        tvMobileNo = findViewById(R.id.tvVerifyOTPMobileNo);
        tvResentOTP = findViewById(R.id.tvResendOTP);
        etOTPVerification1 = findViewById(R.id.etOTPVerification1);
        etOTPVerification2 = findViewById(R.id.etOTPVerification2);
        etOTPVerification3 = findViewById(R.id.etOTPVerification3);
        etOTPVerification4 = findViewById(R.id.etOTPVerification4);
        etOTPVerification5 = findViewById(R.id.etOTPVerification5);
        etOTPVerification6 = findViewById(R.id.etOTPVerification6);
        btnVerify = findViewById(R.id.btnOTPVerify);

        setupInputOTP();
    }

private void setupInputOTP() {
    etOTPVerification1.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if (!s.toString().trim().isEmpty()){
                etOTPVerification2.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    etOTPVerification2.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!charSequence.toString().trim().isEmpty())
            {
                etOTPVerification3.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    etOTPVerification3.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!charSequence.toString().trim().isEmpty()){
                etOTPVerification4.requestFocus();
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    etOTPVerification4.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!charSequence.toString().trim().isEmpty()){
                etOTPVerification5.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
}
}
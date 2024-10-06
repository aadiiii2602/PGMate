package com.example.pgmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class OtpVerificationActivity extends AppCompatActivity {

    TextView tvMobileNo, tvResentOTP;
    EditText etOTPVerification1, etOTPVerification2, etOTPVerification3, etOTPVerification4, etOTPVerification5, etOTPVerification6;
    AppCompatButton btnVerify;
    ProgressDialog progressDialog;
    private String strVerificationCode, strName, strMobileNo, strEmailId, strUsername, strPassword;

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

        strVerificationCode = getIntent().getStringExtra("VerificationCode");
        strName = getIntent().getStringExtra("name");
        strMobileNo = getIntent().getStringExtra("mobileno");
        strEmailId = getIntent().getStringExtra("email");
        strUsername = getIntent().getStringExtra("username");
        strPassword = getIntent().getStringExtra("password");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etOTPVerification1.getText().toString().trim().isEmpty() || etOTPVerification2.getText().toString().trim().isEmpty() ||
                        etOTPVerification3.getText().toString().trim().isEmpty() || etOTPVerification4.getText().toString().trim().isEmpty() ||
                        etOTPVerification5.getText().toString().trim().isEmpty() ||etOTPVerification6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(OtpVerificationActivity.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
                String otpCode = etOTPVerification1.getText().toString()+etOTPVerification2.getText().toString()+etOTPVerification3.getText().toString()+
                        etOTPVerification4.getText().toString()+etOTPVerification5.getText().toString()+etOTPVerification6.getText().toString();

                if (strVerificationCode!=null)
                {
                    progressDialog = new ProgressDialog(OtpVerificationActivity.this);
                    progressDialog.setTitle("Verifying OTP");
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            strVerificationCode,
                            otpCode);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                userRegisterDetails();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(OtpVerificationActivity.this, "OTP Verification Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        tvResentOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + strMobileNo,
                        60, TimeUnit.SECONDS, OtpVerificationActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(OtpVerificationActivity.this, "Verification Completed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OtpVerificationActivity.this, "Verification Failed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                strVerificationCode = newVerificationCode;
                            }
                        }
                );
            }
        });





        setupInputOTP();

    }

    private void userRegisterDetails() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("name", strName);
        params.put("mobileno", strMobileNo);
        params.put("emailid", strEmailId);
        params.put("username", strUsername);
        params.put("password", strPassword);

        client.post("http://192.168.25.55:80/FoodizAPI/userregisterdetails.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    String status = response.getString("success");
                    if (status.equals("1"))
                    {
                        Toast.makeText(OtpVerificationActivity.this, "Registration Successfully Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OtpVerificationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                    else {
                        Toast.makeText(OtpVerificationActivity.this, "Already Data Present", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(OtpVerificationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
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
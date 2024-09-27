package com.example.pgmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName,tvEmail;
    AppCompatButton btnSignOut;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

            tvName= findViewById(R.id.tvName);
            tvEmail= findViewById(R.id.tvEmail);
            btnSignOut = findViewById(R.id.btnLoginSignIn);

        googleSignInOptions = new GoogleSignInOptions.Builder(googleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(ProfileActivity.this,googleSignInOptions);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount!= null){
            String name = googleSignInAccount.getDisplayName();
            String email = googleSignInAccount.getEmail();

            tvName.setText(name);
            tvEmail.setText(email);

            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            });
        }
    }
}

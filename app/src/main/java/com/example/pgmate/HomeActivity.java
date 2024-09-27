package com.example.pgmate;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.homeBottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homebottomnavHome);

    }
    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    MyProfileFragment myProfileFragment = new MyProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homebottomnavHome){
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,homeFragment).commit();

        } else if (item.getItemId()  == R.id.homebottomnavNotification) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,notificationFragment).commit();

        } else if (item.getItemId()  == R.id.homebottomnavMyProfile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,myProfileFragment).commit();
        }
        return true;

    }
}
package com.longs7.lab08_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            if(firebaseAuth.getCurrentUser() == null) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnSignOut).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });
    }
}
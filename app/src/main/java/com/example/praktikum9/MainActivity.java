package com.example.praktikum9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {
    private TextView welcomeTextView, emailTextView, uidTextView;
    private Button logOutButton;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        initComponent();
        displayProfile();

        logOutButton.setOnClickListener(view -> logOut());
    }

    private void initComponent() {
        welcomeTextView = findViewById(R.id.tv_welcome);
        emailTextView = findViewById(R.id.tv_email);
        uidTextView = findViewById(R.id.tv_uid);
        logOutButton = findViewById(R.id.btn_keluar);
    }

    private void displayProfile() {
        welcomeTextView.setText("Selamat Datang " + firebaseUser.getDisplayName());
        emailTextView.setText(firebaseUser.getEmail());
        uidTextView.setText(firebaseUser.getUid());
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(
                this,
                "Berhasil Log out!",
                Toast.LENGTH_LONG
        ).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

package com.example.praktikum9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        initComponent();

        loginButton.setOnClickListener(view -> loginHandler());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void initComponent() {
        emailTextView = findViewById(R.id.et_email);
        passwordTextView = findViewById(R.id.et_pass);
        loginButton = findViewById(R.id.btn_masuk);
    }

    private void loginHandler() {
        String email = emailTextView.getText().toString();
        String passsword = passwordTextView.getText().toString();

        mAuth.signInWithEmailAndPassword(email, passsword).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Toast.makeText(
                        this,
                        "Login Berhasil!",
                        Toast.LENGTH_LONG
                ).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                Toast.makeText(
                        this,
                        "Login Gagal: " + task.getException().getLocalizedMessage(),
                        Toast.LENGTH_LONG
                );
            }
        });

    }
}
package com.longs7.lab08_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtRePassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.etName);
        edtEmail = findViewById(R.id.etEmail);
        edtPassword = findViewById(R.id.etPassword);
        edtRePassword = findViewById(R.id.etRePassword);
        
        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            String rePass = edtRePassword.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || rePass.isEmpty()){
                Toast.makeText(this, "Please fill all of field!", Toast.LENGTH_SHORT).show();
            }
            
            if(!pass.equals(rePass))
                Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(result -> {
                            Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show());
            }
        });
    }
}
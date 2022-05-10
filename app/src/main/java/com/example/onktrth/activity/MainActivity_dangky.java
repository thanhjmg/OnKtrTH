package com.example.onktrth.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onktrth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_dangky extends AppCompatActivity {


    private static  final  String TAG ="MainActivity_dangky";
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPassword;
    private TextView tvConfirmPass;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dangky);

        mAuth = FirebaseAuth.getInstance();

        tvName = findViewById(R.id.tvName2);
        tvEmail = findViewById(R.id.tvEmail2);
        tvPassword = findViewById(R.id.tvPass2);
        tvConfirmPass = findViewById(R.id.tvConfirmPass2);
        btnRegister = findViewById(R.id.btnDangky2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    private void register() {
        String name = tvName.getText().toString().trim();
        String email = tvEmail.getText().toString().trim();
        String password = tvPassword.getText().toString().trim();
        String confirmPassword = tvConfirmPass.getText().toString().trim();

        if(name.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(this, "Please, input full information", Toast.LENGTH_SHORT).show();
        }else {
            if(!confirmPassword.equals(password)) {
                Toast.makeText(this, "Confirm password must equals password", Toast.LENGTH_SHORT).show();
            }else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(MainActivity_dangky.this, "Register success",
                                            Toast.LENGTH_SHORT).show();
                                    clearInput();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity_dangky.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
    private void clearInput() {
        tvName.setText("");
        tvEmail.setText("");
        tvPassword.setText("");
        tvConfirmPass.setText("");
    }

}
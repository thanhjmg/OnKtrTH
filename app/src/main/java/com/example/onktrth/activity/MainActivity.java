package com.example.onktrth.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

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

public class MainActivity extends AppCompatActivity {
    private TextView tvEmail;
    private TextView tvPassword;
    private Button btnLogin;
    private Button btnDangky;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        tvEmail = findViewById(R.id.txtEmail);
        tvPassword = findViewById(R.id.txtPasswrod);
        btnLogin = findViewById(R.id.btnLogin);
        btnDangky = findViewById(R.id.btnDangKy);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_dangky.class);
                startActivity(intent);
            }
        });

    }
    private void login() {
        String email= tvEmail.getText().toString().trim();
        String password = tvPassword.getText().toString().trim();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),MainActivity_List.class);
                                startActivity(intent);
                                Log.d("MESSAGE","SignInwithEmail: succes");
                            }
                            else {
                                Log.w("Message", "signInwithemail: fail");
                            }
                        }

                    });
        }
    }

}
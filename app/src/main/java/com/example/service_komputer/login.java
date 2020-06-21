package com.example.service_komputer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private LinearLayout login;
    private TextView regis;
    private FirebaseAuth mAuth;
    private EditText username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.usr);
        pass = findViewById(R.id.pwd);

        regis = findViewById(R.id.regis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registrasi.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString().trim();
                String pw = pass.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    username.setError("Silahkan Masukan Username Anda");
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    pass.setError("Silahkan Masukan Password Anda");
                    return;
                }

                if (pw.length() < 6 ){
                    pass.setError("Minimal Password 6 Karakter");
                    return;
                }

                mAuth.signInWithEmailAndPassword(name,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Login Berhasil",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(login.this, "Login Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}

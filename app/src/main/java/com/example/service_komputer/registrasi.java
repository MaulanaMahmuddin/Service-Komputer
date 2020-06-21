package com.example.service_komputer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_komputer.adapter.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class registrasi extends AppCompatActivity {

    @BindView(R.id.btn_regis)
    LinearLayout registrasi;
    @BindView(R.id.tlp)
    EditText tlp;
    @BindView(R.id.usr_regis)
    EditText nama;
    @BindView(R.id.email_regis)
    EditText email;
    @BindView(R.id.pwd)
    EditText pw;
    @BindView(R.id.back)
    ImageView back;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseDatabase database;
    DatabaseReference myref;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        }

        database = FirebaseDatabase.getInstance();
        myref = database.getReference("DataUser");


        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String telepon = tlp.getText().toString().trim();
                final String username = nama.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pass = pw.getText().toString().trim();

                if (TextUtils.isEmpty(telepon)){
                    tlp.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(mail)){
                    email.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    nama.setError("Masukan Username");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    pw.setError("Masukan Password");
                    return;
                }
                if (pass.length() < 6){
                    pw.setError("Minimal Password 6 Karakter");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(registrasi.this, "User Created...",Toast.LENGTH_SHORT).show();

                            UserInfo userInfo = new UserInfo(username,telepon,mail,pass);
                            myref.push()
                                    .setValue(userInfo);
//                            userID = mAuth.getCurrentUser().getUid();
//                            DocumentReference documentReference = db.collection("DataUser").document(userID);
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("ntelepon", telepon);
//                            user.put("email", mail);
//                            user.put("nama", username);
//                            user.put("password", pass);
//                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(registrasi.this, "User Created..." +userID,  Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.w(String.valueOf(registrasi), "Error adding document", e);
//                                }
//                            });
                            startActivity(new Intent(getApplicationContext(), login.class));
                            finish();
                        } else {
                            Toast.makeText(registrasi.this, "Register Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

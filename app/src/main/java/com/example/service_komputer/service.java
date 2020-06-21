package com.example.service_komputer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_komputer.adapter.DaftarService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class service extends AppCompatActivity {

    private static final String TAG = "service";

    @BindView(R.id.nohp1)
    EditText nohp;
    @BindView(R.id.nama1)
    EditText nama;
    @BindView(R.id.alamat1)
    EditText ala;
    @BindView(R.id.perangkat1)
    EditText prkt;
    @BindView(R.id.tipe1)
    EditText tp;
    @BindView(R.id.kerusakan1)
    EditText krskan;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.btn_daftar)
    LinearLayout daftar;
    FirebaseFirestore db;
    FirebaseDatabase database;
    DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        database = FirebaseDatabase.getInstance();
        myref = database.getReference("DataService");

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorhp = nohp.getText().toString().trim();
                String username = nama.getText().toString().trim();
                String alamat = ala.getText().toString().trim();
                String perangkat = prkt.getText().toString().trim();
                String tipe = tp.getText().toString().trim();
                String kerusakan = krskan.getText().toString().trim();

                if (TextUtils.isEmpty(nomorhp)){
                    nohp.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    nama.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(alamat)){
                    ala.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(perangkat)){
                    prkt.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(tipe)){
                    tp.setError("Masukan Nomor Telepom");
                    return;
                }
                if (TextUtils.isEmpty(kerusakan)){
                    krskan.setError("Masukan Nomor Telepom");
                    return;
                }

                DaftarService daftarService = new DaftarService(username,nomorhp,alamat,perangkat,tipe,kerusakan);
                myref.push()
                        .setValue(daftarService);

                Dialog dialog= new Dialog(service.this);
                   dialog.setContentView(R.layout.dialog);
                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                   dialog.show();

                   LinearLayout btnok = dialog.findViewById(R.id.btn_ok);
                   btnok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(service.this, MainActivity.class);
                           startActivity(intent);
                       }
                   });
                // Create a new user with a first and last name
//                Map<String, Object> daftar = new HashMap<>();
//                daftar.put("nomorhp", nomorhp);
//                daftar.put("username", username);
//                daftar.put("alamat", alamat);
//                daftar.put("perangkat", perangkat);
//                daftar.put("tipe", tipe);
//                daftar.put("kerusakan", kerusakan);
//
//                // Add a new document with a generated ID
//                db.collection("daftar")
//                    .add(daftar)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                           Dialog dialog= new Dialog(service.this);
//                           dialog.setContentView(R.layout.dialog);
//                           dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                           dialog.show();
//
//                           LinearLayout btnok = dialog.findViewById(R.id.btn_ok);
//                           btnok.setOnClickListener(new View.OnClickListener() {
//                               @Override
//                               public void onClick(View v) {
//                                   Intent intent = new Intent(service.this, MainActivity.class);
//                                   startActivity(intent);
//                               }
//                           });
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });
            }
        });
    }
}

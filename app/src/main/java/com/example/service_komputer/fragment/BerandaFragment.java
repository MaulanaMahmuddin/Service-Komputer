package com.example.service_komputer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_komputer.R;
import com.example.service_komputer.adapter.Beranda;
import com.example.service_komputer.adapter.RecyclerViewAdapter;
import com.example.service_komputer.adapter.UserInfo;
import com.example.service_komputer.login;
import com.example.service_komputer.mandiri;
import com.example.service_komputer.riwayat;
import com.example.service_komputer.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BerandaFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    @BindView(R.id.rv_beranda)
    RecyclerView recyclerView;
    @BindView(R.id.nama)
    TextView nama;
    Unbinder unbinder;
    FirebaseAuth.AuthStateListener mlistener;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    DatabaseReference ref;
    FirebaseDatabase dbb;

    String user, tlp, userID;
    View v;

    public BerandaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_beranda,container,false);
        unbinder = ButterKnife.bind(this, v);
        setupFirebaseAuth();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        dbb = FirebaseDatabase.getInstance();
        ref = dbb.getReference().child("DataUser");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserInfo userinfo = snapshot.getValue(UserInfo.class);
                    user = userinfo.getNama();
                    nama.setText(user);

                    Log.d(TAG,"yey" + userinfo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Data Ditidak Terdetack"+databaseError,Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Data Tidak ditemukan" + databaseError);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        load();
    }

    private void load(){
        List<Beranda> models = new ArrayList<>();

        Beranda model = new Beranda();
        model.setImg(R.drawable.mandiri);
        model.setNama("Perbaikan Mandiri");
        models.add(model);

        model = new Beranda();
        model.setImg(R.drawable.service);
        model.setNama("Daftar Service");
        models.add(model);

        model = new Beranda();
        model.setImg(R.drawable.riwayat);
        model.setNama("Riwayat Service");
        models.add(model);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(models, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Beranda model) {
                switch (model.getNama()) {
                    case "Perbaikan Mandiri":
                        Intent intent = new Intent(getContext(), mandiri.class);
                        startActivity(intent);
                        break;

                        case "Daftar Service":
                        intent = new Intent(getContext(), service.class);
                        startActivity(intent);
                        break;

                        case "Riwayat Service":
                        intent = new Intent(getContext(), riwayat.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            recyclerView.setAdapter(recyclerViewAdapter);
        }


    }

    public void setupFirebaseAuth(){
        Log.d(TAG,"setupFirebaseAuth: setting up Firebase auth");
        mlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    Log.d(TAG, "onAuthStateChange: sign_in" + user.getUid());
                }else {
                    Log.d(TAG, "onAuthStateChange: sign_out");
                    Intent intent = new Intent(getActivity(), login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mlistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mlistener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mlistener);
        }
    }
}

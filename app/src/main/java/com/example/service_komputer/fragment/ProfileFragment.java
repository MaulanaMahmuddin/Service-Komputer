package com.example.service_komputer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.service_komputer.R;
import com.example.service_komputer.adapter.UserInfo;
import com.example.service_komputer.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    @BindView(R.id.named)
    TextView username1;
    @BindView(R.id.notlp)
    TextView notelp;
    Unbinder unbinder;
    FirebaseAuth.AuthStateListener mlistener;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    FirebaseDatabase db;

    String user, tlp, userID;

    @BindView(R.id.btn_logout)
    LinearLayout btnlogout;

    View v;

    public ProfileFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile,container,false);
        setupFirebaseAuth();
        unbinder = ButterKnife.bind(this, v);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: attemping to Sign out the user");
                FirebaseAuth.getInstance().signOut();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference().child("DataUser");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserInfo userinfo = snapshot.getValue(UserInfo.class);
                    user = userinfo.getNama();
                    tlp = userinfo.getNtelepon();
                    username1.setText(user);
                    notelp.setText(tlp);

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
                        Toast.makeText(getActivity()," SignOut", Toast.LENGTH_SHORT).show();
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

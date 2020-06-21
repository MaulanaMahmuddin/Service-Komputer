package com.example.service_komputer.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.service_komputer.R;
import com.example.service_komputer.login;
import com.example.service_komputer.webView.t_hardware;
import com.example.service_komputer.webView.t_jaringan;
import com.example.service_komputer.webView.t_sofware;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "InfoFragment";

    @BindView(R.id.btn_sofware)
    LinearLayout soft;
    @BindView(R.id.btn_hardware)
    LinearLayout hard;
    @BindView(R.id.btn_jarigan)
    LinearLayout jar;
    Unbinder unbinder;
    FirebaseAuth.AuthStateListener mlistener;
    View v;

    public InfoFragment() {

    }

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, v);
        setupFirebaseAuth();
        soft.setOnClickListener(this);
        hard.setOnClickListener(this);
        jar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sofware:
                Intent moveIntent1 = new Intent(getActivity(), t_sofware.class);
                startActivity(moveIntent1);
                break;

            case R.id.btn_hardware:
                Intent moveIntent2 = new Intent(getActivity(), t_hardware.class);
                startActivity(moveIntent2);
                break;

            case R.id.btn_jarigan:
                Intent moveIntent3 = new Intent(getActivity(), t_jaringan.class);
                startActivity(moveIntent3);
                break;
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

package com.example.service_komputer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class mandiri extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.soft_mandiri)
    LinearLayout soft;
    @BindView(R.id.hard_mandiri)
    LinearLayout hard;
    @BindView(R.id.jar_mandiri)
    LinearLayout jar;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandiri);
        ButterKnife.bind(this);
        soft.setOnClickListener(this);
        hard.setOnClickListener(this);
        jar.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.soft_mandiri:
                Intent moveIntent1 = new Intent(this, software.class);
                startActivity(moveIntent1);
                break;

            case R.id.hard_mandiri:
                Intent moveIntent2 = new Intent(this, hardware.class);
                startActivity(moveIntent2);
                break;

            case R.id.jar_mandiri:
                Intent moveIntent3 = new Intent(this, jaringan.class);
                startActivity(moveIntent3);
                break;
        }
    }
}

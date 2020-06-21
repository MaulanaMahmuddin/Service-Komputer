package com.example.service_komputer;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class jaringan extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wv)
    WebView webb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaringan);
        ButterKnife.bind(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webb = findViewById(R.id.wv);
        webb.setWebViewClient(new WebViewClient());
        webb.loadUrl("http://blog.unnes.ac.id/ayukwitantri/2016/02/12/permasalahan-pada-jaringan-dan-cara-memperbaikinya/");
    }
}

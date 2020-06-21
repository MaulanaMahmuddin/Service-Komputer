package com.example.service_komputer;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class software extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.wv)
    WebView webb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);
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
        webb.loadUrl("https://www.beritateknologi.com/empat-langkah-mudah-mengatasi-software-yang-crash-atau-rusak/");
    }
}

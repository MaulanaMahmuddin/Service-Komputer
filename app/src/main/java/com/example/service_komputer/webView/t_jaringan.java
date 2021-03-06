package com.example.service_komputer.webView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_komputer.R;

public class t_jaringan extends AppCompatActivity {

    private ImageView back;
    private WebView webb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_jaringan);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webb = findViewById(R.id.wv_jaringan);
        webb.setWebViewClient(new WebViewClient());
        webb.loadUrl("https://www.dewaweb.com/blog/jaringan-komputer-pengertian-jenis/");
    }
}

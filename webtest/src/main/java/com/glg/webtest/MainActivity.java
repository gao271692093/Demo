package com.glg.webtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            //防止有 URL Scheme 跳转协议类型的url 导致webView加载网页失败
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url == null) {
                    return false;
                }
                if(url.startsWith("http:")||url.startsWith("https:")){
                    view.loadUrl(url);
                    return false;
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        getApplicationContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        });
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }
        webView.loadUrl("http://www.baidu.com");
    }
}

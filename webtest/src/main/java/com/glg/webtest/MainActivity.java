package com.glg.webtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.ekwing.http.common.Config;
import com.ekwing.http.common.HttpProxy;
import com.ekwing.http.common.interfaces.CallBack;
import com.ekwing.http.okgoclient.OkGoWrapper;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config config = new Config.Builder()
                        .connectTimeout(Config.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(Config.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(Config.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .setEnableDns(true) //启用自定DNS
                        .setLogEnable(true) //开启日志
                        .setEnableSkipHttps(true) //允许绕过证书验证
                        .setRetryCount(Config.MAX_RETRY) //重试次数
                        .build();
                HttpProxy.getInstance().initClient(new OkGoWrapper(getApplication(), config));
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("id", "01");
                HttpProxy.getInstance().get("http://www.baidu.com", "Hello", stringStringHashMap, false, new CallBack() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this, "请求成功" + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCacheSuccess(String result) {

                    }

                    @Override
                    public void onError(int code, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "请求失败" + code, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });
    }
}

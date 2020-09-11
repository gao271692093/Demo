package com.glg.okhttptest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.Manifest.permission.INTERNET;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkSelfPermission(INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{INTERNET}, 1);
        }
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button button = findViewById(R.id.upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UploadActivity.class));
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        HttpUtil.sendOkHttpRequest("http://bing.getlove.cn/latelyBingImageStory", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody result = response.body();
                String str = result.string();
                final List<ImageItem> list = JSONArray.parseArray(str, ImageItem.class);
                final ImagesAdapter imagesAdapter = new ImagesAdapter(MainActivity.this, list);
                imagesAdapter.setOnItemClickListener(new ImagesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String url = "https://cn.bing.com/" + list.get(position).getUrl();
                        final String fileName = url.split("/")[url.split("/").length - 1] + ".jpg";
                        OkHttpClient client1 = HttpsCertificateVerifyHelper.trustAllCertificate(new OkHttpClient.Builder()
                                //.cache(cache)
                                .retryOnConnectionFailure(false))
                                .build();
                        //OkHttpClient client = new OkHttpClient();
                        final Request request = new Request.Builder().get().url(url).build();
                        Call call = client1.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("下载", "onFailure: " + e.getMessage());;
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AAAFile";
                                File file=new File(dirName);
                                //Log.d("文件路径：", file.getAbsolutePath());
                                if (!file.exists()) {
                                    file.mkdir();
                                }
                                if (response != null) {
                                    InputStream is = response.body().byteStream();
                                    FileOutputStream fos = new FileOutputStream(new File(dirName + "/" + fileName));
                                    int len = 0;
                                    byte[] buffer = new byte[2048];
                                    while (-1 != (len = is.read(buffer))) {
                                        fos.write(buffer, 0, len);
                                        //Log.d("下载中：","" + len);
                                    }
                                    Log.d("下载", "onSuccess:");
                                    fos.flush();
                                    fos.close();
                                    is.close();
                                }
                            }
                        });
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "" + list.size(), Toast.LENGTH_SHORT).show();
                        recyclerView.setAdapter(imagesAdapter);
                    }
                });
                //Toast.makeText(MainActivity.this, result.string(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "=========================================");
        super.onResume();
    }
}

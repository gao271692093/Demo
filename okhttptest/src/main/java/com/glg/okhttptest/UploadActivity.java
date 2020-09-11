package com.glg.okhttptest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.glg.okhttptest.FileUtils.getPath;
import static com.glg.okhttptest.FileUtils.getRealPathFromURI;

public class UploadActivity extends AppCompatActivity {

    private String TAG = "UploadActivity";
    private String path,uploadfile;
    private File file;
    private EditText description;
    private TextView fileName;
    private Button find;
    private Button commit;
    private ImageView imageView;

    private static final int SUCCESS = 1;
    private static final int FALL = 2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //加载网络成功进行UI的更新,处理得到的图片资源
                case SUCCESS:
                    //通过message，拿到字节数组
                    byte[] Picture = (byte[]) msg.obj;
                    //使用BitmapFactory工厂，把字节数组转化为bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Picture, 0, Picture.length);
                    //通过imageview，设置图片
                    imageView.setImageBitmap(bitmap);

                    break;
                //当加载网络失败执行的逻辑代码
                case FALL:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        description = findViewById(R.id.description);
        fileName = findViewById(R.id.fileName);
        find = findViewById(R.id.find);
        commit = findViewById(R.id.commit);
        imageView = findViewById(R.id.imageView);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = UploadActivity.this.description.getText().toString();
                OkManager manager = OkManager.getInstance();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("description", description) // 提交内容字段
                        .addFormDataPart("uploadfile", uploadfile, RequestBody.create(MediaType.parse("image/jpeg"), file))
                        .build();
                String url = "";
                manager.postFile(url, requestBody,new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: ",e);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseBody = response.body().string();
                        final JSONObject obj = JSON.parseObject(responseBody);
                        Log.e(TAG,obj.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 对返回结果进行操作
                            }
                        });

                    }
                });
            }
        });

        getImage("https://cn.bing.com/" + "/th?id=OHR.SailingStone_ZH-CN1020921437_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG,"返回的数据："+data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Log.d(TAG, uri.getPath() + "===================");
            //使用第三方应用打开
            if ("file".equalsIgnoreCase(uri.getScheme())){
                path = uri.getPath();
                file = new File(path);
                uploadfile = file.getName();
                fileName.setText(uploadfile);
                Log.w(TAG,"getName==="+uploadfile);
                Toast.makeText(this,path+"11111",Toast.LENGTH_SHORT).show();
                return;
            }
            //4.4以后
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                //path = getPath(this, uri);
                path = uri.getPath();
                Log.d(TAG,path);
                file = new File(path);
                uploadfile = file.getName();
                fileName.setText(uploadfile);
                Log.d(TAG,"getName==="+uploadfile);
                Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
            } else {//4.4以下
                path = getRealPathFromURI(this,uri);
                Log.d(TAG,path);
                Toast.makeText(UploadActivity.this, path+"222222", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    private void getImage(String url) {
        OkHttpClient client1 = HttpsCertificateVerifyHelper.trustAllCertificate(new OkHttpClient.Builder()
                //.cache(cache)
                .retryOnConnectionFailure(false))
                .build();
        final Request request = new Request.Builder().get().url(url).build();
        Call call = client1.newCall(request);
        final Bitmap[] bitmap = new Bitmap[1];
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] bytes = response.body().bytes();
//                InputStream inputStream = response.body().byteStream();
//                //bitmap[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                bitmap[0] = BitmapFactory.decodeStream(inputStream);
                Message message = handler.obtainMessage();
                message.obj = bytes;
                message.what = SUCCESS;
                handler.sendMessage(message);
            }
        });
        //return bitmap[0];
    }
}
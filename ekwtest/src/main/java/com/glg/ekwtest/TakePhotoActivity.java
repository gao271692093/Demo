package com.glg.ekwtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePhotoActivity extends AppCompatActivity {

    private Camera camera;

    private boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { // 判断手机 是否安装SD卡
            Toast.makeText(this, "请安装sd卡", Toast.LENGTH_SHORT).show();
        }

        //实现预览功能
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置SurfaceView自己不维护缓冲
        Button preview = findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPreview) {
                    camera = Camera.open();
                    isPreview = true;//设置为预览状态
                }
                try {
                    camera.setPreviewDisplay(surfaceHolder);//设置用于显示预览的SurfaceView
                    Camera.Parameters parameters = camera.getParameters();

                    parameters.setPictureFormat(PixelFormat.JPEG);//设置图片格式为jpg
                    parameters.set("jpeg-quality", 80);//设置图片的质量

                    camera.setParameters(parameters);//重新设置摄像头参数
                    camera.startPreview();//开始预览
                    camera.autoFocus(null);//设置自动对焦
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //实现拍照功能
        Button takePhoto = findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camera != null) {
                    camera.takePicture(null,null, jpeg);//进行拍照
                }
            }
        });
    }

    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);//根据拍照 所得数据创建位图
            camera.stopPreview();//停止预览
            isPreview = false;//设置为非预览状态

            //获取相片在SD卡中的 位置
            File appDir = new File(Environment.getExternalStorageDirectory(), "/sdcard/DCIM/Camera/");
            if(!appDir.exists()) {//如果该目录不存在就创建该目录
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpeg";
            File file = new File(appDir, fileName);//创建文件对象
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);//创建一个文件输出流对象
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);//将图片内容压缩成jpeg格式输出到输出文件当中
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //将照片插入到系统图库当中
            try {
                MediaStore.Images.Media.insertImage(TakePhotoActivity.this.getContentResolver(), file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //最后通知图库跟新
            TakePhotoActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "")));
            Toast.makeText(TakePhotoActivity.this, "图片保存至:"+file, Toast.LENGTH_SHORT).show();
            resetCamera();
        }
    };

    private  void resetCamera() {
        if(!isPreview) {
            camera.startPreview();
            isPreview = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(camera!=null) {
            camera.stopPreview();
            camera.release();
        }
    }
}
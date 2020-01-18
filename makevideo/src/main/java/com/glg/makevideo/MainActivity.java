package com.glg.makevideo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button play, make_start, make_stop;

    private SurfaceView surfaceView;

    private File videoFile;

    private MediaRecorder mediaRecorder;

    private Camera camera;

    private boolean isRecorder = false;//设置 录像状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(1920, 1080);//设置surfaceview的分辨率
        play = findViewById(R.id.play);
        make_start = findViewById(R.id.make_start);
        make_stop = findViewById(R.id.make_pause);
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡", Toast.LENGTH_SHORT).show();
        }
        make_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record();
            }
        });
        make_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaRecorder.stop();
                mediaRecorder.release();
                Toast.makeText(MainActivity.this, "录像保存在："+ videoFile, Toast.LENGTH_SHORT).show();
                isRecorder = false;
            }
        });
    }

    private void record() {
        File  path = new File(Environment.getExternalStorageDirectory() + "/MyVideo/");
        if(!path.exists()) {
            path.mkdir();
        }
        String fileName = "video.mp4";
        videoFile = new File(path + fileName);
        mediaRecorder = new MediaRecorder();
        camera.setDisplayOrientation(90);//调整摄像头的角度
        camera.unlock();//解锁摄像头
        mediaRecorder.setCamera(camera);//使用摄像头
        mediaRecorder.reset();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//使用手机的主麦克风
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//使用摄像头获取图像
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//设置视频输出格式为mp4
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);//设置视频的编码格式
        mediaRecorder.setVideoEncodingBitRate(1920*1080);//设置清晰度
        mediaRecorder.setVideoSize(1920, 1080);//视频的尺寸
        mediaRecorder.setVideoFrameRate(10);//每秒有10帧
        mediaRecorder.setOutputFile(videoFile.getAbsolutePath());//视频的输出路径
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());//设置使用的suifaceView
        mediaRecorder.setOrientationHint(90);//调整播放视频的 角度
        try {
            mediaRecorder.prepare();//准备录像
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();//开始 录像
        Toast.makeText(this, "开始录制", Toast.LENGTH_SHORT).show();
        isRecorder =  true;//正在录制
    }

    @Override
    protected void onPause() {//在activity失去焦点的时候关闭预览并释放资源
        super.onPause();
        camera.stopPreview();
        camera.release();
    }

    @Override
    protected void onPostResume() {//在activity获取焦点 的时候开启摄像头
        super.onPostResume();
        camera = Camera.open();//开启摄像头
    }
}

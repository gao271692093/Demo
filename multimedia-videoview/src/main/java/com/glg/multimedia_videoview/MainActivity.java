package com.glg.multimedia_videoview;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.security.Permission;

public class MainActivity extends Activity {

    private VideoView videoView;

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoView = findViewById(R.id.videoVIew);

        //Toast.makeText(this, Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        final File file = new File("/sdcard/video1.mp4");
        Log.d("VideoView","=========>>>>"+file.exists());
        if(file.exists()) {
            videoView.setVideoPath(file.getAbsolutePath());
        } else {
            Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
        }

        mediaController = new MediaController(this);
        mediaController.setEnabled(true);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();//让VideoView获得焦点
        //videoView.start();
        mediaController.show();
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                }
                videoView.resume();
                //videoView.setVideoPath(file.getAbsolutePath());
            }
        };
        videoView.setOnCompletionListener(onCompletionListener);
    }
}

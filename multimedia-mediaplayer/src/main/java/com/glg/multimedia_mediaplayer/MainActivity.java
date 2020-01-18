package com.glg.multimedia_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer.OnCompletionListener onCompletionListener;

    private int music[];

    private int index = 0;

    private boolean flag = true;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = initMusic();
        mediaPlayer = MediaPlayer.create(this, music[index]);
        onCompletionListener = new MediaPlayer.OnCompletionListener() {//设置播放完成以后的动作
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                if (index == music.length - 1) {
                    index = 0;
                } else {
                    index = index +  1;
                }
                mediaPlayer = MediaPlayer.create(MainActivity.this, music[index]);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(this);
                Log.d("mediaPlayer", "==>" + mediaPlayer.toString());
            }
        };
        Button button1 = findViewById(R.id.btn_previous);
        Button button2 = findViewById(R.id.btn_play_pause);
        Button button3 = findViewById(R.id.btn_next);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                if(index == 0) {
                    index = music.length - 1;
                } else {
                    index = index - 1;
                }
                mediaPlayer = MediaPlayer.create(MainActivity.this, music[index]);
                mediaPlayer.setOnCompletionListener(onCompletionListener);
                mediaPlayer.start();
                flag = false;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag) {
                    mediaPlayer.start();
                    flag = false;
                } else {
                    mediaPlayer.pause();
                    flag = true;
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                if(index == music.length - 1) {
                    index = 0;
                } else {
                    index = index + 1;
                }
                mediaPlayer = MediaPlayer.create(MainActivity.this, music[index]);
                mediaPlayer.setOnCompletionListener(onCompletionListener);
                mediaPlayer.start();
                flag = false;
            }
        });
        Button button4 = findViewById(R.id.btn_RePlay);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(MainActivity.this, music[index]);
                mediaPlayer.setOnCompletionListener(onCompletionListener);
                mediaPlayer.start();
                flag = false;
            }
        });
        mediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        super.onDestroy();
    }

    private int[] initMusic() {
        int[] music = new int[2];
        music[0] = R.raw.background;
        music[1] = R.raw.background1;
        return music;
    }
}

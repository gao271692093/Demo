package com.glg.multi_soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listVIew);
        AudioAttributes audioAttributes  = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC) //设置音效的使用场景
                .setUsage(AudioAttributes.USAGE_MEDIA) //设置音效类型
                .build();
        final SoundPool soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes) //设置音效池的属性
                .setMaxStreams(10) //设置最多可容纳10个音频流
                .build();
        final HashMap<Integer, Integer> hashMap  = new HashMap<Integer, Integer>();
        hashMap.put(0, soundPool.load(this, R.raw.background, 1));
        hashMap.put(1, soundPool.load(this, R.raw.background1, 1));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                soundPool.play(hashMap.get(position), 1, 1, 0, 0, 1);
            }
        });
    }
}

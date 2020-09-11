package com.glg.customviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Date date = new Date();
//        date.setTime(0);
//        Toast.makeText(this, new SimpleDateFormat().format(date), Toast.LENGTH_SHORT).show();
//        date.setTime(12354646546L);
//        Toast.makeText(this, new SimpleDateFormat().format(date), Toast.LENGTH_SHORT).show();
//        date.setTime(4564646546L);
//        Toast.makeText(this, new SimpleDateFormat().format(date), Toast.LENGTH_SHORT).show();
        long duration = 1L * 1000 * 9 + 1L * 1000 * 60 * 9 + 1L * 1000 * 60 * 60 * 9;
        long hours = duration/(1000 * 60 * 60);
        long minute = (duration - hours * 60 * 60 * 1000) / (1000 * 60);
        long seconds = duration / 1000 - hours * 60 * 60 - minute * 60;
        StringBuilder stringBuilder = new StringBuilder();
        if(hours < 10) {
            stringBuilder.append("0" + hours + ":");
        } else {
            stringBuilder.append(hours + ":");
        }
        if(minute < 10) {
            stringBuilder.append("0" + minute + ":");
        } else {
            stringBuilder.append(minute + ":");
        }
        if (seconds < 10) {
            stringBuilder.append("0" + seconds);
        } else {
            stringBuilder.append(seconds);
        }
        Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
    }
}

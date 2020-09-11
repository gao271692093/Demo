package com.glg.zoomimagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ZoomImageView1 zoomImageView = findViewById(R.id.zoomImageView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beautiful);
        zoomImageView.setImageBitmap(bitmap);
    }
}

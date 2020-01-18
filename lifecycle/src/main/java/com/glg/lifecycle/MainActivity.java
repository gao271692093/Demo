package com.glg.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Activity生命周期","create");
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,YourActivity.class);
                startActivity(intent);
            }
        });
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity生命周期","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity生命周期","onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity生命周期","onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity生命周期","onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity生命周期","ondestroy");
    }
}

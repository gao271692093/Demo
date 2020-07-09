package com.glg.startforresulttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(Tag, "==>>" + isTop("MainActivity"));
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 0x11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        if(requestCode == 0x11 &&  resultCode == 0x11) {
            Toast.makeText(MainActivity.this, "收到返回值了，具体为"+bundle.getInt("abc"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "==>>onStart");
    }

    @Override
    protected void onStop() {
        Log.d(Tag, "==>>onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d(Tag, "==>>onDestroy");
        super.onDestroy();

}

    @Override
    protected void onPause() {
        Log.d(Tag, "==>>onPause");
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.d(Tag, "==>>onResume");
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag, "==>>onRestart");
    }

//    private boolean isTop(String activity) {
//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        ComponentName cn = activityManager.getRunningTasks(1).get(0).topActivity;
//        return cn.getClassName().contains(activity);
//    }
}

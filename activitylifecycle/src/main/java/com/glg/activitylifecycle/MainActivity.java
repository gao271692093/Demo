package com.glg.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Tag", "oncreate");
        if(savedInstanceState!=null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d("Tag",tempData);
        }
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        textView.setText(this.toString());
        Button startNormalActivity = findViewById(R.id.normal_activitiy);
        Button startDialogActivity = findViewById(R.id.dialog_activity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
        Button standard = findViewById(R.id.standard);
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandardActivity.class);
                startActivity(intent);
                textView.setText(this.toString());
            }
        });
        Button singleTop = findViewById(R.id.single);
        singleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleActivity.class);
                startActivity(intent);
                textView.setText(this.toString());
            }
        });
        Button singleTask = findViewById(R.id.singleTask);
        singleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleTask.class);
                startActivity(intent);
                textView.setText(this.toString());
            }
        });
        Button singleInstance = findViewById(R.id.SingleInstance);
        singleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingleInstance.class);
                startActivity(intent);
                textView.setText(this.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Tag", "onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Tag", "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag", "ondestroy");
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Tag", "onpause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag", "onresume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Tag", "onrestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }
}

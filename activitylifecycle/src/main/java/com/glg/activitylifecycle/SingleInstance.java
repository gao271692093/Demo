package com.glg.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SingleInstance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TaskId", "TaskId is" + getTaskId());
        setContentView(R.layout.activity_single_instance);
        Toast.makeText(SingleInstance.this, this.toString(), Toast.LENGTH_SHORT).show();
        Button button = findViewById(R.id.singleInstance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInstance.this, NextActivity.class);
                startActivity(intent);
            }
        });
    }
}

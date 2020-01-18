package com.glg.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SingleTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        Button button = findViewById(R.id.singleTask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTask.this, SingleTask.class);
                Toast.makeText(SingleTask.this, this.toString(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Button button1 = findViewById(R.id.mainActivity2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

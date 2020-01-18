package com.glg.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Button button1 = findViewById(R.id.singleTop);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SingleActivity.this, MainActivity.class);
                Intent intent = new Intent(SingleActivity.this, SingleActivity.class);
                Toast.makeText(SingleActivity.this, this.toString(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.mainActivity1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.glg.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        Button button = findViewById(R.id.standard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandardActivity.this, StandardActivity.class);
                Toast.makeText(StandardActivity.this, this.toString(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}

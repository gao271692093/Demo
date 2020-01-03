package com.glg.ekwtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class PrivateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        TextView textView = findViewById(R.id.textView_private);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if(NavUtils.getParentActivityName(PrivateActivity.this) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

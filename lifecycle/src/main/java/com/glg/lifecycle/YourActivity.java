package com.glg.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class YourActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your);
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onCreate(null);
                finish();
            }
        });
    }
}

package com.glg.paint_animfairy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final AnimationDrawable animation = (AnimationDrawable) linearLayout.getBackground();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true) {
                    animation.start();
                    flag = false;
                } else {
                    animation.stop();
                    flag = true;
                }
            }
        });
    }
}

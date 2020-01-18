package com.glg.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class putOnHat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_on_hat);
        final HatView hatView = new HatView(putOnHat.this);
        hatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hatView.setX(event.getX() - 80);
                hatView.setY(event.getY() - 80);
                hatView.invalidate();
                return true;
            }
        });
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.addView(hatView);
    }
}

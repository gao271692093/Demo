package com.glg.constraintlayouttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ActivityMotionTest
        setContentView(R.layout.activity_motion);

        //CustomConstraintTest
        //setContentView(R.layout.layout_constraint2_custom);

        //LayerTest
//        setContentView(R.layout.layout_constraint2_layer);
//        final Layer layer = findViewById(R.id.layer);
//        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
//        valueAnimator.setDuration(2000);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                layer.setRotation((float)(valueAnimator.getAnimatedValue()));
//            }
//        });
//        layer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                valueAnimator.start();
//            }
//        });
    }
}

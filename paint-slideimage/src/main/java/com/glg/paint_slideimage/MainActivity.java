package com.glg.paint_slideimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private int[] image = {R.drawable.art01, R.drawable.art02, R.drawable.art03, R.drawable.art04, R.drawable.art05, R.drawable.art06, R.drawable.art07, R.drawable.art08, R.drawable.art09, R.drawable.art10};

    private final int distance = 50;

    private GestureDetector gestureDetector;

    private ViewFlipper viewFlipper;

    private Animation[] animations = new Animation[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.viewFlipper);
        gestureDetector = new GestureDetector(this, this);
        for(int i = 0; i < image.length; i += 1) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image[i]);
            viewFlipper.addView(imageView);
        }
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.anim_alph_in);
        animations[0].setDuration(4000);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.anim_alph_out);
        animations[1].setDuration(4000);
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.anim_translate_left);
        animations[2].setDuration(4000);
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.anim_translate_right);
        animations[3].setDuration(4000);
        viewFlipper.setInAnimation(animations[0]);
        viewFlipper.setOutAnimation(animations[1]);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX() > distance) {
            viewFlipper.setAnimation(animations[2]);
            viewFlipper.showPrevious();
            return true;
        } else {
            viewFlipper.setAnimation(animations[3]);
            viewFlipper.showNext();
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}

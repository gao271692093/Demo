package com.glg.seepicture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    Animation[] animation = new Animation[4];
    final int distance = 50;
    private int pictures[] = {R.drawable.art01,R.drawable.art02,R.drawable.art03,R.drawable.art04,R.drawable.art05,R.drawable.art06,R.drawable.art07,R.drawable.art08,R.drawable.art09,R.drawable.art10,};

    GestureDetector gestureDetector;

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this, this);
        viewFlipper = findViewById(R.id.viewFlipper);
        for(int  i = 0; i < pictures.length; i+= 1) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pictures[i]);
            viewFlipper.addView(imageView);
        }
        //animation[0] = AnimationUtils.loadAnimation(this, R.anim.)
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
            viewFlipper.setInAnimation(animation[2]);
            viewFlipper.setOutAnimation(animation[1]);
            viewFlipper.showPrevious();
            return true;
        } else {
            viewFlipper.setInAnimation(animation[0]);
            viewFlipper.setInAnimation(animation[4]);
            viewFlipper.showPrevious();
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}

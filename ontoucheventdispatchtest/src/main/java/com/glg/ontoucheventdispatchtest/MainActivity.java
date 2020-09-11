package com.glg.ontoucheventdispatchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("MyButton", "HelloWorld");
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "===dispatchTouchEvent()");
        boolean b = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent返回结果为" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "===onTouchEvent()");
        boolean b = super.onTouchEvent(event);
        Log.i(TAG, "onTouchEvent返回结果为" + b);
        return b;
    }
}

package com.glg.ontoucheventdispatchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Description:
 *
 * @package: com.glg.ontoucheventdispatchtest
 * @className: MyButton
 * @author: gao
 * @date: 2020/7/31 10:07
 */
class MyButton extends androidx.appcompat.widget.AppCompatButton {

    private String TAG = "MyButton";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "===onTouchEvent()");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "===dispatchTouchEvent()");
        return super.dispatchTouchEvent(event);
    }
}

package com.glg.ontoucheventdispatchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @package: com.glg.ontoucheventdispatchtest
 * @className: MyLinearLayout
 * @author: gao
 * @date: 2020/7/31 10:03
 */
class MyLinearLayout extends LinearLayout {

    private String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "===onTouchEvent()");
        boolean b = super.onTouchEvent(event);
        Log.i(TAG, "onTouchEvent返回结果为" + b);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        Log.i(TAG, "===onInterceptTouchEvent()");
        boolean b = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "onIntercept返回结果为" + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "===dispatchTouchEvent()");
        boolean b = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent返回结果为" + b);
        return b;
    }
}

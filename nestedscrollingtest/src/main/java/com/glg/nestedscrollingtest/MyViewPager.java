package com.glg.nestedscrollingtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * Created by gao on 2020/7/20 18:36.
 * Function:
 */
class MyViewPager extends ViewPager {
    private int height1;
    private int height2;
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int height = 0;
//        for (int i = 0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED));
//            int h = child.getMeasuredHeight();
//            if (h > height)
//                height = h;
//        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height1 - 132, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setHeight1(int height1) {
        this.height1 = height1;
    }

    public void setHeight2(int height2) {
        this.height2 = height2;
    }

    @SuppressLint("LongLogTag")
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int curPosition;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int location[] = new int[2];
                //curPosition = this.getCurrentItem();
                //Toast.makeText(getContext(), "" + curPosition, Toast.LENGTH_SHORT).show();
                //Log.e("==============================", "" + curPosition + "++" + this.getAdapter().getCount());
                ((ViewPagerAdapter)this.getAdapter()).getmCurrentView().getLocationOnScreen(location);
                //getChildAt(curPosition).getLocationOnScreen(location);
                //Toast.makeText(getContext(), "" + location[1], Toast.LENGTH_SHORT).show();
                if(location[1] == 132) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
//                int count = this.getAdapter().getCount();
//                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
//                if (curPosition == count - 1|| curPosition==0) {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                } else {//其他情况，由孩子拦截触摸事件
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }

        }
        return super.dispatchTouchEvent(ev);
    }
}

package com.glg.constraintlayouttest;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Description:
 *
 * @package: com.glg.constraintlayouttest
 * @className: AdHelper
 * @author: gao
 * @date: 2020/8/13 16:17
 */
class AdHelper extends ConstraintHelper {
    public AdHelper(Context context) {
        super(context);
    }

    public AdHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void updatePostLayout(ConstraintLayout container) {
        super.updatePostLayout(container);
        View[] views = getViews(container);
        for (View v : views) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(v, 0, 0, 0f, v.getWidth());
            circularReveal.setDuration(5000);
            circularReveal.start();
        }
    }
}

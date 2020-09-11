package com.glg.animatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startObjectAnimatorAnim(textView);
            }
        });
        final TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startValueAnimatorAnim(textView1);
            }
        });
        final TextView textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPropertyValueHolderAnim(textView2);
            }
        });
        final TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimatorSet(textView3);
            }
        });
        final TextView textView4 = findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEvaluator(textView4);
            }
        });
        final TextView textView01 = findViewById(R.id.textView01);
        final TextView textView02 = findViewById(R.id.textView02);
        final TextView textView03 = findViewById(R.id.textView03);
        final TextView textView04 = findViewById(R.id.textView04);
        final TextView textView05 = findViewById(R.id.textView05);
        final TextView textView06 = findViewById(R.id.textView06);
        final TextView textView07 = findViewById(R.id.textView07);
        final TextView textView08 = findViewById(R.id.textView08);
        final TextView textView09 = findViewById(R.id.textView09);
        final TextView textView10 = findViewById(R.id.textView10);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInterpolatorAnim(textView01, new AccelerateInterpolator());
                startInterpolatorAnim(textView02, new OvershootInterpolator());
                startInterpolatorAnim(textView03, new AccelerateDecelerateInterpolator());
                startInterpolatorAnim(textView04, new AnticipateInterpolator());
                startInterpolatorAnim(textView05, new AnticipateOvershootInterpolator());
                startInterpolatorAnim(textView06, new BounceInterpolator());
                startInterpolatorAnim(textView07, new CycleInterpolator(3f));
                startInterpolatorAnim(textView08, new DecelerateInterpolator());
                startInterpolatorAnim(textView09, new LinearInterpolator());
                startInterpolatorAnim(textView10, new DecelerateAccelerateInterpolator());
            }
        });
    }

    /**
     * ObjectAnimator基本使用继承子ValueAnimator
     * @param v
     */
    public void startObjectAnimatorAnim(View v) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.3f);
        //执行事件
        alphaAnim.setDuration(1000);
        //延迟
        alphaAnim.setStartDelay(300);
        alphaAnim.start();
    }

    /**
     * 在一段时间内生成连续的值完成view的缩放
     * @param v
     */
    public void startValueAnimatorAnim(final View v) {
        //不改变属性大小，只在一段事件内生成连续的值
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 100f);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //百分比对应的值
                float value = (float) animation.getAnimatedValue();
                Log.e("TAG", "onAnimationUpdate: " + value);
                v.setScaleX(0.5f + value / 200);
                v.setScaleY(0.5f + value / 200);
            }
        });
        animator.start();
    }

    /**
     * 一个动画实现多个效果的变换
     * @param v
     */
    public void startPropertyValueHolderAnim(View v) {
        PropertyValuesHolder alphaProper = PropertyValuesHolder.ofFloat("alpha", 1f, 0.3f);
        PropertyValuesHolder scaleXProper = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f);
        PropertyValuesHolder scaleYProper = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f);
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, alphaProper, scaleXProper, scaleYProper);
        animator.setDuration(500);
        animator.start();
    }

    /**
     * 执行多个动画并控制动画顺序
     * @param v
     */
    public void startAnimatorSet(View v) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationX", 0f, 500f);
        animator1.setInterpolator(new DecelerateAccelerateInterpolator());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(v, "scaleX", 0f, 1f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(v, "translationX", 500f, 0f);
        animator4.setInterpolator(new DecelerateAccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        //动画1，2同时执行
        animatorSet.play(animator1).with(animator2);
        //动画2执行完成后执行动画3
        animatorSet.play(animator3).after(animator2);
        //动画1,2,3按顺序执行
        //animatorSet.playSequentially(animator1,animator2,animator3);
        //动画1,2,3同时执行
        //animatorSet.playTogether(animator1,animator2,animator3);
        animatorSet.play(animator4).after(animator3);
        animatorSet.start();
    }

    /**
     * 使用估值器实现重力下落
     * @param v
     */
    public void startEvaluator(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(2000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF pointF = new PointF();
        final float pointx = v.getX();
        final float pointy = v.getY();
        Log.d("Point", v.getX() + "===" + v.getY());
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                //fraction是运动中的匀速变化的值
                //根据重力计算实际的运动y=vt=0.5*g*t*t
                //g越大效果越明显
                pointF.x = 100 * (fraction * 5);
                pointF.y = 0.5f * 300f * (fraction * 5) * (fraction * 5);
                return pointF;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p = (PointF) animation.getAnimatedValue();
                v.setX(p.x);
                v.setY(p.y);
            }
        });
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2200);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            v.setX(pointx);
                            v.setY(pointy);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //插值器可以在java代码中设置，也可以在动画效果的xml文件中进行设定
    /**
     * 为不同的视图添加不同的插值器
     * @param v
     * @param interpolator
     */
    public void startInterpolatorAnim(View v, Interpolator interpolator) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 500f, 0f, 0f);
        translateAnimation.setDuration(5000);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setRepeatCount(Integer.MAX_VALUE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        v.startAnimation(translateAnimation);
    }
}

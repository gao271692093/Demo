package com.glg.countdowntimertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        countDownTimer = new CountDownTimer(1000 * 60 * 60 * 24, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!MainActivity.this.isFinishing()) {

                    long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天

                    long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
                    //单位时
                    long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
                    //单位分
                    long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
                    //单位秒
                    textView.setText(hour + "小时" + minute + "分钟" + second + "秒");
                }
            }

            /**
             *倒计时结束后调用的
             */
            @Override
            public void onFinish() {

            }

        };
        countDownTimer.start();
    }
}

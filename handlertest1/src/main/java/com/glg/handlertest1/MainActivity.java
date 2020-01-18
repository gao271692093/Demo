package com.glg.handlertest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private ProgressBar progressBar;

    private ViewFlipper viewFlipper;

    private Animation[] animations = new Animation[2];

    private final int time  = 60;

    private final int TIME_MESSAGE = 0x001;

    private final int FLAG_MSG = 0x002;

    private int progress = 1000;

    private int images[] = new int[]{R.drawable.art01, R.drawable.art02, R.drawable.art03, R.drawable.art04, R.drawable.art05, R.drawable.art06, R.drawable.art07, R.drawable.art08, R.drawable.art09,R.drawable.art10};

    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        progressBar = findViewById(R.id.timer);
        viewFlipper = findViewById(R.id.viewFlipper);
        for(int i = 0; i < images.length; i += 1) {
            ImageView imageView =  new ImageView(this);
            imageView.setImageResource(images[i]);
            viewFlipper.addView(imageView);
        }
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        animations[0].setDuration(1000);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        animations[1].setDuration(1000);
        viewFlipper.setInAnimation(animations[1]);
        viewFlipper.setOutAnimation(animations[0]);
        message = Message.obtain();
        message.what = FLAG_MSG;
        handler.sendMessageDelayed(message, 3000);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                });
                thread.start();
            }
        });
        handler.sendEmptyMessage(TIME_MESSAGE);

        LooperThread thread = new LooperThread();
        thread.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                textView.setText("你今天的努力，是幸运的伏笔；当下的付出，是明日的花开");
            }
            if(msg.what == TIME_MESSAGE) {
                if(progress > 0) {
                    progress -= 1;
                    progressBar.setProgress(progress);
                    handler.sendEmptyMessageDelayed(TIME_MESSAGE, 10);
                } else {
                    Toast.makeText(MainActivity.this, "时间到，游戏结束", Toast.LENGTH_SHORT).show();
                }
            }
            if(msg.what == FLAG_MSG) {
                viewFlipper.showNext();
                message = handler.obtainMessage(FLAG_MSG);
                handler.sendMessageDelayed(message, 3000);
            }
        }
    };
}

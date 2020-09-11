package com.glg.utiltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.glg.utiltest.R;
import com.glg.utiltest.util.DateUtils;
import com.glg.utiltest.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button customToast;
    private Button threadCustomToast;
    private static MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customToast = findViewById(R.id.custom_toast);
        threadCustomToast = findViewById(R.id.thread_custom_toast);
        customToast.setOnClickListener(this);
        threadCustomToast.setOnClickListener(this);
        Toast.makeText(this, DateUtils.getIntervalTime("2020-09-09 05:24:18", "2020-09-09 08:30:36", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_toast:
                ToastUtil.showToast(MainActivity.this, "这是一个toast", "我就是那个toast");
                break;
            case R.id.thread_custom_toast:
                myHandler = new MyHandler(this);
                myHandler.post(myRunnable);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacks(myRunnable);
        myHandler = null;
    }

    //******************************************************

    /**
     * 创建静态内部类
     */
    private static class MyHandler extends Handler {
        //持有弱引用HandlerActivity,GC回收时会被回收掉.
        private final WeakReference<MainActivity> mActivty;

        MyHandler(MainActivity activity) {
            mActivty = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    MainActivity activity = mActivty.get();
                    if (activity != null) {
                        //执行业务逻辑
                        String text = (String) msg.obj;
                        ToastUtil.showToast(activity, "这是一个toast", text);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            //线程中无法使用Toast，需要将Toast发送至主线程中才能使用
            Message msg = new Message();
            msg.what = 0x01;
            msg.obj = "这是线程的toast";
            myHandler.sendMessage(msg);
        }
    };

}

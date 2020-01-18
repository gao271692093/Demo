package com.glg.servicedemo03;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService1 extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService1(String name) {
        super(name);
    }

    public MyIntentService1() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("IntentService", "已启动");
        long endTime = System.currentTimeMillis() + 20*1000;
        while (System.currentTimeMillis()<endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        Log.i("IntentService", "已停止");
        super.onDestroy();
    }
}

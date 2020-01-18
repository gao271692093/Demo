package com.glg.handlertest1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class LooperThread extends Thread {

    public Handler handler;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i("Looper", String.valueOf(msg.what));
            }
        };
        Message message = handler.obtainMessage();
        message.what = 0x7;
        handler.sendMessage(message);
        Looper.loop();
    }
}

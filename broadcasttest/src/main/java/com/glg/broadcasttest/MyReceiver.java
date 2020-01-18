package com.glg.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String Action1 = "zuckerberg";
    private static final String Action2 = "mayun";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Action1)) {
            Toast.makeText(context, "MyReceiver收到：扎克伯格的广播", Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals("MyBroadcast")) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                String text = bundle.getString("text");
                Toast.makeText(context, "成功接收广播："+text, Toast.LENGTH_SHORT).show();
            }
        }  else {
            Toast.makeText(context, "MyReceiver收到马云的广播", Toast.LENGTH_SHORT).show();
        }
    }
}

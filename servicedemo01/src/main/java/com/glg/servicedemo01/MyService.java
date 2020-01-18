package com.glg.servicedemo01;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    public static boolean isplay;
    private MediaPlayer mediaPlayer;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i("Service", "已创建");
        mediaPlayer = MediaPlayer.create(this, R.raw.background);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service", "已开启");
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isplay = mediaPlayer.isPlaying();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("Service", "已停止");
        mediaPlayer.stop();
        isplay = mediaPlayer.isPlaying();
        mediaPlayer.release();
        super.onDestroy();
    }

    //判断Service是否正在运行
    public boolean isRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //获取所有正在运行的Service
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)activityManager.getRunningServices(10);
        for(int i = 0; i <runningService.size(); i += 1) {
            if(runningService.get(i).service.getClassName().toString().equals("com.glg.servicedemo01.MainActivity")) {
                return true;
            }
        }
        return false;
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }
}

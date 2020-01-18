package com.glg.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    final int NotifyId = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.获取通知管理器类
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /**
         * 兼容Android版本8.0系统
         */
        String channeId = "1";
        String channelName = "default";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channeId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);         // 开启指示灯，如果设备有的话
            channel.setLightColor(Color.RED);   // 设置指示灯颜色
            channel.setShowBadge(true);         // 检测是否显示角标
            notificationManager.createNotificationChannel(channel);
        }
        //2.构建通知类
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "1");
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setContentTitle("微信");//标题
        builder.setContentText("您有一条未读消息!");//内容
        builder.setWhen(System.currentTimeMillis());    //时间
        builder.setAutoCancel(true);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        //3.获取通知
        Notification notification = builder.build();

        //4.发送通知
        notificationManager.notify(100, notification);

//        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_SERVICE, "123", NotificationManager.IMPORTANCE_DEFAULT);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannel(notificationChannel);
//        Notification.Builder builder = new Notification.Builder(MainActivity.this);
//        builder.setAutoCancel(true);
//        builder.setChannelId("001");
//        builder.setSmallIcon(R.drawable.ic_launcher_background);
//        builder.setContentTitle("奖励百万红包!!!");
//        builder.setContentText("点击查看详情");
//        builder.setWhen(System.currentTimeMillis());
//        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
//
//        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//        builder.setContentIntent(pendingIntent);
//        Notification notification = builder.build();
//        notificationManager.notify(NotifyId, notification);
    }
}

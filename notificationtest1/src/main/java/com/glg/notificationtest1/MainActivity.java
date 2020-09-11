package com.glg.notificationtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int NOTIFICATIONID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                File file = new File("/system/media/audio/ringtones/Atria.ogg");
                Log.i("Notification", "===>>>" + file.exists());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//有效
                    NotificationChannel notificationChannel = new NotificationChannel("myChannel", "我的渠道名字 ", NotificationManager.IMPORTANCE_LOW);
                    Toast.makeText(this, notificationChannel.toString(), Toast.LENGTH_SHORT).show();
                    notificationManager.createNotificationChannel(notificationChannel);
                    Notification notification = new Notification.Builder(this)
                            .setChannelId("myChannel")
                            .setContentTitle("Title")
                            .setContentText("Learn how to build notifications, send and sync dta, and use voice actions. Get the official Android IDE and developer tools to build apps for  Android.")
                            .setStyle(new Notification.BigTextStyle().bigText("Learn how to build notifications, send and sync dta, and use voice actions. Get the official Android IDE and developer tools to build apps for  Android."))
                            .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.art01)))
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setLights(Color.GREEN, 1000, 1000)
                            //.setSound(FileProvider.getUriForFile(getApplicationContext(),"com.glg.notificationtest1.fileprovider",new File("/system/product/media/audio/ringtones/Atria.ogg")))//待解决
                            .setVibrate(new long[]{0, 1000, 1000, 1000})
                            //.setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.art01)))//待解决
                            //.setDefaults(NotificationCompat.DEFAULT_ALL)
                            //.setPriority(Notification.PRIORITY_MAX) //设置通知的重要程度
                            .build();
                    notificationManager.notify(111, notification);
                } else {//无效
                    Notification notification = new NotificationCompat.Builder(this)
                            .setContentTitle("This is content title")
                            .setContentText("This is content text")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setChannelId("001")
                            .setAutoCancel(true)
                            .setLights(Color.GREEN, 1000, 1000)
                            .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Atria.ogg")))
                            .setVibrate(new long[]{0, 1000, 1000, 1000})
                            .build();
                    notificationManager.notify(NOTIFICATIONID,notification);
                }
                break;
            default:
                break;
        }
    }
}

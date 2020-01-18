package com.glg.createtimertasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //第一种方式
        //long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;//获取系统开机到现在的时间毫秒数
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);//pendingIntent是我们自己定义的Intent

        //第二种方式
        //long triggerAtTime = System.currentTimeMillis() + 10 * 1000;
        //alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);

        //第三种方式
        Intent intent = new Intent(MainActivity.this, LongRunningService.class);
        MainActivity.this.startService(intent);

        //从Android4.4系统开始，Alarm任务的触发时间将会变得不准确，有可能会延时一段时间后才能得到执行。这并不是bug，而是系统在耗电性方面进行的优化。
        //如果你要求Alarm任务的执行时间必须准确无误，可以使用AlarmManager的setExact()方法来替代set()方法，就基本上可以保证任务能够准时执行了。

        //Doze模式下，我们的 Alarm任务也将会变得不准时，如果真的有非常特殊的需求，要求alarm任务即使在Doze模式下也必须正常执行，可以调用AlarmManager的setAndAllowWhileIdle()或setExactAndAllowWhileIdle()方法，这两个方法之间的区别和set()、setExact()方法之间的区别是一样的。
    }
}

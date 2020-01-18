package com.glg.actionbartest3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置ActionBar为选项卡模式，并将标签页添加到 ActionBar当中
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//设置ActionBar采用选项卡模式
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);//隐藏标题栏

        //把各个标签页添加到ActionBar当中
        actionBar.addTab(actionBar.newTab().setText("空旷").setTabListener(new MyTabListener(this, Fragment1.class)));
        actionBar.addTab(actionBar.newTab().setText("美丽").setTabListener(new MyTabListener(this, Fragment2.class)));
        actionBar.addTab(actionBar.newTab().setText("清香").setTabListener(new MyTabListener(this, Fragment3.class)));
        actionBar.addTab(actionBar.newTab().setText("艳丽").setTabListener(new MyTabListener(this, Fragment4.class)));
        actionBar.addTab(actionBar.newTab().setText("神往").setTabListener(new MyTabListener(this, Fragment5.class)));
    }
}

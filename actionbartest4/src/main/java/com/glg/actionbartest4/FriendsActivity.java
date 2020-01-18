package com.glg.actionbartest4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        //判断父Activity是否为空 ，如果 不为空设置导航图标显示
        if(NavUtils.getParentActivityName(FriendsActivity.this)!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

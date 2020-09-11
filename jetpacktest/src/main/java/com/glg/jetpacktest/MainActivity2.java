package com.glg.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.glg.jetpacktest.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding activityMain2Binding = DataBindingUtil.setContentView(MainActivity2.this, R.layout.activity_main2);
        final User user = new User();
        user.userName.set("小不点-初始值");
        user.userGender.set("男");
        activityMain2Binding.setUser(user);

        activityMain2Binding.buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.userName.set("子龙-改变后的值");
            }
        });

        final Dog dog = new Dog("小不点-可变对象", "雄");
        activityMain2Binding.setDog(dog);
        activityMain2Binding.buttonChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dog.setName("大黄-数据更新");
                dog.notifyChange();
            }
        });
    }
}
package com.glg.viewmodelandlivedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AccountModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView mText = findViewById(R.id.textView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_1, new TopFragment("TopFragment")).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_2, new BottomFragment("BottomFragment")).commit();
        mModel = ViewModelProviders.of(this).get(AccountModel.class);
        findViewById(R.id.main_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.setAccount("Change_all", "183****3101", "https://blog.csdn.net/Change_all");
            }
        });
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getPhone(), accountBean.getBlog()));
            }
        });
        getLifecycle().addObserver(new StudyLifecycle("MainActivity"));
    }
}

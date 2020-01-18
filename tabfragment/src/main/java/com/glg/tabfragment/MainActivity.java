package com.glg.tabfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1 = findViewById(R.id.image1);
        ImageView imageView2 = findViewById(R.id.image2);
        ImageView imageView3 = findViewById(R.id.image3);
        ImageView imageView4 = findViewById(R.id.image4);
        imageView1.setOnClickListener(l);
        imageView2.setOnClickListener(l);
        imageView3.setOnClickListener(l);
        imageView4.setOnClickListener(l);
    }

    View.OnClickListener l = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;
            switch(v.getId()) {
                case R.id.image1:
                    f = new WeChatFragment();
                    break;
                case R.id.image2:
                    f = new MessageFragment();
                    break;
                case R.id.image3:
                    f = new FindFragment();
                    break;
                case R.id.image4:
                    f = new MeFragment();
                    break;
                default:
                    break;
            }
            ft.replace(R.id.fragment, f);
            ft.commit();
        }
    };
}

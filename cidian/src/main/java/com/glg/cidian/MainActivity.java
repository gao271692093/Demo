package com.glg.cidian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = findViewById(R.id.ll);
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(this);
        ll.addView(scrollView);
        scrollView.addView(ll1);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.cidian);
        ll1.addView(imageView);
        TextView textView = new TextView(this);
        textView.setText(R.string.cidian);
        ll1.addView(textView);
    }
}

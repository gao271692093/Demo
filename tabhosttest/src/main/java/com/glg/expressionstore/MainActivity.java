package com.glg.expressionstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2").setContent(R.id.right));
    }
}

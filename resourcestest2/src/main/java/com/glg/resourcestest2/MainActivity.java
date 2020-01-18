package com.glg.resourcestest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] textViewId = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] colors = getResources().getIntArray(R.array.bgcolor);
        String[] arrs = getResources().getStringArray(R.array.word);
        for(int i = 0; i < textViewId.length; i += 1) {
            TextView textView = findViewById(textViewId[i]);
            textView.setBackgroundColor(colors[i]);
            textView.setText(arrs[i]);
        }
    }
}

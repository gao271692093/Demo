package com.glg.sendobjecttest_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Person person = getIntent().getParcelableExtra("person_data");
        TextView textView = findViewById(R.id.textView);
        textView.setText(person.getName() + " " + person.getAge());
    }
}

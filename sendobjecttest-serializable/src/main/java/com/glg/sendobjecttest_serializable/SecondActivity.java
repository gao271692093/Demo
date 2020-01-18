package com.glg.sendobjecttest_serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Person person = (Person) getIntent().getSerializableExtra("person_data");
        TextView textView = findViewById(R.id.textView);
        textView.setText(person.getName() + " " + person.getAge());
    }
}

package com.glg.sendobjecttest_serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendObject = findViewById(R.id.send_object);
        sendObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName("Tom");
                person.setAge(20);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("person_data", person);
                startActivity(intent);
            }
        });
    }
}

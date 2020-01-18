package com.glg.accessadress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String phone = bundle.getString("phone");
        String yb = bundle.getString("yb");
        String prinvince = bundle.getString("prinvince");
        String details = bundle.getString("details");
        TextView textView1 = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.phone);
        TextView textView3 = findViewById(R.id.address);
        TextView textView4 = findViewById(R.id.yb);
        textView1.setText(name);
        textView2.setText(phone);
        textView3.setText(prinvince + details);
        textView4.setText(yb);
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

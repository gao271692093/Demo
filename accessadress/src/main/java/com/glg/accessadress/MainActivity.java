package com.glg.accessadress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.address_name)).getText().toString();
                String phone = ((EditText)findViewById(R.id.address_phone)).getText().toString();
                String prinvince = ((EditText)findViewById(R.id.address_prinvince)).getText().toString();
                String details = ((EditText)findViewById(R.id.address_details)).getText().toString();
                String yb = ((EditText)findViewById(R.id.address_yb)).getText().toString();
                if("".equals(name) || "".equals(phone) || "".equals(prinvince) || "".equals(details) || "".equals(yb)) {
                    Toast.makeText(MainActivity.this, "请将收货地址信息填写完整：", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("name",name);
                    bundle.putCharSequence("phone", phone);
                    bundle.putCharSequence("prinvince", prinvince);
                    bundle.putCharSequence("details", details);
                    bundle.putCharSequence("yb", yb);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}

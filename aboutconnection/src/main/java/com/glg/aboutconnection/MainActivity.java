package com.glg.aboutconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton1 = findViewById(R.id.imagebutton1);
        ImageButton imageButton2 = findViewById(R.id.imagebutton2);
        imageButton1.setOnClickListener(clickListener);
        imageButton2.setOnClickListener(clickListener);
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            ImageButton imageButton = (ImageButton)v;
            switch (imageButton.getId()) {
                case R.id.imagebutton1:
                    intent.setAction(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:111-111"));
                    startActivity(intent);
                    break;
                case R.id.imagebutton2:
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:111-111"));
                    intent.putExtra("sms_body","welcome to android!");
                    startActivity(intent);
                    break;
            }
        }
    };
}

package com.glg.selectico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends AppCompatActivity {

    int ico[] = {R.drawable.ico1,R.drawable.ico2,R.drawable.ico3,R.drawable.ico4,R.drawable.ico5,R.drawable.ico6,R.drawable.ico7,R.drawable.ico8,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        GridView gridView = findViewById(R.id.gridview);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return ico.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if(convertView == null) {
                    imageView = new ImageView(HeadActivity.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxWidth(158);
                    imageView.setMaxHeight(150);
                    imageView.setPadding(5,5,5,5);
                } else {
                    imageView = (ImageView)convertView;
                }
                imageView.setImageResource(ico[position]);
                return imageView;
            }
        };
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("ico",ico[position]);
                intent.putExtras(bundle);
                setResult(0x11, intent);
                finish();
            }
        });
    }
}

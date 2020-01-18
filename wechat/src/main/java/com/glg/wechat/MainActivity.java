package com.glg.wechat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int art[] = new int[]{R.mipmap.art01,R.mipmap.art02,R.mipmap.art03,R.mipmap.art04,R.mipmap.art05,R.mipmap.art06,R.mipmap.art07,R.mipmap.art08,R.mipmap.art09,R.mipmap.art10};
        String items[] = new String[] {"张三","李四","王五","赵云","马超","曹操","李白","刘备","司马懿","赵子龙"};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < items.length; i += 1) {
            Map map = new HashMap<String, Object>();
            map.put("name",items[i]);
            map.put("image",art[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.main,new String[]{"name","image"},new int[]{R.id.textView,R.id.imageView});
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, map.get("name").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

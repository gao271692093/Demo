package com.glg.sqlite_dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbOpenHelper = new DBOpenHelper(this, "db_dict", null, 1);

        final ListView listView = findViewById(R.id.listView);
        final EditText editText = findViewById(R.id.editText);
        Button translation = findViewById(R.id.button_translate);
        Button add = findViewById(R.id.button_add);
        translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editText.getText().toString();
                Cursor cursor = dbOpenHelper.getReadableDatabase().query("tb_dict", null, "word=?",new String[]{key},null,null,null);
                ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
                while(cursor.moveToNext()) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("word", cursor.getString(1));
                    map.put("interpret", cursor.getString(2));
                    list.add(map);
                }
                Log.d("日志","log=====>" + (list == null) + list.size());
                if(list == null || list.size()==0) {
                    Toast.makeText(MainActivity.this, "很遗憾，没有相关记录", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, list, R.layout.result, new String[]{"word", "interpret"}, new int[]{R.id.text_word, R.id.text_interpret});
                    listView.setAdapter(simpleAdapter);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbOpenHelper!=null) {
            dbOpenHelper.close();
        }
    }
}

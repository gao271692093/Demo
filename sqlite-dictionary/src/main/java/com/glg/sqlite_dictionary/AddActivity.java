package com.glg.sqlite_dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private DBOpenHelper dbOpenHelper;

    private EditText edit_words;

    private EditText edit_translation;

    private Button button_save;

    private Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbOpenHelper = new DBOpenHelper(AddActivity.this, "db_dict", null, 1);
        edit_words = findViewById(R.id.edit_words);
        edit_translation = findViewById(R.id.edit_translation);
        button_save = findViewById(R.id.button_save);
        button_cancel = findViewById(R.id.button_cancel);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = edit_words.getText().toString();
                String interpret = edit_translation.getText().toString();
                if("".equals(words) || "".equals(interpret)) {
                    Toast.makeText(AddActivity.this, "请填写单词和翻译", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(dbOpenHelper.getReadableDatabase(), words, interpret);
                    Toast.makeText(AddActivity.this, "添加生词成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData(SQLiteDatabase sqLiteDatabase, String word, String interpret) {
        ContentValues values = new ContentValues();
        values.put("word", word);
        values.put("interpret", interpret);
        sqLiteDatabase.insert("tb_dict", null, values);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbOpenHelper!=null) {
            dbOpenHelper.close();
        }
    }
}

package com.glg.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  String columns = ContactsContract.Contacts.DISPLAY_NAME;//希望获得姓名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        textView.setText(getQueryData());
    }

    private CharSequence getQueryData() {
        StringBuilder stringBuilder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int displayNameIndex = cursor.getColumnIndex(columns);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String displayName = cursor.getString(displayNameIndex);
            stringBuilder.append(displayName + "\n");
        }
        cursor.close();
        return stringBuilder.toString();
    }
}

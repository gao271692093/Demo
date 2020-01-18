package com.glg.alermtest;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlermActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setIcon(R.drawable.alerm);
        alertDialog.setTitle("传递正能量：");
        alertDialog.setMessage("要么出众，要么出局");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}

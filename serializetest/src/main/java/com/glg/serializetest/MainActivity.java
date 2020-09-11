package com.glg.serializetest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { // 判断手机 是否安装SD卡
            Toast.makeText(this, "请安装sd卡", Toast.LENGTH_SHORT).show();
        } else {
            if(!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                try {
                    FileOutputStream fos = new FileOutputStream(getExternalCacheDir() + "/object.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    Student student1 = new Student("小不点", "666666", "21");
                    Teacher teacher1 = new Teacher("Jully", "女", 25);
                    oos.writeObject(student1);
                    //student1.writeObject(oos);
                    oos.writeObject(teacher1);
                    oos.flush();
                    oos.close();

                    FileInputStream fis = new FileInputStream(getExternalCacheDir() + "/object.txt");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Student student2 = (Student) ois.readObject();
//                    Student student2 = new Student();
//                    student2.readObject(ois);
                    Teacher teacher2 = (Teacher) ois.readObject();
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(student2.getUserName()+ " " +
                            student2.getPassword() + " " + student2.getYears() + "\n" + teacher2.getName() + " " + teacher2.getSex() + " " + teacher2.getYears());
                    ois.close();
                }catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if(!(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "拒绝权限无法继续操作哦", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

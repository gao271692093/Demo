package com.glg.alertdialog2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带取消、确定按钮的对话框
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(R.drawable.ico1);
                alertDialog.setTitle("乔布斯：");
                alertDialog.setMessage("活着就是为了改变世界，难道还有其他原因吗？");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您点击了否按钮", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您点击了是按钮", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带四个列表项的列表对话框
                final String items[] = {"当你有使命，它会让你更专注", "要么出众，要么出局", "活着就是为了改变世界", "求知若饥，虚心若愚"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.orange_pic);
                builder.setTitle("请选择你喜欢的名言");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您选择了【" + items[which] + "】", Toast.LENGTH_LONG).show();
                    }
                });
                builder.create().show();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带单选列表项的对话框
                final String items[] = {"马云","马化腾","王健林"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.lock);
                builder.setTitle("如果让你选择，你最想做哪一个？");
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您选择了【"+items[which]+"】", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setPositiveButton("确定", null);
                builder.create().show();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带多选列表项的对话框
                final boolean[] chedkedItems = new boolean[]{false,true, false, true, false};
                final String[] items = new String[]{"球球大作战", "开心消消乐 ","天天酷跑","欢乐斗地主","王者荣耀"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.apple_pic);
                builder.setTitle("请选择你喜爱的游戏：");
                builder.setMultiChoiceItems(items, chedkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        chedkedItems[which] = isChecked;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = "";
                        for(int i = 0; i < chedkedItems.length; i += 1) {
                            if(chedkedItems[i] == true) {
                                result += items[i] + "、";
                            }
                        }
                        if(!"".equals(result)) {
                            Toast.makeText(MainActivity.this, result,Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
}

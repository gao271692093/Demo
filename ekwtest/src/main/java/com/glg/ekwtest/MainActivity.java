package com.glg.ekwtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment1;
    private Fragment fragment2;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        final FragmentManager fragmentManager = getFragmentManager();
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        button1.setBackgroundColor(getResources().getColor(R.color.background));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundColor(getResources().getColor(R.color.background));
                button2.setBackgroundColor(getResources().getColor(R.color.nul));
                button3.setBackgroundColor(getResources().getColor(R.color.nul));
                if(fragment1 == null) {
                    fragment1 = new EkwContentFragment3();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment2, fragment1);
                fragmentTransaction.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setBackgroundColor(getResources().getColor(R.color.background));
                button1.setBackgroundColor(getResources().getColor(R.color.nul));
                button3.setBackgroundColor(getResources().getColor(R.color.nul));
                if(fragment2 == null) {
                    fragment2 = new EkwContentFragment2();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment2, fragment2);
                fragmentTransaction.commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = {"清理缓存", "版本更新", "隐私政策", "当前版本"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.settings);
                builder.setTitle("设置");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                alertDialog.setMessage("确定清楚已缓存的课程资源吗？\n清除后需要重新下载哦");
                                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(MainActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(MainActivity.this, "您点击了确定按钮", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                alertDialog.show();
                                break ;
                            case 1:
                                Toast.makeText(MainActivity.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
                                break ;
                            case 2:
                                Intent intent = new Intent(MainActivity.this, PrivateActivity.class);
                                startActivity(intent);
                                break ;
                            case 3:
                                Toast.makeText(MainActivity.this, "当前版本V2.3.1", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("退出登录");
                        alertDialog.setMessage("确定要退出登录吗？");
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您点击了取消按钮", Toast.LENGTH_LONG).show();
                            }
                        });
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                                manager.restartPackage(getPackageName());
                            }
                        });
                        alertDialog.show();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if(System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出应用程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    private long getCache() {
        long cache = 32l;
        return cache;
    }
}

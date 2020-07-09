package com.glg.goodluck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.glg.goodluck.Utils.DoubleColorBallUtils;
import com.glg.goodluck.Utils.FunTenMinutesUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment1;
    private Fragment fragment2;
    private long exitTime = 0;
    private StringBuilder kinds = new StringBuilder();
    private int num = 3;
    private List<DoubleColorBallItem> doubleColorBallItemList = new ArrayList<DoubleColorBallItem>();
    private List<FunTenMinutesItem> funTenMinutesItemList = new ArrayList<FunTenMinutesItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new DoubleColorBallFragment();
        fragment2 = new FunTenMinutesFragment();
        getSupportActionBar().hide();
        final FragmentManager fragmentManager = getFragmentManager();
        final TextView doubleColorBall = findViewById(R.id.menu_double_color_ball);
        final TextView funTenMinutes = findViewById(R.id.menu_fun_ten_minute);
        TextView settings = findViewById(R.id.menu_settings);
        doubleColorBall.setBackgroundColor(getResources().getColor(R.color.background));
        doubleColorBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleColorBall.setBackgroundColor(getResources().getColor(R.color.background));
                funTenMinutes.setBackgroundColor(getResources().getColor(R.color.nul));
                if (fragment1 == null) {
                    fragment1 = new DoubleColorBallFragment();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.remove(fragment2);
                //fragmentTransaction.add(R.id.fragment_content, fragment1);
                fragmentTransaction.replace(R.id.fragment_content, fragment1);
                if(fragment2!=null) {
                    fragmentTransaction.hide(fragment2);
                }
                fragmentTransaction.show(fragment1);
                fragmentTransaction.commit();
            }
        });
        funTenMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funTenMinutes.setBackgroundColor(getResources().getColor(R.color.background));
                doubleColorBall.setBackgroundColor(getResources().getColor(R.color.nul));
                if(fragment2 == null) {
                    fragment2 = new FunTenMinutesFragment();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.remove(fragment1);
                //fragmentTransaction.add(R.id.fragment_content, fragment2);
                fragmentTransaction.replace(R.id.fragment_content, fragment2);
                if(fragment1!=null) {
                    fragmentTransaction.hide(fragment1);
                }
                fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
            }
        });
        Button getOne = findViewById(R.id.btn_getOne);
        getOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fragment1.isHidden()) {
                    List<DoubleColorBallItem> list = DoubleColorBallUtils.getItem();
                    for(int i = 0; i < list.size(); i += 1) {
                        doubleColorBallItemList.add(list.get(i));
                    }
                    DoubleColorBallAdapter doubleColorBallAdapter = new DoubleColorBallAdapter(doubleColorBallItemList);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView_double_color_ball);
                    Log.i("..................",Boolean.toString(recyclerView == null));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(doubleColorBallAdapter);
                    for(int i = 0; i < funTenMinutesItemList.size(); i += 1) {
                        Log.i("++++++++++++",funTenMinutesItemList.get(i).toString());
                    }
                } else {
                    List<FunTenMinutesItem> list = FunTenMinutesUtils.getItem(kinds.delete(0, kinds.length()).append("任选三").toString(), num);
                    for(int i = 0; i < list.size(); i += 1) {
                        funTenMinutesItemList.add(list.get(i));
                    }
                    FunTemMinutesAdapter funTemMinutesAdapter = new FunTemMinutesAdapter(funTenMinutesItemList);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView_fun_ten_minutes);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(funTemMinutesAdapter);
                }
            }
        });
        Button getFive = findViewById(R.id.btn_getFive);
        getFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
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
}

package com.glg.servicedemo02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private long exitTime = 0;

    private BinderService binderService;

    private List<Item> itemList = new ArrayList<Item>();

    //private int[] textViews = new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7};

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderService = ((BinderService.MyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectRandom = findViewById(R.id.select_random);
        selectRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<Integer> number = binderService.getRandomRed();
//                List<String> numbers = fix(number);
//                for(int i = 0; i < number.size(); i += 1) {
//                    TextView textView = findViewById(textViews[i]);
//                    textView.setText(numbers.get(i));
//                }
                List<String> numbers = fix(binderService.getRandomRed());
                Item item = new Item(numbers.get(0),numbers.get(1),numbers.get(2),numbers.get(3),numbers.get(4),numbers.get(5),numbers.get(6));
                itemList.add(item);
                ItemAdapter itemAdapter = new ItemAdapter(itemList);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(itemAdapter);
            }
        });
        Button selectRandomFive = findViewById(R.id.select_random_five);
        selectRandomFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 5; i += 1) {
                    List<String> numbers = fix(binderService.getRandomRed());
                    Item item = new Item(numbers.get(0),numbers.get(1),numbers.get(2),numbers.get(3),numbers.get(4),numbers.get(5),numbers.get(6));
                    itemList.add(item);
                }
                ItemAdapter itemAdapter = new ItemAdapter(itemList);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(itemAdapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, BinderService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    private List<String> fix(List<Integer> list) {
        int t = 0;
        int i = 0;
        for(i = 0; i < 6; i += 1) {
            for(int j = 0; j < 6 - i - 1; j += 1) {
                if(list.get(j) >  list.get(j + 1)) {
                    t = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, t);
                }
            }
        }
        List<String> list1 = new ArrayList<String>();
        String string;
        for(i = 0; i < list.size(); i += 1) {
            if(list.get(i) < 10) {
                string = "0" + String.valueOf(list.get(i));
            } else {
                string = String.valueOf(list.get(i));
            }
            list1.add(string);
        }
        return list1;
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

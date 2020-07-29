package com.glg.headerintoplist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Group> groups;

    private TextView title;

//    private int position = 0;
//
//    private int locations[];
//
//    private View view;

    private LinearLayoutManager linearLayoutManager;

    private int[] fruits =  {R.drawable.apple_pic,R.drawable.banana_pic, R.drawable.grape_pic, R.drawable.orange_pic,
            R.drawable.watermelon_pic, R.drawable.cherry_pic, R.drawable.mango_pic, R.drawable.pear_pic, R.drawable.pineapple_pic, R.drawable.strawberry_pic};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);

        recyclerView = findViewById(R.id.recyclerView);
        groups = new ArrayList<Group>();
        init(groups);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        GroupAdapter groupAdapter = new GroupAdapter(MainActivity.this, groups);
        recyclerView.setAdapter(groupAdapter);
//        locations = new int[2];
//        title.setText(groups.get(0).getGroupName());
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                view = recyclerView.getChildAt(position);
//                view.getLocationOnScreen(locations);
//                if(locations[1] < 80) {
//                    title.setText(groups.get(linearLayoutManager.findFirstVisibleItemPosition()).getGroupName());
//                }
//                return false;
//            }
//        });

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                title.setText(groups.get(linearLayoutManager.findFirstVisibleItemPosition()).getGroupName());
            }
        });
    }

    private void init(List<Group> groups) {
        for(int i = 0;  i < 10; i  += 1) {
            List<Item> itemList = new ArrayList<>();
            initItem(i + 1, itemList);
            Group group = new Group("第" + (i + 1) + "组", itemList);
            groups.add(group);
        }
    }

    private void initItem(int s, List<Item> list) {
        int k = (int)(Math.random() * 10) + 1;
        for(int i = 0; i < k; i += 1) {
            Item item = new Item("第" + s +"组，Fruit" + i, fruits[(int)(Math.random()*10)]);
            list.add(item);
        }
    }
}

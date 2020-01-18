package com.glg.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private String[] data = {"Apple", "Banana", "Orange", "libai", "Caocao", "Lvbu", "DiaoChan", "LiuBei", "SunShangXiang", "SunWuKong", "ZhuBaJie",
//     "DaJi", "MingShiYing", "ZhuGeLiang"};

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,data);
////        ListView listView = findViewById(R.id.listView);
////        listView.setAdapter(adapter);
        initItems();
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.item_layout, itemList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item  = itemList.get(position);
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initItems() {
        for(int i = 0; i < 4; i += 1) {
            Item apple = new Item("Apple", R.drawable.apple_pic);
            itemList.add(apple);
            Item banana = new Item("banana", R.drawable.banana_pic);
            itemList.add(banana);
            Item orange = new  Item("orange", R.drawable.orange_pic);
            itemList.add(orange);
            Item grape = new Item("grape", R.drawable.grape_pic);
            itemList.add(grape);
            Item watermelon = new Item("watermelon", R.drawable.watermelon_pic);
            itemList.add(watermelon);
        }
    }
}

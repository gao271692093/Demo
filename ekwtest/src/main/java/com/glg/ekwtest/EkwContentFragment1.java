package com.glg.ekwtest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EkwContentFragment1 extends Fragment {

    String messages[];

    private List<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ekw_content_fragment1, container, false);
        messages = getResources().getStringArray(R.array.listitem);
        itemList = new ArrayList<>();
        initItems();
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), R.layout.item_layout, itemList);
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//        ItemsAdapter itemsAdapter = new ItemsAdapter(itemList);
//        itemsAdapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//
//            }
//
//            @Override
//            public void onLongClick(int position) {
//
//            }
//        });
//        recyclerView.setAdapter(itemsAdapter);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item  = itemList.get(position);
                Toast.makeText(getActivity(), item.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initItems() {
        for(int i = 0; i < messages.length; i += 1) {
            Item item = new Item(messages[i], ">");
            itemList.add(item);
        }
    }
}

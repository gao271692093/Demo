package com.glg.ekwtest;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EkwContentFragment3 extends Fragment {

    String messages[];

    private List<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ekw_content_fragment3, container, false);
        messages = getResources().getStringArray(R.array.listitem);
        itemList = new ArrayList<>();
        initItems();
        ItemsAdapter itemsAdapter = new ItemsAdapter(itemList);
        itemsAdapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), messages[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemsAdapter);
        return view;
    }

    private void initItems() {
        for(int i = 0; i < messages.length; i += 1) {
            Item item = new Item(messages[i], ">");
            itemList.add(item);
        }
    }
}

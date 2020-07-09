package com.glg.goodluck;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoubleColorBallFragment extends Fragment {

    private List<DoubleColorBallItem> list = new ArrayList<DoubleColorBallItem>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_double_color_ball, container, false);
        DoubleColorBallAdapter adapter = new DoubleColorBallAdapter(list);
        adapter.setOnItemClickListener(new DoubleColorBallAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_double_color_ball);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public List<DoubleColorBallItem> getList() {
        return list;
    }

    public void setList(List<DoubleColorBallItem> list) {
        this.list = list;
    }
}

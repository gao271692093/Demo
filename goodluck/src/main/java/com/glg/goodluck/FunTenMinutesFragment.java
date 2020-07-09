package com.glg.goodluck;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FunTenMinutesFragment extends Fragment {

    private List<FunTenMinutesItem> list = new ArrayList<FunTenMinutesItem>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fun_ten_minutes, container, false);
        FunTemMinutesAdapter funTemMinutesAdapter = new FunTemMinutesAdapter(list);
        funTemMinutesAdapter.setOnItemClickListener(new FunTemMinutesAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fun_ten_minutes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(funTemMinutesAdapter);
        return view;
    }
}

package com.glg.goodluck;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FunTemMinutesAdapter extends RecyclerView.Adapter {

    private List<FunTenMinutesItem> itemList;

    private OnItemClickListener itemClickListener;

    public FunTemMinutesAdapter(List<FunTenMinutesItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView kinds;
        TextView digital;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kinds = itemView.findViewById(R.id.fun_ten_minutes_kinds);
            digital = itemView.findViewById(R.id.fun_ten_minutes_digital);
            num = itemView.findViewById(R.id.fun_ten_minutes_num);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }
}

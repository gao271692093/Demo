package com.glg.servicedemo02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;

    private OnItemClickListener mOnItemClickListener;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Item item = itemList.get(position);
        holder.textView1.setText(item.getOne());
        holder.textView2.setText(item.getTwo());
        holder.textView3.setText(item.getThree());
        holder.textView4.setText(item.getOne());
        holder.textView5.setText(item.getTwo());
        holder.textView6.setText(item.getThree());
        holder.textView7.setText(item.getSeven());
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;

        public ViewHolder(@NonNull View view) {
            super(view);
            textView1 = view.findViewById(R.id.item_textView1);
            textView2 = view.findViewById(R.id.item_textView2);
            textView3 = view.findViewById(R.id.item_textView3);
            textView4 = view.findViewById(R.id.item_textView4);
            textView5 = view.findViewById(R.id.item_textView5);
            textView6 = view.findViewById(R.id.item_textView6);
            textView7 = view.findViewById(R.id.item_textView7);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

package com.glg.headerintoplist1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private int resourceId;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        //普通方法
        //View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        //优化
        /*
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }
        ImageView itemImage = view.findViewById(R.id.item_itemId);
        TextView textName = view.findViewById(R.id.item_name);
        itemImage.setImageResource(item.getImageId());
        textName.setText(item.getName());
         */
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.itemImage = view.findViewById(R.id.item_itemId);
            viewHolder.itemName = view.findViewById(R.id.item_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.itemImage.setImageResource(item.getImageId());
        viewHolder.itemName.setText(item.getName());
        return view;
    }

    class ViewHolder {
        ImageView itemImage;
        TextView itemName;
    }
}

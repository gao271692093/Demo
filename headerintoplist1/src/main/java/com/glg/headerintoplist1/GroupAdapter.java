package com.glg.headerintoplist1;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by gao on 2020/7/27 16:12.
 * Function:
 */
class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private Context context;

    private List<Group> groupList;

    public GroupAdapter(Context context,List<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        Group group = groupList.get(position);
        holder.groupContent.setAdapter(new ItemAdapter(context, R.layout.item_layout, group.getGroupContent()));
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ListView groupContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupContent = itemView.findViewById(R.id.group_content);
        }
    }
}

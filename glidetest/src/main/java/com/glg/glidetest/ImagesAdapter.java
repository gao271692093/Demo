package com.glg.glidetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Description:
 *
 * @package: com.glg.mygif
 * @className: ImagesAdapter
 * @author: gao
 * @date: 2020/8/10 18:55
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<ImageItem> list;

    private ImageItem imageItem;

    private Context context;

    public ImagesAdapter(Context context, List<ImageItem> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<ImageItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null);
        ImagesAdapter.ViewHolder holder = new ImagesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        imageItem = list.get(position);
        Glide.with(context)
                .load("https://cn.bing.com/" + imageItem.getUrl())
                //.transform(new CircleCrop())//图像变换
                //.diskCacheStrategy(DiskCacheStrategy.NONE)//禁用Glide的缓存功能
                //.placeholder(R.drawable.loading)//占位图
                //.error(R.drawable.error)//异常占位图
                //.asGif()//设置图片格式
                //.override(100, 100)//设置图片大小，单位是像素
                //.centerCrop()
                .into(holder.imageView);
        holder.textView.setText(imageItem.getCopyright());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}

package com.glg.okhttptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    private Activity activity;

    private ViewHolder holder;

    private static final int SUCCESS = 1;
    private static final int FALL = 2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //加载网络成功进行UI的更新,处理得到的图片资源
                case SUCCESS:
                    //通过message，拿到字节数组
                    byte[] Picture = (byte[]) msg.obj;
                    //使用BitmapFactory工厂，把字节数组转化为bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Picture, 0, Picture.length);
                    //通过imageview，设置图片
                    holder.imageView.setImageBitmap(bitmap);

                    break;
                //当加载网络失败执行的逻辑代码
                case FALL:
                    break;
            }
        }
    };

    public ImagesAdapter(Activity activity, List<ImageItem> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        final ImagesAdapter.ViewHolder holder = new ImagesAdapter.ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        imageItem = list.get(holder.getLayoutPosition());

        Glide.with(activity).load("https://cn.bing.com/" + imageItem.getUrl()).into(holder.imageView);

        //holder.imageView.setImageBitmap(getImage("https://cn.bing.com/" + imageItem.getUrl()));

//        this.holder = holder;
//        getImage("https://cn.bing.com/" + imageItem.getUrl());

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

    private ImagesAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(ImagesAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private void getImage(String url) {
        OkHttpClient client1 = HttpsCertificateVerifyHelper.trustAllCertificate(new OkHttpClient.Builder()
                //.cache(cache)
                .retryOnConnectionFailure(false))
                .build();
        final Request request = new Request.Builder().get().url(url).build();
        Call call = client1.newCall(request);
        final Bitmap[] bitmap = new Bitmap[1];
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] bytes = response.body().bytes();
//                InputStream inputStream = response.body().byteStream();
//                //bitmap[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                bitmap[0] = BitmapFactory.decodeStream(inputStream);
                Message message = handler.obtainMessage();
                message.obj = bytes;
                message.what = SUCCESS;
                handler.sendMessage(message);
            }
        });
        //return bitmap[0];
    }
}

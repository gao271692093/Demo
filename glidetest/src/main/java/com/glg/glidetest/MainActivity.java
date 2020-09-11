package com.glg.glidetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ekwing.dataparser.json.CommonJsonBuilder;
import com.ekwing.http.common.HttpProxy;
import com.ekwing.http.common.interfaces.CallBack;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://bing.getlove.cn/latelyBingImageStory";
    private List<ImageItem> list = new ArrayList<>();
    private ImagesAdapter imagesAdapter = new ImagesAdapter(MainActivity.this, list);
    private RecyclerView recyclerView;
    private CallBack callBack = new CallBack() {
        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(String result) {
            list = CommonJsonBuilder.toObjectArray(result, ImageItem.class);
            imagesAdapter.setList(list);
            imagesAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCacheSuccess(String result) {

        }

        @Override
        public void onError(int code, Throwable throwable) {
            Toast.makeText(MainActivity.this, "请求失败" + code, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String gif = "http://p1.pstatp.com/large/166200019850062839d3";
        final ImageView imageView = findViewById(R.id.imageView);

        //加载到的图片不一定要显示到ImageView中，可以填充到Target当中，Target需要我们自定义
        //在onResourceReady()方法中，我们就可以获取到Glide加载出来的图片对象了，也就是方法参数中传过来的Drawable对象。有了这个对象之后你可以使用它进行任意的逻辑操作，这里只是简单地把它显示到了ImageView上。
//        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                imageView.setImageDrawable(resource);
//            }
//        };
//        Glide.with(this).load(gif).into(simpleTarget);

        //将into()替换成preload()以后，表示预加载该图片，当我们再进行加载该图片的时候就会直接从缓存中进行加载了
        Glide.with(this).load(gif).preload();

        //MultiTransformation<Bitmap> bitmapMultiTransformation = new MultiTransformation<>(new GrayscaleTransformation(),new BlurTransformation(25,3),new CropCircleWithBorderTransformation(), new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.TOP));
        //Glide.with(this).load("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg").apply(RequestOptions.bitmapTransform(bitmapMultiTransformation)).into(imageView);
        try {
            Glide.with(this)
                    .asGif()
                    .load(new URL(gif))
                    //.override(100,100)//重新设置加载以后的图片大小，单位为像素

                    //加载图片会存在加载失败的情况，如果我们需要对加载的情况进行处理时就使用listener()方法
                    //加载成功的时候会调用onResourceReady()方法，加载失败的时候会调用onLoadFailed()方法，通过传入的GlideException参数，我们就可以具体定位失败的原因了
                    //对于listener()方法返回的boolean值表示该事件有没有被处理，true表示已经处理了，不会再向下传递了，false反之。
                    //比如在listener()和Target对象都重写了onResourceReady()，如果listener()方法中传入的RequestListener对象的onResourceReady()方法返回了true，就不会再调用Target对象的onResourceReady()方法了。
//                    .listener(new RequestListener<GifDrawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
//                            if (resource instanceof GifDrawable) {
//                                //加载一次
//                                ((GifDrawable)resource).setLoopCount(1);
//                            }
//                            return false;
//                        }
//                    })
                    .into(imageView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(imagesAdapter);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        HttpProxy.getInstance().get(URL,null, null, false, callBack);
    }

    /**
     * submit()方法也可以替换into()方法，不过会比preload()方法的使用复杂很多，这个方法只会下载图片，而不会加载图片。
     * 当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作。
     * 该方法不传任何参数时下载的是原始尺寸的图片，传入宽和高时会下载指定尺寸的图片。
     * 当调用了submit()方法后会立即返回一个FutureTarget对象，然后Glide会在后台开始下载图片文件。
     * 接下来我们调用FutureTarget的get()方法就可以去获取下载好的图片文件了，如果此时图片还没有下载完，那么get()方法就会阻塞住，一直等到图片下载完成才会有值返回。
     */
    public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

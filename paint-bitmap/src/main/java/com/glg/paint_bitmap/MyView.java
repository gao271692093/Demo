package com.glg.paint_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
//        System.out.println(new File("/storage/self/primary/beautiful.jpg").exists());
//        System.out.println(Environment.getExternalStorageDirectory().toString());
//        System.out.println(Environment.getExternalStorageState());
        //Bitmap bitmap = BitmapFactory.decodeFile("/storage/self/primary/beautiful.jpg");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.i(TAG, "onDraw: ====> path" + path);

        File file = new File("/storage/self/primary/beautiful.jpg");
        Log.i(TAG, "onDraw: ====> file.exists()" + file.exists());

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        //bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);//想要设置bitmap的大小的时候使用，注意设置的大小只能比原来的小
        System.out.println(bitmap.getHeight());
        System.out.println(bitmap.getWidth());
        //bitmap.setHeight(1000);
        //bitmap.setWidth(500);/sdcard/beautiful.jpg
        canvas.drawBitmap(bitmap, 0, 0, paint);
        //Bitmap bitmap;
//        try ( InputStream is = new URL("/storage/self/primary/art01.jpg" ).openStream() ) {
//            bitmap = BitmapFactory.decodeStream( is );
//            canvas.drawBitmap(bitmap, 0, 0, paint);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (InputStream inputStream = new FileInputStream(new File("/storage/self/primary/art01.jpg"))) {
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            canvas.drawBitmap(bitmap, 0, 0, paint);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 200, 200, 200, 200);
        canvas.drawBitmap(bitmap1, 50, 650, paint);
    }
}

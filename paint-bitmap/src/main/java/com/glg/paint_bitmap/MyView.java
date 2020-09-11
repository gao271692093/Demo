package com.glg.paint_bitmap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;

public class MyView extends View {
    private static final String TAG = "MyView";
    private Context context;

    public MyView(Context context) {
        super(context);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        if(context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ((Activity)context).requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        }
        File file = new File("/storage/self/primary/beautiful.jpg");


        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        //bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);//想要设置bitmap的大小的时候使用，注意设置的大小只能比原来的小
        System.out.println(bitmap.getHeight());
        System.out.println(bitmap.getWidth());
        //bitmap.setHeight(1000);
        //bitmap.setWidth(500);/sdcard/beautiful.jpg

        //缩放
        canvas.save();
        Float fll = Float.valueOf(Float.valueOf(bitmap.getHeight()) / Float.valueOf(bitmap.getWidth()));
        Toast.makeText(getContext(), "" + fll, Toast.LENGTH_LONG).show();
        canvas.scale(fll, fll, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        //canvas.rotate(90, bitmap.getWidth()/2, bitmap.getHeight()/2);
        //canvas.skew(0, 1);//错切，y轴不变，x轴顺时针旋转45度
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        //canvas.drawBitmap(bitmap, 0, 0, paint);
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

        int saveCount = canvas.save();
        canvas.translate(-200, 750);
        canvas.clipRect(200, 200, 500, 500);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restoreToCount(saveCount);

        canvas.save();
        canvas.translate(100, 750);
        canvas.clipRect(200, 200, 500, 500);
        //Region.Op.DIFFERENCE代表在第一次裁剪的基础上将第二次裁剪擦除掉
        //Region.Op.INTERSECT是保留第一次裁剪和第二次裁剪重叠的部分
        //Region.Op.REVERSE_DIFFERENCE是以第二次剪裁的为基础，在此基础上擦除第一次剪裁的部分
        //Region.Op.UNION,合并,即保留第一次剪裁和第二次剪裁的并集
        //Region.Op.XOR,异或操作，做出第一次剪裁，并且做出第二次剪裁，在此基础上擦除掉重叠的部分
        canvas.clipRect(450, 450, 600, 600, Region.Op.DIFFERENCE);
        canvas.drawBitmap(bitmap,0, 0, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(45, 300, 300);
        canvas.drawBitmap(bitmap1, 600, 450, paint);
        canvas.restore();

        paint.setAntiAlias(true);
        paint.setColor(0xFF0000FF);
        paint.setStyle(Paint.Style.STROKE);
        Path paths = new Path();
        paths.addCircle(300, 300, 100, Path.Direction.CW);
        canvas.save();
        canvas.translate(400, 750);
        canvas.clipPath(paths);//canvas.clipOutPath(path)是绘制path以外的区域
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        Matrix matrix = new Matrix();
        canvas.setMatrix(matrix);//放弃掉canvas的变化，使用matrix的变换
        canvas.concat(matrix);//将matrix的变换和canvas的变化叠加
        canvas.restore();

        //camera旋转实现的旋转效果：沿X轴顺时针旋转、沿Y轴按右手方向、沿Z轴也是右手方向
        canvas.save();
        Camera camera = new Camera();
        camera.save();
        canvas.translate(bitmap.getWidth(), bitmap.getHeight() + 1100);
        camera.rotateX(30);
        camera.applyToCanvas(canvas);
        canvas.translate(-bitmap.getWidth(), -bitmap.getHeight()-1100);
        camera.restore();
        canvas.drawBitmap(bitmap, 0, 1100, paint);
        canvas.restore();
    }
}

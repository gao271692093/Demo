package com.glg.shadertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @package: com.glg.shadertest
 * @className: MyView
 * @author: gao
 * @date: 2020/7/30 14:13
 */
class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //线性渐变
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);//端点之外延续端点处的颜色
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
        shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);//镜像模式
        paint.setShader(shader);
        canvas.drawCircle(800, 300, 200, paint);
        shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);//重复模式
        paint.setShader(shader);
        canvas.drawCircle(300, 800, 200, paint);

        //辐射渐变
        //centerX centerY：辐射中心的坐标
        //radius：辐射半径
        //centerColor：辐射中心的颜色
        //edgeColor：辐射边缘的颜色
        //tileMode：辐射范围之外的着色模式。(与线性渐变相同)
        shader = new RadialGradient(800, 800, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(800, 800, 200, paint);

        //扫描渐变
        //cx cy ：扫描的中心
        //color0：扫描的起始颜色
        //color1：扫描的终止颜色
        shader = new SweepGradient(300, 1300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));
        paint.setShader(shader);
        canvas.drawCircle(300, 1300, 200, paint);

        //BitmapShader
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ico);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(800, 1300, 200, paint);
    }
}

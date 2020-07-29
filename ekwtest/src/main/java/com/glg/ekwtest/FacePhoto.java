package com.glg.ekwtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by gao on 2020/7/23 14:15.
 * Function:
 */
class FacePhoto extends View {

    private Bitmap bitmap;

    public FacePhoto(Context context) {
        super(context);
    }

    public FacePhoto(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ico2);
        }
        Paint paint = new Paint();
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(69,71, 69, paint);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

package com.glg.putonhat2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

public class HatView extends View {

    private float x;
    private float y;
    public HatView(Context context) {
        super(context);
        x = 65;
        y = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hat);
        canvas.drawBitmap(bitmap, x, y, paint);
        if(bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}

package com.glg.painttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(0xFFFF6600);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10, 10, 280, 280, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(0xFF000000);
        paint.setTextSize(25);
        canvas.drawText("HelloWorld!", 140, 150, paint);
    }
}

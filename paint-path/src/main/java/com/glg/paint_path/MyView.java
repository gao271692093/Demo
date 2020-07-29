package com.glg.paint_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF0000FF);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.addCircle(150, 150, 100, Path.Direction.CW);
        //canvas.drawPath(path, paint);

        paint.setTextSize(30);
        canvas.drawTextOnPath("活着就是为了改变世界", path, 90, -20, paint);//hOffset : 与路径起始点的水平偏移距离   vOffset : 与路径中心的垂直偏移量
    }
}

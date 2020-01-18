package com.glg.paint_robot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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
        paint.setColor(0xFFA4C739);

        //绘制机器人的头
        RectF rectF = new RectF(10, 10, 100, 100);//定义外轮廓矩形
        rectF.offset(90, 20);
        canvas.drawArc(rectF, -10, -160, false, paint);//绘制弧

        //绘制眼睛
        paint.setColor(0xFFFFFFFF);
        canvas.drawCircle(165, 53, 4, paint);
        canvas.drawCircle(125, 53, 4, paint);

        //绘制天线
        paint.setColor(0xFFA4C739);
        paint.setStrokeWidth(2);
        canvas.drawLine(110, 15, 125,35, paint);
        canvas.drawLine(180, 15, 165, 35, paint);

        //绘制身体
        canvas.drawRect(100, 75, 190, 150, paint);
        RectF rectF_body = new RectF(100, 140, 190, 160);
        canvas.drawRoundRect(rectF_body, 10, 10, paint);

        //绘制胳膊
        RectF rectF_arm = new RectF(75, 75, 95, 140);
        canvas.drawRoundRect(rectF_arm, 10, 10, paint);
        rectF_arm.offset(120,0);
        canvas.drawRoundRect(rectF_arm, 10, 10, paint);

        //绘制腿
        RectF rectF_leg = new RectF(115, 150, 135, 200);
        canvas.drawRoundRect(rectF_leg, 10, 10, paint);
        rectF_leg.offset(40,0);
        canvas.drawRoundRect(rectF_leg, 10, 10, paint);
    }
}

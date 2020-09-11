package com.glg.paint_expert;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @package: com.glg.paint_expert
 * @className: MyView
 * @author: gao
 * @date: 2020/7/31 14:17
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
        paint.setAntiAlias(true);
        paint.setColor(0xFF0000FF);
        paint.setStyle(Paint.Style.STROKE);//Paint.Style.FILL设置只绘制图形内容,Paint.Style.STROKE设置只绘制图形的边,Paint.Style.FILL_AND_STROKE设置都绘制
        Path path = new Path();
        path.moveTo(100, 200);
        path.lineTo(200,300);
        path.lineTo(300,100);
        path.lineTo(400,300);

        //PathEffect 在有些情况下不支持硬件加速，需要关闭硬件加速才能正常使用

        //PathEffect pathEffect = new CornerPathEffect(80);//把所有的拐角变成圆角，参数radius 是圆角的半径
        //PathEffect pathEffect = new DiscretePathEffect(20, 5);//把绘制改为使用定长的线段来拼接，并且在拼接的时候对路径进行随机偏离,segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量
        //PathEffect pathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);//使用虚线来绘制线条,第一个参数 intervals 是一个数组，它指定了虚线的格式,最少两个元素，实线虚线长度相间，第二个参数 phase 是虚线的偏移量
        Path path1 = new Path();
//        path1.moveTo(100,200);
//        path1.lineTo(120, 230);
//        path1.lineTo(130,190);
//        path1.close();

//        path1.addCircle(0, 0, 10, Path.Direction.CCW);
//        PathEffect pathEffect = new PathDashPathEffect(path1, 40, 0,
//                PathDashPathEffect.Style.TRANSLATE);//shape 参数是用来绘制的 Path,advance 是两个相邻的 shape 段之间的间隔,phase是虚线的偏移,最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式,TRANSLATE：位移,ROTATE：旋转,MORPH：变体

//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        PathEffect pathEffect = new SumPathEffect(dashEffect, discreteEffect);//SumPathEffect是对两种效果的组合

//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        PathEffect pathEffect = new ComposePathEffect(dashEffect, discreteEffect);//ComposePathEffect也是对两种效果的组合，不同的是Compose是在使用第一种效果得到的路径基础上使用第二种效果，传递的 两个参数分别是第一次和第二次应用的效果
//        paint.setPathEffect(pathEffect);

        //setShadowLayer() 和 setMaskFilter() ，它们和前面的效果类方法有点不一样：它们设置的是「附加效果」，也就是基于在绘制内容的额外效果。
        paint.setShadowLayer(10, 0, 0, Color.RED);//绘制在绘制层下方的附加效果，radius 是阴影的模糊范围； dx dy 是阴影的偏移量； shadowColor 是阴影的颜色。

        paint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL));//绘制在绘制层上方的附加效果，radius 参数是模糊的范围， style 是模糊的类型。一共有四种：NORMAL: 内外都模糊绘制 SOLID: 内部正常绘制，外部模糊INNER: 内部模糊，外部不绘制OUTER: 内部不绘制，外部模糊（什么鬼？）

        canvas.drawPath(path, paint);

        paint.setTextSize(30);
        canvas.drawTextOnPath("活着就是为了改变世界", path, 90, -20, paint);//hOffset : 与路径起始点的水平偏移距离   vOffset : 与路径中心的垂直偏移量
    }
}

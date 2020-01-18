package com.glg.sensortest2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PointerView extends View implements SensorEventListener {

    private SensorManager sensorManager;

    private Bitmap pointer = null;

    private float[] allValue;

    public PointerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), sensorManager.SENSOR_DELAY_GAME);
        pointer = BitmapFactory.decodeResource(super.getResources(), R.drawable.pointer11);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] value = event.values;
            allValue = value;
            super.postInvalidate();//刷新界面
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据x轴、y轴的 磁场强度绘制指针
        if(allValue != null) {
            float x = allValue[0];
            float y = allValue[1];
            canvas.save();
            canvas.restore();//重置绘制图像
            canvas.translate(super.getWidth()/2, super.getHeight()/2);//设置旋转的中心点为屏幕的中心点
            if(y == 0 && x > 0) {
                canvas.rotate(90);//旋转90度
            } else if(y == 0 && x < 0) {
                canvas.rotate(270);//旋转270度
            } else {
                if(y >= 0) {
                    canvas.rotate((float) Math.tanh(x/y)*90);
                } else {
                    canvas.rotate(180 + (float) Math.tanh(x/y)*90);
                }
            }
            canvas.drawBitmap(this.pointer, -this.pointer.getWidth()/2, -this.pointer.getHeight()/2, new Paint());//绘制指针
        }
    }
}

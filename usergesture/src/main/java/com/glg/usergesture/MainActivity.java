package com.glg.usergesture;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

//第二步，让MainActivity实现GestureOverlayView.OnGesturePerformedListener接口，并实现onGesturePerformed方法
public class MainActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gestureLibrary;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第三步，加载raw文件夹下的手势文件，如果加载失败就退出应用
        gestureLibrary = GestureLibraries.fromRawResource(MainActivity.this, R.raw.gestures);
        editText = findViewById(R.id.editText);
        if(!gestureLibrary.load()) {
            finish();
        }
        //第四步，获得GestureOverlayView组件，并为其设置属性值和事件监听器
        GestureOverlayView gestureOverlayView = findViewById(R.id.gesture);
        gestureOverlayView.setGestureColor(Color.BLACK);
        gestureOverlayView.setFadeOffset(1000);
        gestureOverlayView.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //第五步，获得最佳匹配gesture，并进行显示
        ArrayList<Prediction> gestures = gestureLibrary.recognize(gesture);
        int index = 0;
        double score = 0.0;
        for(int  i =0; i < gestures.size(); i += 1) {
            Prediction result = gestures.get(i);
            if(result.score > score) {
                index = i;
                score = result.score;
            }
        }
        if (null == editText.getText()) {
            editText.setText(gestures.get(index).name);
        } else {
            editText.setText(editText.getText().toString() + gestures.get(index).name);
            editText.setSelection(editText.getText().length());
        }

    }
}

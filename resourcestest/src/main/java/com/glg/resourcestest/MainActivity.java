package com.glg.resourcestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView textView = findViewById(R.id.textView);
        //错误写法
        //textView.setText(R.string.testString + ".....");
        //正确写法
        //textView.setText(getResources().getString(R.string.testString) + ".............");
        TextView textView = findViewById(R.id.textView2);
        textView.setBackgroundColor(getResources().getColor(R.color.title));
        textView.setTextColor(getResources().getColor(R.color.bg));

        TextView textView4 = findViewById(R.id.textView4);
        textView4.setTextSize(getResources().getDimension(R.dimen.title));

        ListView listView = findViewById(R.id.listView);
        String[] arrs = getResources().getStringArray(R.array.listitem);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrs);
        listView.setAdapter(adapter);
    }

    /**
     * dp转换为px
     *
     * @param context
     * @param value 单位dp
     * @return
     */
    public static int dp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    /**
     * sp转换为px
     *
     * @param context
     * @param value 单位sp
     * @return
     */
    public static int sp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2dp(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (value / v + 0.5f);
    }

    /**
     * px转换为sp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2sp(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / v + 0.5f);
    }
}

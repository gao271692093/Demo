package com.glg.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @package: com.glg.customviewtest
 * @className: MyLinearLayout
 * @author: gao
 * @date: 2020/8/19 15:13
 */
class MyLinearLayout extends LinearLayout {
    private int colorText;
    private String textLeft;
    private String textTitle;
    private String textRight;
    private TextView tvLeft;
    private TextView tvTitle;
    private TextView tvRight;

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        //从xml的属性中获取到字体颜色与string
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.MyTitle);
        colorText=ta.getColor(R.styleable.MyTitle_textColor, Color.BLACK);
        textLeft=ta.getString(R.styleable.MyTitle_leftText);
        textTitle=ta.getString(R.styleable.MyTitle_titleText);
        textRight=ta.getString(R.styleable.MyTitle_rightText);
        ta.recycle();

        //获取到控件
        //加载布局文件，与setContentView()效果一样
        LayoutInflater.from(context).inflate(R.layout.my_linearlayout, this);
        tvLeft=(TextView)findViewById(R.id.textView_left);
        tvTitle=(TextView)findViewById(R.id.textView_title);
        tvRight=(TextView)findViewById(R.id.textView_right);

        //将控件与设置的xml属性关联
        tvLeft.setTextColor(colorText);
        tvLeft.setText(textLeft);
        tvTitle.setTextColor(colorText);
        tvTitle.setText(textTitle);
        tvRight.setTextColor(colorText);
        tvRight.setText(textRight);

    }

}

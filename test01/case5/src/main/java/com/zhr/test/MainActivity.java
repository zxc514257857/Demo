package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * 案例五：计算器布局界面 练习android五大布局之表格布局
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private GridLayout mGridLayout;
    private String[] mChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView(){
        mGridLayout = (GridLayout) findViewById(R.id.grid_layout);
        mChars = new String[]{"7" ,"8" ,"9" ,"÷" ,
                "4" ,"5" ,"6" ,"×" ,
                "1" ,"2" ,"3" ,"-" ,
                "." ,"0" ,"=" ,"+" };
    }

    public void initData(){
        for(int i = 0 ; i < mChars.length ; i++){
            Button button = new Button(mContext);
            // 循环设置文字内容
            button.setText(mChars[i]);
            button.setTextSize(50);
            // 循环按钮摆放位置
            // 在TextView和Button后指定Button所在的行
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            // 指定Button所在的列
            GridLayout.Spec columnSoec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec , columnSoec);
            // 左上及中间的形状不变,右下布局以填满GridLayout为宗旨
            layoutParams.setGravity(Gravity.FILL);
            mGridLayout.addView(button , layoutParams);
        }
    }
}

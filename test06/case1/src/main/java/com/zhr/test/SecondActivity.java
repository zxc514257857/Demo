package com.zhr.test;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Context mContext = SecondActivity.this;
    private Activity mActivity = SecondActivity.this;
    private Button mBt1;
    private Button mBt2;
    private Button mBt3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 滑动效果
//            getWindow().setEnterTransition(new Slide().setDuration(2000));
//            getWindow().setExitTransition(new Slide().setDuration(2000));

            // 隐藏效果
//            getWindow().setEnterTransition(new Fade().setDuration(2000));
//            getWindow().setExitTransition(new Fade().setDuration(2000));

            // 爆炸效果
//            getWindow().setEnterTransition(new Explode().setDuration(2000));
//            getWindow().setExitTransition(new Explode().setDuration(2000));
        }

        mBt1 = findViewById(R.id.b1);
        mBt2 = findViewById(R.id.b2);
        mBt3 = findViewById(R.id.b3);
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 测试android 5.0以后系统自带的转场效果 共享元素效果
                 * 使用共享元素效果的按钮需要在xml文件中加上 transitionName属性
                 * @param view
                 */
                Intent intent = new Intent(mContext , MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(mActivity , mBt1 , "bt2").toBundle());
                }
            }
        });

        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(mActivity , mBt2 , "bt3").toBundle());
                }
            }
        });

        mBt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , MainActivity.class);
                startActivity(intent);
                // 渐隐的转场
                mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
    }
}

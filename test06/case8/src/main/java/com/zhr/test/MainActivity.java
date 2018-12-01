package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 案例八：qvod
 * 模拟视频播放 练习activity的生命周期
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
    }

    @Override
    protected void onStop() {
        System.out.println("onstop , 暂停视频的播放");
        super.onStop();
    }

    @Override
    protected void onStart() {
        System.out.println("onstart , 继续播放视频");
        super.onStart();
    }
}

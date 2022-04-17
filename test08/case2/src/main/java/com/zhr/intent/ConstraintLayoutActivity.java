package com.zhr.intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 案例二：
 * ConstraintLayout 基础布局特性和2.0布局特性
 * https://blog.csdn.net/weixin_46055193/article/details/106199825
 * https://blog.csdn.net/Android_boom/article/details/115740421
 *
 * Fragment的使用
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ConstraintLayout 2.0布局
//        setContentView(R.layout.activity_main);
        // ConstraintLayout 基础布局特性
        setContentView(R.layout.activity_main1);
    }
}

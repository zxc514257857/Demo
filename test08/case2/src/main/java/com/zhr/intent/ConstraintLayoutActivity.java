package com.zhr.intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 布局中使用了 constraintlayout 2.0布局特性
        setContentView(R.layout.activity_main);
    }
}

package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 案例七：android中的单例模式写法
 * 什么叫做单例模式：
 * 1，只能有一个实例
 * 2，只能自己创建自己的唯一实例
 * 3，必须向其他所有对象提供这一实例
 *
 * 懒汉式的四种写法中推荐第三种和第四种
 * 饿汉式推荐
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

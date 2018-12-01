package com.zhr.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 案例二：开启界面的两种方式
 * 显式意图和隐式意图
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 开启显式意图
     * @param view
     */
    public void jump1(View view){
        Intent intent = new Intent();
//        intent.setClassName( "com.zhr.test" , "com.zhr.test.Jiemian01");
        intent.setClass(this , Jiemian01.class);
        startActivity(intent);
    }

    /**
     * 开启隐式意图
     * @param view
     */
    public void jump2(View view){
        Intent intent = new Intent();
        intent.setAction("com.zhr.test.action");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }
}

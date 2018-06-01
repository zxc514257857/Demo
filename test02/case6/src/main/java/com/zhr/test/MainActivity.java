package com.zhr.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 案例六：使用自定义Application存储全局变量
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App mApp = App.getInstance();
        StudentBean mBean = new StudentBean();
        mBean.setAge(28);
        mBean.setName("张三");
        mApp.setBean(mBean);
        Button mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this , SecondActivity.class));
    }
}
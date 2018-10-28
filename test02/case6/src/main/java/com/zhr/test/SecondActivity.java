package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App app = App.getInstance();
        StudentBean bean = app.getBean();
        String name = bean.getName();
        int age = bean.getAge();
        Log.i(TAG, "StudentBean: name," + name + ";age," + age);
    }
}

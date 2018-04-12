package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * 案例四：数据库帮助类的创建
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 这据代码执行完后数据库还不会创建
        MyDataBaseOpenHelper helper = new MyDataBaseOpenHelper(mContext);
        // 这句代码执行完数据库才会被创建
        helper.getWritableDatabase();
    }
}

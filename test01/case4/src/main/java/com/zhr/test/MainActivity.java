package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 案例四：Logcat的使用
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 测试发现 v和d级别日志不打印,所以调试的时候还是使用i级别日志以上较好！！！
     */
    public void click01(View view){
        Log.v(TAG , "verbose级别日志！");
    }

    public void click02(View view){
        Log.d(TAG , "debug级别日志！");
    }

    public void click03(View view){
        Log.i(TAG , "info级别日志！");
    }

    public void click04(View view){
        Log.w(TAG , "warn级别日志！");
    }

    public void click05(View view){
        Log.e(TAG , "error级别日志！");
    }

    public void click06(View view){
        Log.wtf(TAG , "what the fuck级别日志！");
    }
}

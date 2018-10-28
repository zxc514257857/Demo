package com.zhr.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例二：更新UI的测试
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTv;
    private List<String> mList;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AppConstance.STR:
                    final String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage: " + str);
                    ThreadUtil.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(str);
                        }
                    });
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
    }

    private void initData() {
        mList = new ArrayList<>();
        for(int i = 1 ; i <= 100 ; i++){
            mList.add("测试数据" + i);
        }

        ThreadUtil.runOnThread(new Runnable() {
            @Override
            public void run() {
                for(String str : mList){
                    Message msg = Message.obtain();
                    msg.what = AppConstance.STR;
                    msg.obj = str;
                    mHandler.handleMessage(msg);
                    // 耗时性操作 子线程完成
                    SystemClock.sleep(1000);
                }
            }
        });
    }
}

package com.zhr.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 案例六：霓虹灯布局界面 练习android五大布局之帧布局
 */
public class MainActivity extends AppCompatActivity {

    private static final int COLOR_CHANGE = 1;
    private int currentColor = 0;
    private TextView[] mTextViews;
    private int[] mColors;
    private int[] mIds;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case COLOR_CHANGE:
                    // 更改颜色
                    for(int i = 0 ; i < mColors.length ; i++){
                        mTextViews[i].setBackgroundResource(mColors[ (i + currentColor) % mColors.length]);
                    }
                    currentColor++;
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

    public void initView(){
        mColors = new int[]{
                R.color.colorRed,
                R.color.colorOrange,
                R.color.colorYellow,
                R.color.colorGreen,
                R.color.colorCyan,
                R.color.colorBlue,
                R.color.colorPurple
        };

        mIds = new int[]{
                R.id.red,
                R.id.orange,
                R.id.yellow,
                R.id.green,
                R.id.cyan,
                R.id.blue,
                R.id.purple
        };

        mTextViews = new TextView[mColors.length];
        for (int i = 0; i < mColors.length ; i++){
            mTextViews[i] = (TextView) findViewById(mIds[i]);
        }
    }

    public void initData(){
        Timer timer = new Timer();
        // 三个参数含义:定时执行的任务/第一次多久后执行/每隔多久执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 发送消息 让UI线程更改UI
                mHandler.sendEmptyMessage(COLOR_CHANGE);
            }
        },0,100);
    }
}

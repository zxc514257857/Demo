package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 案例四：android 通过MediaPlayer 实现语音播报功能
 * 语音可以选择支付宝 微信 其他支付方式
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private Button mBtnZfb;
    private Button mBtnWx;
    private Button mBtnMormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnZfb = findViewById(R.id.btn_zfb);
        mBtnWx = findViewById(R.id.btn_wx);
        mBtnMormal = findViewById(R.id.btn_mormal);

        mBtnZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoicePlay.with(mContext).playZFB("500" , false);
            }
        });

        mBtnWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoicePlay.with(mContext).playWX("500" , false);
            }
        });

        mBtnMormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoicePlay.with(mContext).playNormal("500" , false);
            }
        });
    }
}

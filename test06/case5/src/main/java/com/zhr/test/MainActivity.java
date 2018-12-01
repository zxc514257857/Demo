package com.zhr.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 案例五：短信分享
 * 使用意图 进行短信分享
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt = findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    // 来自调用系统短信的系统上层源代码
    // <intent-filter>
    //  <action android:name="android.intent.action.VIEW" />
    //  <action android:name="android.intent.action.SENDTO" />
    //  <category android:name="android.intent.category.DEFAULT" />
    //  <category android:name="android.intent.category.BROWSABLE" />
    //  <data android:scheme="sms" />
    //  <data android:scheme="smsto" />
    // </intent-filter>

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SENDTO");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body" , "推荐你看看这个博客：https://blog.csdn.net/zxc514257857");
        startActivity(intent);
    }
}

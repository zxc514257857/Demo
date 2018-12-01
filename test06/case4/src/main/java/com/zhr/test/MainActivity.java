package com.zhr.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 案例四：版权所有
 * 使用意图 调用系统浏览器打开网页
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mTv.setText("https://blog.csdn.net/zxc514257857");
        mTv.setOnClickListener(this);
    }

    // 来自调用系统浏览器的系统上层源代码
    //    <intent-filter>
    //      <action android:name="android.intent.action.VIEW" />
    //      <category android:name="android.intent.category.DEFAULT" />
    //      <category android:name="android.intent.category.BROWSABLE" />
    //      <data android:scheme="http" />
    //      <data android:scheme="https" />
    //      <data android:scheme="about" />
    //      <data android:scheme="javascript" />
    //    <intent-filter>

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        // 显示数据给用户
        intent.setAction("android.intent.action.VIEW");
        // 启动activit默认调用
        intent.addCategory("android.intent.category.DEFAULT");
        // activity可以调用浏览器
        intent.addCategory("android.intent.category.BROWSABLE");
        // 在浏览器上显示此网址信息
        intent.setData(Uri.parse("https://blog.csdn.net/zxc514257857"));
//        // 在浏览器上显示MP4文件
////        intent.setData(Uri.parse("http://media.zhumei.net/public/uploads/20180723/7df6c47fd0b0e48caf1ce89e6d6e0a78.mp4"));
//        // 在浏览器上显示jpg图片
//        intent.setData(Uri.parse("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/5b9e12ff-b59c-4d5f-9e56-c9eeee379fc9/dcbx3lr-d660b87b-2021-482b-a36c-d5ac2ef0a0ec.jpg/v1/fill/w_740,h_1081,q_70,strp/on_my_way_home__by_pascalcampion_dcbx3lr-pre.jpg"));
        startActivity(intent);
    }
}

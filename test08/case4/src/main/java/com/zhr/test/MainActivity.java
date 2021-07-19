package com.zhr.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.NotificationUtils;

/**
 * 案例四：Notification的使用
 * Notification的实现步骤：
 * 获取NotificationManager对象
 * 获取Notification对象
 * 管理事件Intent
 * 发送通知
 *
 * 通知有几种形式：
 * 普通式
 * 悬挂式
 * 折叠式
 * 锁屏式
 *
 * 通知必备的参数  : smallIcon  contentTitle  contentText  其他都是选配
 *
 * 更换AppName时遇到的问题：多个module时，app的名称会被module的名称替换掉
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private Button mBtn2;
    private boolean mIsOpen1;
    private boolean mIsOpen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mIsOpen1 = false;
        mIsOpen2 = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                if(!mIsOpen1){
                    // 发送的通知1： icon 标题 详细文本
                    NotificationUtils.create(R.drawable.ic_android_black_24dp , "我是标题" , "我是详细文本");
                    // 发送的通知2： 相对于第一种 可以设置触发的intent事件
                    NotificationUtils.create(this , 1 , new Intent() , R.drawable.ic_android_black_24dp , "我是标题" , "我是详细文本");
                    mIsOpen1 = true;
                    mBtn1.setText("工具类实现的通知(开)");
                }else {
                    // 开启的id是0，所以关闭0
                    // 关闭通知1
                    NotificationUtils.cancel(0);
                    // 关闭通知2
                    NotificationUtils.cancel(1);
                    mIsOpen1 = false;
                    mBtn1.setText("工具类实现的通知(关)");
                }
                break;

            case R.id.btn2:
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                if(!mIsOpen2){
                    // smallIcon 是状态栏图标  只能使用纯alpha图层图片进行设置，否则会变成白色方块
                    // 使用android自带的svg图片，File --> New --> Vector Asset
                    builder.setSmallIcon(R.drawable.ic_flight_black_24dp)
                        // largeIcon 是通知栏图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources() , R.mipmap.ic_launcher))
                        // 设置标题
                        .setContentTitle("我是标题")
                        // 设置详细文本
                        .setContentText("我是详细文本")
                        // 设置次要文本
                        .setSubText("我是次要文本")
                        // 设置进度条 总长度 / 现在长度 / 是否不确定进度
                        // 进度条走完触发一个Intent
//                        .setProgress(100 , 50 , false)
                        // 设置通知优先级
                        // PRIORITY_MIN          不在状态栏显示smallIcon，在通知栏最底部显示，在锁屏栏最底部显示
                        // PRIORITY_LOW          在状态栏显示最右侧，在通知栏相对底部显示，在锁屏栏相对底部显示
                        // PRIORITY_DEFAULT      在状态栏显示，通知栏显示在顶部，锁屏栏显示在顶部
                        // PRIORITY_HIGH         相对于default，在状态栏显示靠左，在通知栏显示靠近顶部，在锁屏栏显示靠近顶部
                        // PRIORITY_MAX          在状态栏显示在最左侧，通知栏显示在最顶层，锁屏栏显示在最顶层
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // 设置通知点击消失 设置为true并且设置了setContentIntent() 才能实现点击取消功能
                        .setAutoCancel(true)
                        .setWhen(System.currentTimeMillis())
                        // 设置显示当前时间
                        .setShowWhen(true)
                        // 显示数字
                        .setNumber(50)
                        // 通知首次出现在通知栏，带上升动画效果的，可设置文字，图标
                        .setTicker("22222222222")
                        // 设置他为一个正在进行的通知。如一个文件下载,网络连接
                        // 设置为false 可以侧滑删除
//                        .setOngoing(true)
                        .setContentIntent(PendingIntent.getActivity(this , 2 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT))
                        .build();
                    manager.notify(2 , builder.build());
                    mIsOpen2 = true;
                    mBtn2.setText("自定义实现的通知(开)");
                }else {
                    manager.cancel(2);
                    mIsOpen2 = false;
                    mBtn2.setText("自定义实现的通知(关)");
                }
                break;

            default:
                break;
        }
    }
}

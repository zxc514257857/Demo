package com.zhr.test;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 案例三：PendingIntent的使用
 * 1、pendingIntent 是Intent的封装
 * Intent是立刻执行的行为，pendingIntent不是立刻执行的行为，是满足某些条件才执行的行为
 * pendingIntent 的使用场景是：闹钟、通知、桌面部件
 * 应用的四大组件设置了exported=false，其他应用使用Intent就无法调用本应用的四大组件了，但是使用pendingIntent可以调用
 *
 * 2、android:exported 是四大组件中都会有的一个属性
 * 表示 是否暴露给其他应用程序调用本四大组件
 * 程序A如果不想让 其他应用调用本程序的四大组件，只要在AndroidManifest文件中添加exported属性，便能使其他的程序不能调用自己
 * <activity android:name="" android:exported="false"/>
 * <service android:name="" android:exported="false"/>
 * <receiver android:name="" android:exported="false"/>
 * <provider android:name="" android:exported="false"/>
 * 需要注意的是，如果两个应用程序的sharedUserId设置成一样的话，exported设置成false就没用了，两个程序的组件和资源还是可以相互调用和访问
 *
 * 3、android:sharedUserId 在AndroidManifest 根节点下添加的属性
 * 就是对应一个分配的linux用户ID ，主要是让多个安装的应用共享资源
 * 做法就是设置为相同的sharedUserId
 * android:sharedUserId="android.uid.system" 系统级进程
 * android:sharedUserId="包名或其他自定义名称" 普通共享app
 * https://blog.csdn.net/jiangwei0910410003/article/details/51316688
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * PendingIntent的使用步骤：
         * 传递四个参数：上下文 ，请求码 ，请求意图 ，标志位
         * 里面请求码和标志位是比较重要的
         * requestCode 一般和调用这个PendingIntent的对象所传入的id或requestCode保持一致，比如说Notification中
         * notificationManager.notify(id , builder.build());  否则无法显示多条不同的通知
         * intent 就是需要触发调用的意图
         * flags 可以设置四种属性：
         * PendingIntent.FLAG_CANCEL_CURRENT   若存在相同PendingIntent对象，先取消再重新创建
         * PendingIntent.FLAG_NO_CREATE        使用较少，PendingIntent不会主动创建。如果PendingIntent当前不存在，调用会返回null
         * PendingIntent.FLAG_ONE_SHOT         PendingIntent创建后只能使用一次，然后就会自动取消掉
         * PendingIntent.FLAG_UPDATE_CURRENT   使用较多，若存在相同PendingIntent对象，会更新此对象，包括Intent中的Extras会数据
         */
        // 通过getActivity() 创建一个pandingIntent 当意图触发时，效果相当于mContext.startActivity()
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this , 0 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT);
        // 通过getService() 创建一个pandingIntent 当意图触发时，效果相当于mContext.startService()
        PendingIntent pendingIntent2 = PendingIntent.getService(this , 0 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT);
        // 通过getBroadcast() 创建一个pandingIntent 当意图触发时，效果相当于mContext.sendBroadcast()
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(this , 0 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT);
        // 通过getForegroundService() 创建一个pandingIntent 当意图触发时，效果相当于mContext.sendBroadcast()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            PendingIntent pendingIntent4 = PendingIntent.getForegroundService(this , 0 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT);
        }
        // 通过getActivities() 创建一个pandingIntent 当意图触发时，效果相当于mContext.startActivity()
        PendingIntent pendingIntent5 = PendingIntent.getActivities(this , 0 , new Intent[]{} , PendingIntent.FLAG_UPDATE_CURRENT);
        // pendingIntent 一般就是其他应用组件需要调用它时要传入的内容
        // Intent和PengingIntent的区别，Intent是需要立即执行的意图，PendingIntent是一个需要被触发的Intent(是对Intent的一个封装)
        // 而这里需要去判断是getActivity还是getService是根据实际是需要打开activity、发送服务广播等来判断
    }
}

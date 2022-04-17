package com.zhr.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Android 多语言适配以及 屏幕朝向问题适配
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btn3;

    /**
     * android 重走生命周期方法：
     * 横竖屏切换的时候会重走生命周期方法， xml文件中配置onConfigurationChanged = "orientation | ScreenSize \ keyBoardHidden"
     * 切换系统语言的时候会重走生命周期方法
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        tv2 = findViewById(R.id.tv2);
//        btn = findViewById(R.id.btn);
//        // 设置 多语言的方法： 在strings.xml 中点击Open editor , 再点击左上角的地球 找到Chinese(zh)
//        // 就会创建一个values 放置中文的文件夹
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv2.setText(R.string.content);
//            }
//        });

        /**
         * 屏幕的朝向问题：现在很的app都把屏幕的朝向写死了
         * 如果用绝对像素值的方式写布局，竖屏转横屏的时候布局会有问题
         * 建议使用百分比布局方式  即ConstraintLayout 中的bias属性
         */
        setContentView(R.layout.activity_second);
        btn3 = findViewById(R.id.btn3);
        if(null != btn3){
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this , "btn3" , Toast.LENGTH_LONG).show();
                }
            });
        }

        /**
         * 水平布局和垂直布局不相同的布局方式实现：（即同一个Activity设置（横竖屏）两套不同的布局）
         * 找到xml布局 在预览的左上角 点击屏幕旋转的按钮 找到 Create LandScape Variation 点击就是新建横屏布局
         * 新老布局可以兼容进行开发
         * android 布局默认是纵向布局 ，需要横向布局的话不需要判断，系统已经给你判断好了，直接创建一个横向布局就行了
         */
    }

    /**
     * 屏幕翻转问题解决：
     * 要么让屏幕不要翻转
     * 要么重写onConfigurationChanged ,屏幕翻转不重走生命周期方法
     * 要么在屏幕翻转时 屏幕上的临时数据会丢失 需要将数据缓存起来
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key" , "value");
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String key = (String) savedInstanceState.get("key");
        Log.i(TAG , "onRestoreInstanceState: " + key);
    }
}

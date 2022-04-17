package com.zhr.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel的入门案例。没有使用DataBinding和LiveData 。 通过ViewModel来缓存界面上的数据
 *
 * ViewModel 属于Android Jetpack 中的一个类
 * Jetpack 又被称为 Androidx库
 * Android Jetpack 分为四个部分：Foundation 、Architecture 、 Behavior 和UI四个部分
 * 基础 、 架构 、 行为 、 界面
 * 目前讲的：VieModel 、 DataBinding 、 LiveData都是这个  （Architecture）架构里面的
 *
 * Androidx和第三方库的依赖冲突问题解决：implementation 'androidx'｛exclude 'support'｝
 *
 * 横竖屏切换时创建的布局，最好控件都存在，位置可以变化。如果控件不存在，需要进行控件的非空判断
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewModel();
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
    }

    private void initViewModel() {
        /**
         * 使用ViewModel 可以对数据进行方便的管理 不管是横竖屏切换还是系统语言切换 数据都不会丢失
         * ViewModel 可以在当前Activity的Fragment中实现数据共享
         */
        // 这里的Owner指定的是ViewModel的存储范围 this指的是这个Activity范围内
        // 在Fragment中使用的是getActivity() , 不能用this , 保证不同的Fragment之间进行数据共享
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        mTv.setText(String.valueOf(myViewModel.getNum()));
        if(null != mBtn1){
            mBtn1.setOnClickListener(view -> {
                int num = myViewModel.getNum();
                num += 1;
                myViewModel.setNum(num);
                mTv.setText(String.valueOf(myViewModel.getNum()));
            });
        }
        if(null != mBtn2){
            mBtn2.setOnClickListener(view -> {
                int num = myViewModel.getNum();
                num += 2;
                myViewModel.setNum(num);
                mTv.setText(String.valueOf(myViewModel.getNum()));
            });
        }
    }
}

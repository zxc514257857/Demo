package com.zhr.test;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 案例一：多界面应用
 * Intent跳转的使用
 * 在theme文件中添加 windowContentTransitions属性使用activity转场动画
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private Activity mActivity = MainActivity.this;
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn2 = findViewById(R.id.btn2);
    }

    /**
     * 测试android 5.0以后系统自带的转场效果 滑动效果 隐藏效果 爆炸效果等
     * @param view
     */
    public void jump1(View view){
        Intent intent = new Intent(this , SecondActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    /**
     * 测试android 5.0以后系统自带的转场效果 共享元素效果
     * 使用共享元素效果的按钮需要在xml文件中加上 transitionName属性
     * @param view
     */
    public void jump2(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 要传的三个参数 mActivity  本按钮id  要跳转的按钮transitionName名称
            startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(this , mBtn2 , "b1").toBundle());
        }
    }

    /**
     * 测试android 5.0之前使用overridePendingTransition 做的转场效果
     * 使用共享元素效果的按钮需要在xml文件中加上 transitionName属性
     * @param view
     */
    public void jump3(View view){
        Intent intent = new Intent(mContext, SecondActivity.class);
        startActivity(intent);
        // 渐隐的转场 alpha 进场的activity效果 出场的activity效果
//        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        // 从上往下的转场 tranlate
//        mActivity.overridePendingTransition(R.anim.up_to_down, R.anim.self_to_down);
        // 从下往上的转场 tranlate
//        mActivity.overridePendingTransition(R.anim.down_to_up, R.anim.self_to_up);
        // 从右往左的转场 tranlate
//        mActivity.overridePendingTransition(R.anim.right_to_left, R.anim.self_to_left);
        // 从左往右的转场 tranlate
//        mActivity.overridePendingTransition(R.anim.left_to_right, R.anim.self_to_right);
        // 放大缩小的转场 scale
//        mActivity.overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
        // 旋转的转场 roate
//        mActivity.overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);
        // 多种动画混合的转场效果
        mActivity.overridePendingTransition(R.anim.set_in, R.anim.set_out);
        finish();
    }
}

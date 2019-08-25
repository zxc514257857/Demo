package com.zhr.test;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 案例六：View弹跳动画和FloatingActionButton悬浮按钮学习
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox mCbDelay;
    private RelativeLayout mRlBg;
    private LinearLayout mLl02;
    private LinearLayout mLl03;
    private LinearLayout mLl04;
    private LinearLayout mLl05;
    private FloatingActionButton mFab01;
    private FloatingActionButton mFab02;
    private FloatingActionButton mFab03;
    private FloatingActionButton mFab04;
    private FloatingActionButton mFab05;
    private AnimatorSet mAs02;
    private AnimatorSet mAs03;
    private AnimatorSet mAs04;
    private AnimatorSet mAs05;
    // 判断是否点击过 防止重复点击
    private boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCbDelay = findViewById(R.id.cbDelay);
        mRlBg = findViewById(R.id.rlBg);
        mLl02 = findViewById(R.id.ll02);
        mLl03 = findViewById(R.id.ll03);
        mLl04 = findViewById(R.id.ll04);
        mLl05 = findViewById(R.id.ll05);

        mFab01 = findViewById(R.id.fab01);
        mFab02 = findViewById(R.id.fab02);
        mFab03 = findViewById(R.id.fab03);
        mFab04 = findViewById(R.id.fab04);
        mFab05 = findViewById(R.id.fab05);

        mAs02 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim);
        mAs03 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim);
        mAs04 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim);
        mAs05 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim);

        mFab01.setOnClickListener(this);
        mFab02.setOnClickListener(this);
        mFab03.setOnClickListener(this);
        mFab04.setOnClickListener(this);
        mFab05.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab01:
                Toast.makeText(this , "fab01 is clicked !" , Toast.LENGTH_SHORT).show();
                // 点击成功
                isClick = !isClick;
                if(isClick){
                    openFabMenu();
                }else {
                    offFabMenu();
                }
                break;

            case R.id.fab02:
                Toast.makeText(this , "fab02 is clicked !" , Toast.LENGTH_SHORT).show();
                offFabMenu();
                break;

            case R.id.fab03:
                Toast.makeText(this , "fab03 is clicked !" , Toast.LENGTH_SHORT).show();
                offFabMenu();
                break;

            case R.id.fab04:
                Toast.makeText(this , "fab04 is clicked !" , Toast.LENGTH_SHORT).show();
                offFabMenu();
                break;

            case R.id.fab05:
                Toast.makeText(this , "fab05 is clicked !" , Toast.LENGTH_SHORT).show();
                offFabMenu();
                break;

            default:
                break;
        }
    }

    /**
     * 打开fab菜单
     */
    private void openFabMenu() {
        boolean checked = mCbDelay.isChecked();
        mRlBg.setVisibility(View.VISIBLE);
        // 更换背景图片
        mFab01.setImageResource(R.mipmap.icon6);
        // 更换背景颜色
        mFab01.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.holo_blue_dark)));

        mAs02.setTarget(mLl02);
        mAs02.setStartDelay(checked ? 100 : 0);
        mAs02.start();
        mAs03.setTarget(mLl03);
        mAs03.setStartDelay(checked ? 200 : 0);
        mAs03.start();
        mAs04.setTarget(mLl04);
        mAs04.setStartDelay(checked ? 300 : 0);
        mAs04.start();
        mAs05.setTarget(mLl05);
        mAs05.setStartDelay(checked ? 400 : 0);
        mAs05.start();
    }

    /**
     * 关闭fab菜单
     */
    private void offFabMenu() {
        mRlBg.setVisibility(View.GONE);
        mFab01.setImageResource(R.mipmap.icon1);
        mFab01.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.holo_purple)));
        // 重置点击
        isClick = false;
    }
}

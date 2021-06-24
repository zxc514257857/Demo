package com.zhr.intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        mVp = findViewById(R.id.vp);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                replaceFragment();
                break;

            case R.id.btn2:
                // ViewPager + Fragment写法
                LeftFragment leftFragment = new LeftFragment();
                RightFragment rightFragment = new RightFragment();
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(leftFragment);
                fragments.add(rightFragment);
                TestPagerAdapter pagerAdapter = new TestPagerAdapter(getSupportFragmentManager(), fragments);
                mVp.setAdapter(pagerAdapter);
                break;

            default:
                break;
        }
    }

    private void replaceFragment() {
        // 获取管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 得到事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LeftFragment leftFragment = new LeftFragment();
        fragmentTransaction.replace(R.id.frameLayout, leftFragment, "left");
        fragmentTransaction.commit();
    }
}
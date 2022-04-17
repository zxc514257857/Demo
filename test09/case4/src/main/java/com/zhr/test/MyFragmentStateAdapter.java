package com.zhr.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragments;

    /**
     * lifecycle
     * 遵循顶层设计，便捷的完成生命周期感知
     */
    public MyFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle , List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.mFragments = fragments;
    }

    /**
     * 返回每个Item中的Fragment
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    /**
     * 获取ViewPager页面的Item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
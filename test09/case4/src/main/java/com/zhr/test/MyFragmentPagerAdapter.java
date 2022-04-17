package com.zhr.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.mFragments = fragments;
    }

    /**
     * 返回当前选中的fragment视图
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 返回fragment集合的个数
     * @return
     */
    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}

package com.zhr.intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TestPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public TestPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return null == mFragments ? 0 : mFragments.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
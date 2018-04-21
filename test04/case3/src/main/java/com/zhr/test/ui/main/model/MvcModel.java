package com.zhr.test.ui.main.model;

import android.app.ProgressDialog;

import com.zhr.test.bean.StoriesBean;
import com.zhr.test.ui.main.view.MvcView;

import java.util.List;

public interface MvcModel {

    void getData();

    int getRandomInt(List<StoriesBean> mStories);

    String[] getStringArr(List<StoriesBean> mStories);

    boolean[] getBooleanArr(List<StoriesBean> mStories);

    void simulateSleep1(long ms , MvcView mMv);

    void simulateSleep2(long ms , MvcView mMv , ProgressDialog mPd2);
}

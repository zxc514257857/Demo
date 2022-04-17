package com.zhr.test;

import androidx.lifecycle.ViewModel;

/**
 * 用户界面上的UI Data 数据放在这里进行管理
 * LiveData 通过观察者自动刷新界面上的数据
 */
public class MyViewModel extends ViewModel {

    private int num = 1;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
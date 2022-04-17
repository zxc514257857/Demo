package com.zhr.test;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private int mNum = 0;

    public int getNum(){
        return mNum;
    }

    public void setNum(int num){
        this.mNum = num;
    }

    public int add(int i){
        return getNum() + i;
    }
}
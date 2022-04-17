package com.zhr.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private static final String TAG = "MyViewModel";
    private SavedStateHandle mStateHandle1;
    private SavedStateHandle mStateHandle2;
    private int mBackNum1;
    private int mBackNum2;

    public MyViewModel(SavedStateHandle handle){
        this.mStateHandle1 = handle;
        this.mStateHandle2 = handle;
    }

    public MutableLiveData<Integer> getNum1(){
        if(!mStateHandle1.contains("handle1_key")){
            mStateHandle1.set("handle1_key" , 0);
        }
        return mStateHandle1.getLiveData("handle1_key");
    }

    public MutableLiveData<Integer> getNum2(){
        if(!mStateHandle2.contains("handle2_key")){
            mStateHandle2.set("handle2_key" ,0);
        }
        return mStateHandle2.getLiveData("handle2_key");
    }

    public void add1(int i){
        mBackNum1 = (int)mStateHandle1.getLiveData("handle1_key").getValue();
        mStateHandle1.set("handle1_key" , mBackNum1 + i);
    }

    public void add2(int i){
        mBackNum2 = (int)mStateHandle2.getLiveData("handle2_key").getValue();
        mStateHandle2.set("handle2_key" , (int)mStateHandle2.getLiveData("handle2_key").getValue() + i);
    }

    public void back(){
        mStateHandle1.set("handle1_key" , mBackNum1);
        mStateHandle2.set("handle2_key" , mBackNum2);
    }

    public void clear(){
        mBackNum1 = (int)mStateHandle1.getLiveData("handle1_key").getValue();
        mBackNum2 = (int)mStateHandle2.getLiveData("handle2_key").getValue();
        mStateHandle1.set("handle1_key" , 0);
        mStateHandle2.set("handle2_key" , 0);
    }
}

package com.zhr.test;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {

    private SavedStateHandle mHandle1;
    private SavedStateHandle mHandle2;
    private Application mApplication;
    private int back1;
    private int back2;

    public MyViewModel(@NonNull Application application , SavedStateHandle handle) {
        super(application);
        this.mApplication = application;
        this.mHandle1 = handle;
        this.mHandle2 = handle;
    }

    public MutableLiveData<Integer> getNum1(){
        if(!mHandle1.contains("handle_key1")){
            mHandle1.set("handle_key1" , 0);
        }
        return mHandle1.getLiveData("handle_key1");
    }

    public MutableLiveData<Integer> getNum2(){
        if(!mHandle2.contains("handle_key2")){
            mHandle2.set("handle_key2" , 0);
        }
        return mHandle2.getLiveData("handle_key2");
    }

    public void add1(int i){
        back1 = getNum1().getValue();
        getNum1().setValue(getNum1().getValue() + i);
    }

    public void add2(int i){
        back2 = getNum2().getValue();
        getNum2().setValue(getNum2().getValue() + i);
    }

    public void save(){
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("sp_key1" , getNum1().getValue());
        edit.putInt("sp_key2" , getNum2().getValue());
        edit.apply();
    }

    public void load(){
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name", Context.MODE_PRIVATE);
        getNum1().setValue(sp.getInt("sp_key1" , 0));
        getNum2().setValue(sp.getInt("sp_key2" , 0));
    }

    public void back(){
        getNum1().setValue(back1);
        getNum2().setValue(back2);
    }

    public void clear(){
        back1 = getNum1().getValue();
        getNum1().setValue(0);
        back2 = getNum2().getValue();
        getNum2().setValue(0);
    }
}
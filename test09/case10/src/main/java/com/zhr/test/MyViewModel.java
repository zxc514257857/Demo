package com.zhr.test;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    Application mApplication;
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

    public void save(int i){
        // Sp的使用 SharedPreferences ::: sharedPreferences  注意 后面有一个s
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name" , Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("sp_key" , i);
        // apply 是异步提交  commit() 是同步提交 推荐使用apply()
        edit.apply();
    }

    public int load(){
        // 在SP中读， 在Editor中写， 在getSharedPreferences中获取数据
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name" , Context.MODE_PRIVATE);
        // 在 data - data - 包名 - shared prefs中可以找到 sp_name.xml文件 可以找到对应的键是"sp_key"
        return sp.getInt("sp_key", 0);
    }
}
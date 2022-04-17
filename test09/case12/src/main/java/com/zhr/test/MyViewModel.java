package com.zhr.test;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends AndroidViewModel {

    private static final String TAG = "MyViewModel";
    private MutableLiveData<Integer> mNum;
    private Application mApplication;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;
    }

    public MutableLiveData<Integer> getNum() {
        if (null == mNum) {
            mNum = new MutableLiveData<>();
            mNum.setValue(0);
        }
        return mNum;
    }

    public void add(int i) {
        Integer value = mNum.getValue();
        if (null != value) {
            mNum.setValue(value + i);
        }
    }

    public void save() {
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        Integer value = mNum.getValue();
        Log.i(TAG, "save: " + value);
        edit.putInt("sp_key", value);
        edit.apply();
    }

    public void load() {
        SharedPreferences sp = mApplication.getSharedPreferences("sp_name", Context.MODE_PRIVATE);
        int spValue = sp.getInt("sp_key", 0);
        Log.i(TAG, "load: " + spValue);
        getNum().setValue(spValue);
    }
}
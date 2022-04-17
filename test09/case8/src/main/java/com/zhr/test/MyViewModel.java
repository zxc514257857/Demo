package com.zhr.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> num;

    public MutableLiveData<Integer> getNum() {
        if(null == num){
            num = new MutableLiveData<>();
            num.setValue(0);
        }
        return num;
    }

    public void addData(){
        num.setValue(num.getValue() + 1);
    }
}
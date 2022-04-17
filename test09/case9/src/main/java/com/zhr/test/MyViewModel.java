package com.zhr.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> numA;
    private MutableLiveData<Integer> numB;
    private int backNumA;
    private int backNumB;

    public MutableLiveData<Integer> getNumA() {
        if(null == numA){
            numA = new MutableLiveData<>();
            numA.setValue(0);
        }
        return numA;
    }

    public MutableLiveData<Integer> getNumB() {
        if(null == numB){
            numB = new MutableLiveData<>();
            numB.setValue(0);
        }
        return numB;
    }

    public void addNumA(int n){
        backNumA = numA.getValue();
        backNumB = numB.getValue();
        numA.setValue(numA.getValue() + n);
    }

    public void addNumB(int n){
        backNumA = numA.getValue();
        backNumB = numB.getValue();
        numB.setValue(numB.getValue() + n);
    }

    public void clear(){
        backNumA = numA.getValue();
        backNumB = numB.getValue();
        numA.setValue(0);
        numB.setValue(0);
    }

    public void back(){
        numA.setValue(backNumA);
        numB.setValue(backNumB);
    }
}
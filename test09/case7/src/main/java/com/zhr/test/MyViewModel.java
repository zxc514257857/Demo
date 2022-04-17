package com.zhr.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel 里面写的主要是视图的数据
 */
public class MyViewModel extends ViewModel {

    // 可变的动态数据，用于observe进行观察，这里的数据类型就是观察需要返回的数据类型
    private MutableLiveData<Integer> num;

    // 在构造方法中进行初始化赋值
//    public ViewModelWithLiveData() {
//        likedNum = new MutableLiveData<>();
//        likedNum.setValue(0);
//    }

    public MutableLiveData<Integer> getNum() {
        if(null == num){
            num = new MutableLiveData<>();
            num.setValue(0);
        }
        return num;
    }

    public void addNum(int n){
        num.setValue(num.getValue() + n);
    }
}
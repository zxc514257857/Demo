package com.zhr.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.zhr.test.databinding.ActivityMain9Binding;

/**
 * ViewModel 、 LiveData 、 DataBinding
 * ScreenOrientaion 、 Localization 、 Vector Drawable
 * 主要讲一下：如何使用assert
 * 分为Image assert 和 Vector assert 在File - New中找到这两个选项
 * Image assert是制作Logo用的，有Logo那个外框，是png格式的
 * Vector assert做任何图片都可以使用，是xml格式的
 */
public class MainActivity extends AppCompatActivity {

    // android 权限访问修饰符
    // public private protected 和空（friendly）
    // public 是指在项目中任何地方都可以正常访问
    // private 是指在本类中可以进行访问
    // 空 是指在本包中可以进行访问
    // protected 是指在本包中以及其子类中可以进行访问

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在Fragment中使用
//        ActivityMainBinding mainBinding = DataBindingUtil.inflate(inflater , R.layout.activity_main8 , null , false);
        // 在Adapter中使用
//        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.activity_main14, viewGroup, false);
        ActivityMain9Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main9);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.setViewModel(myViewModel);
        binding.setLifecycleOwner(this);
    }
}

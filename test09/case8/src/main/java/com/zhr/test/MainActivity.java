package com.zhr.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.zhr.test.databinding.ActivityMain8Binding;

/**
 * ViewModel + LiveData + dataBinding的简单使用
 *
 * JetPack 架构中的 ViewModel 、 DataBinding 、 LiveData
 * DataBinding View和Controller解绑 更加模块化 解决程序难以维护的问题
 * 使用ViewModel 存放界面的数据
 *
 * 使用DataBinding 的步骤：
 * 在module的build.gradle 中android节点下添加：
 * dataBinding{
 *      enabled true
 * }
 * 在布局文件中找到根布局， alt + enter 选择 Convert to data binding layout 然后就转换完成了
 * 不需要 setContentView(); 使用DataBindingUtil.setContentView(); 返回值为 ActivityxxxBinding binding
 * 在binding中获取控件id
 * 在<data></>标签中创建<variable></variable> 变量名称和变量类型（就是ViewModel的全类名）标签
 * 在text=@｛viewModel.xxx｝   在调用方法时 @{() -> viewModel.xxx}   数据回绑到xml布局文件中
 * binding 中设置 两个值 setxxx(变量名)  setLifecycleOwner(this)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain8Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main8);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // 这里set的就是 xml文件里面的变量名称
        binding.setViewModel(myViewModel);
        // 设置LiveData的数据监听
        binding.setLifecycleOwner(this);
    }
}

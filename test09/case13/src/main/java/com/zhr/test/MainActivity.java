package com.zhr.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import com.zhr.test.databinding.ActivityMain13Binding;

/**
 * ViewModel + SavedStateHandle + DataBinding  解决应用在后台内存不足时被杀死无法保存缓存数据的情况（比MutableLiveData更智能化,
 * SavedStateHandle 是MutableLiveData的封装）
 * ViewModel 中的构造方法传递的是SavedStateHandle对象
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain13Binding binding = DataBindingUtil.setContentView(this , R.layout.activity_main13);
        // 在AndroidViewModel中使用SavedStateHandle代替MutableLiveData ，用于应用在后台内存不足时被杀死无法保存缓存数据的情况
        MyViewModel myViewModel = new ViewModelProvider(this , new SavedStateViewModelFactory(getApplication() , this)).get(MyViewModel.class);
        binding.setViewModel(myViewModel);
        binding.setLifecycleOwner(this);
        myViewModel.getNum1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i(TAG, "onChanged1: " + integer);
            }
        });
        myViewModel.getNum2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i(TAG, "onChanged2: " + integer);
            }
        });
    }
}

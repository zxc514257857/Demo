package com.zhr.test;

import android.os.Bundle;

import com.zhr.test.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel + DataBinding + SharedPrefs（缓存数据） 没有使用LiveData
 * 数据的永久缓存
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyViewModel mMyViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        mMyViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // 在ViewModel中使用Context ，要么这样，要么使用AndroidViewModel
        mMyViewModel.mApplication = getApplication();
        mBinding.setMainActivity(this);
        mBinding.setLifecycleOwner(this);
        mMyViewModel.setNum(mMyViewModel.load());
        mBinding.tv.setText(String.valueOf(mMyViewModel.load()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyViewModel.setNum(mMyViewModel.load());
        mBinding.tv.setText(String.valueOf(mMyViewModel.load()));
    }

    public void clickBtn1(){
        int add = mMyViewModel.add(1);
        mMyViewModel.setNum(add);
        mBinding.tv.setText(String.valueOf(add));
    }

    public void clickBtn2(){
        int reduce = mMyViewModel.add(-1);
        mMyViewModel.setNum(reduce);
        mBinding.tv.setText(String.valueOf(reduce));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMyViewModel.save(mMyViewModel.getNum());
    }
}
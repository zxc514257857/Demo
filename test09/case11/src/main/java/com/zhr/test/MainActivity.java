package com.zhr.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.zhr.test.databinding.ActivityMain11Binding;

/**
 * ViewModel + DataBinding （使用ViewModel的特性进行数据的缓存，可以缓存横竖屏切换的数据，
 * 但是不能缓存后台进程被杀死和退出应用后的数据） 没有使用LiveData
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyViewModel mMyViewModel;
    private ActivityMain11Binding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main11);
        mMyViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        mBinding.setMainActivity(this);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBinding.tv.setText(String.valueOf(mMyViewModel.getNum()));
    }

    public void clickBtn1() {
        int add = mMyViewModel.add(1);
        mMyViewModel.setNum(add);
        mBinding.tv.setText(String.valueOf(add));
    }

    public void clickBtn2() {
        int reduce = mMyViewModel.add(-1);
        mMyViewModel.setNum(reduce);
        mBinding.tv.setText(String.valueOf(reduce));
    }

    /**************************************横竖屏切换和后台进行被杀死 不调用是什么鬼**************************************/
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        int stateKey = outState.getInt("state_key");
        mMyViewModel.setNum(stateKey);
        mBinding.tv.setText(stateKey);
        Log.i(TAG, "onSaveInstanceState: " + stateKey);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int num = mMyViewModel.getNum();
        savedInstanceState.putInt("state_key" , num);
        Log.i(TAG, "onRestoreInstanceState: " + num);
    }
    /**************************************横竖屏切换和后台进行被杀死 不调用是什么鬼**************************************/
}

package com.zhr.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.zhr.test.databinding.ActivityMain14Binding;

/**
 * AndroidViewModel + SavedStateHandle(在正常情况下起作用) + DataBinding + SharedPrefs(在生命周期中起作用，加一个save和load方法)
 * 解决应用后台杀死的缓存保存以及数据的持久化存储问题。是最全面和最终极的应用了
 *
 * 共同使用androidViewModel 和savedStateHandle，在ViewModel中只用传入 SavedStateFactory 就可以了，不需要再管Context
 */
public class MainActivity extends AppCompatActivity {

    private MyViewModel mMyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain14Binding binding = DataBindingUtil.setContentView(this , R.layout.activity_main14);
        mMyViewModel = new ViewModelProvider(this , new SavedStateViewModelFactory(getApplication() , this)).get(MyViewModel.class);
        binding.setViewModel(mMyViewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyViewModel.load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMyViewModel.save();
    }
}

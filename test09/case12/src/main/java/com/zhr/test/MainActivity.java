package com.zhr.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zhr.test.databinding.ActivityMain12Binding;

/**
 * ViewModel（AndroidViewModel） + LiveData + DataBinding + SharedPrefs（进行数据的全方位存储）
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyViewModel mMyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 为什么这里要用Main1和Main2呢 ， 因为同样使用ActivityMainBinding会报错 ，不会重复
        ActivityMain12Binding binding = DataBindingUtil.setContentView(this , R.layout.activity_main12);
        mMyViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.setViewModel(mMyViewModel);
        binding.setLifecycleOwner(this);
        // LiveData 数据变化监听(与ViewModel相关)
        mMyViewModel.getNum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer > 0){
                    Toast.makeText(MainActivity.this, "大于0啦！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "小于0啦！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
        mMyViewModel.load();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        mMyViewModel.save();
    }
}

package com.zhr.test;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * LiveData 感知数据的变化 自动刷新界面
 * ViewModel + LiveData（进行数据缓存和数据动态变化感知）
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private ImageButton mIb1;
    private ImageButton mIb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewModel();
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
        mIb1 = findViewById(R.id.ib1);
        mIb2 = findViewById(R.id.ib2);
    }

    private void initViewModel() {
        // 要使用new ViewModelProvider(this)，要先使用ViewModelProviders 加入一个远程依赖
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // 观察ViewModel中的数据变化（感知数据的变化，自动刷新界面）
        // 这里的owner是指具有周期管理方法的类 系统已经帮我们处理好了创建和销毁操作 不需要我们再去操作了
        myViewModel.getNum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mTv.setText(String.valueOf(integer));
            }
        });

        mIb1.setOnClickListener(view -> myViewModel.addNum(1));
        mIb2.setOnClickListener(view -> myViewModel.addNum(-1));
    }
}

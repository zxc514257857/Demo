package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例五：SP实现设置中心
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Bind(R.id.cb)
    CheckBox mCb;
    @Bind(R.id.seekBar)
    SeekBar mSeekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        // 设置CheckBox选择状态改变的监听
        mCb.setOnCheckedChangeListener(this);
        // 设置SeekBar拖动状态的监听
        mSeekBar.setOnSeekBarChangeListener(this);

        boolean isChecked = PrefUtils.getBoolean(mContext, "isChecked", false);
        if(isChecked){
            mCb.setChecked(isChecked);
            int progress = PrefUtils.getInt(mContext, "progress", 0);
            mSeekBar.setProgress(progress);
        }else{
            mCb.setChecked(isChecked);
            mSeekBar.setProgress(0);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean statue) {
        // 对选中状态进行判定
        if(statue){
            PrefUtils.putBoolean(mContext , "isChecked" , statue);
        }else{
            PrefUtils.putBoolean(mContext , "isChecked" , statue);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // fromUser字段的意思是：手动拖动的进度条变化为true, 因为代码更新造成进度条变化为false
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // 停止拖动时保存进度条信息
        int progress = seekBar.getProgress();
        PrefUtils.putInt(mContext , "progress" , progress);
    }
}

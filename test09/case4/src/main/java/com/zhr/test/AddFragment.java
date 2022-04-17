package com.zhr.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddFragment extends Fragment {

    private static final String TAG = "AddFragment";
    private String yuwen = "";
    private String shuxue = "";
    private String yingyu = "";

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.sb)
    SeekBar mSb;
    @BindView(R.id.cb1)
    CheckBox mCb1;
    @BindView(R.id.cb2)
    CheckBox mCb2;
    @BindView(R.id.cb3)
    CheckBox mCb3;
    @BindView(R.id.rb)
    RatingBar mRb;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_add, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        // Seekbar的改变监听
        mSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 进度条当前进度
             * 是手动控制还是程序控制（true的话是手动控制  false的话是自动控制）
             * @param seekBar
             * @param progress
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG , "onProgressChanged: progress:" + progress + ",fromUser:" + fromUser);
                mTv.setText(String.valueOf(progress));
                mTv.setAlpha(progress);
            }

            /**
             * 开始拖动时回调(触控跟踪)
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: ");
            }

            /**
             * 停止推动时回调(触控跟踪)
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch: ");
            }
        });

        // checkbox 就是可以单选 也可以多选的控件
        mCb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                yuwen = "语文";
            }else {
                yuwen = "";
            }
            mTv.setText(yuwen + shuxue + yingyu);
        });
        mCb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                shuxue = "数学";
            }else {
                shuxue = "";
            }
            mTv.setText(yuwen + shuxue + yingyu);
        });
        mCb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                yingyu = "英语";
            }else {
                yingyu = "";
            }
            mTv.setText(yuwen + shuxue + yingyu);
        });

        // 设置rattingbar是否能手动触摸修改数据  true表示不能手动触摸修改  false表示可以手动触摸修改
        mRb.setIsIndicator(false);
        // fromUser true表示人工操作  false表示代码操作
        mRb.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            mTv.setText(String.valueOf(rating));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(null != unbinder){
            unbinder.unbind();
        }
    }
}
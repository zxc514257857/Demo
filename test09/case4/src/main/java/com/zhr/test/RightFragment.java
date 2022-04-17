package com.zhr.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RightFragment extends Fragment {

    private static final String TAG = "RightFragment";

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.pb1)
    ProgressBar mPb1;
    @BindView(R.id.pb2)
    ProgressBar mPb2;
    @BindView(R.id.pb3)
    ProgressBar mPb3;
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.switch1)
    Switch mSwitch1;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_right, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        mPb3.setMax(100);
        mBtn.setOnClickListener(view -> {
            String trim = mEt.getText().toString().trim();
            if(!TextUtils.isEmpty(trim)){
                mPb3.setProgress(Integer.parseInt(trim));
            }
        });
        mRg.setOnCheckedChangeListener((radioGroup, id) -> {
            if(id == R.id.rb1){
                Log.i(TAG, "onCreateView: 图片一");
                mIv.setImageResource(R.mipmap.ic_launcher_round);
            }else {
                Log.i(TAG, "onCreateView: 图片二");
                mIv.setImageResource(R.mipmap.ic_launcher);
            }
        });
        mSwitch1.setOnCheckedChangeListener((compoundButton, boo) -> {
            if(boo){
                mTv.setText("开");
            }else {
                mTv.setText("关");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != unbinder) {
            unbinder.unbind();
        }
    }
}

package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.FanJianBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例十五：汉字繁简转换
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private TextView mTv;
    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mEt= findViewById(R.id.et);
        mTv = findViewById(R.id.tv);
    }

    private void initData() {}

    public void click1(View view){
        view.setOnClickListener(this);
    }

    public void click2(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String et = mEt.getText().toString().trim();
        if(!TextUtils.isEmpty(et)){
            OkHttpUtils.post().url(AppConstance.BASE1 + et)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        FanJianBean fanJianBean = JSON.parseObject(response, FanJianBean.class);
                        if(AppConstance.SUCCESS.equals(fanJianBean.getSuccess())){
                            ResultBean result = fanJianBean.getResult();
                            Toast.makeText(mContext , "汉字繁简互转成功！" , Toast.LENGTH_LONG).show();
                            mTv.setText("转换前：" + result.getWd() + "\n"
                                    + "转换为繁体：" + result.getText());
                        }else{
                            Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    }else{
                        Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                        mTv.setText(AppConstance.NULL);
                    }
                }
            });

            OkHttpUtils.post().url(AppConstance.BASE2 + et)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        FanJianBean fanJianBean = JSON.parseObject(response, FanJianBean.class);
                        if(AppConstance.SUCCESS.equals(fanJianBean.getSuccess())){
                            ResultBean result = fanJianBean.getResult();
                            Toast.makeText(mContext , "汉字繁简互转成功！" , Toast.LENGTH_LONG).show();
                            mTv.setText("转换前：" + result.getWd() + "\n"
                                    + "转换为简体：" + result.getText());
                        }else{
                            Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    }else{
                        Toast.makeText(mContext , "汉字繁简互转失败！" , Toast.LENGTH_LONG).show();
                        mTv.setText(AppConstance.NULL);
                    }
                }
            });
        }else{
            Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
            mTv.setText(AppConstance.NULL);
        }
    }
}

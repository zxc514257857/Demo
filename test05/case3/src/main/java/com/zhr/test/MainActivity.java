package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例三：网页源码查看器
 * OkHttpUtils使用返回String的方式,url中放入一个网页地址即可查看网页源码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private TextView mTvWebSourceCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTvWebSourceCode = findViewById(R.id.tv_web_source_code);
    }

    private void initData() {}

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext , "点击成功！" , Toast.LENGTH_LONG).show();
        OkHttpUtils.get().url(AppConstance.BAIDU).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext , "获取网页源码失败！" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                    Toast.makeText(mContext , "获取网页源码成功！" , Toast.LENGTH_LONG).show();
                    mTvWebSourceCode.setText(response);
                }else{
                    Toast.makeText(mContext , "获取网页源码失败！" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

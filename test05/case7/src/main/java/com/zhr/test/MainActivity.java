package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.PublicIpBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例七：本机公网IP地址查询
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
    }

    private void initData() {}

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        OkHttpUtils.post().url(AppConstance.BASE)
                .addParams("appkey" , AppConstance.TEST_APP_KEY)
                .addParams("sign" , AppConstance.TEST_SIGN)
                .addParams("format" , AppConstance.TEST_FORMAT)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext , "查询本机公网IP地址失败！" , Toast.LENGTH_LONG).show();
                mTv.setText(AppConstance.NULL);
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                    PublicIpBean publicIpBean = JSON.parseObject(response, PublicIpBean.class);
                    if(AppConstance.SUCCESS.equals(publicIpBean.getSuccess())){
                        ResultBean result = publicIpBean.getResult();
                        Toast.makeText(mContext , "查询本机公网IP地址成功！" , Toast.LENGTH_LONG).show();
                        mTv.setText("本机公网IP地址：" + result.getIp() + "\n"
                                + "本机公网IP地址所在地区：" + result.getAtt()+ "\n"
                                + "网络运营商：" + result.getOperators()
                        );
                    }else{
                        Toast.makeText(mContext , "本机公网IP地址查询失败！" , Toast.LENGTH_LONG).show();
                        mTv.setText(AppConstance.NULL);
                    }
                }
            }
        });
    }
}

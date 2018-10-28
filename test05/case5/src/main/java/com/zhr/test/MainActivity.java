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
import com.zhr.test.bean.IPBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例五：IP地址归属查询（百度地图SDK同样可以实现）
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

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String et = mEt.getText().toString().trim();
        if(!TextUtils.isEmpty(et)){
            OkHttpUtils.post().url(AppConstance.IP + et)
                    .addParams("ip" , et)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "查询IP地址归属地失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        IPBean ipBean = JSON.parseObject(response, IPBean.class);
                        if(AppConstance.SUCCESS.equals(ipBean.getSuccess())){
                            ResultBean result = ipBean.getResult();
                            if(AppConstance.STATUE_OK.equals(result.getStatus())){
                                Toast.makeText(mContext , "查询IP地址归属地成功！" , Toast.LENGTH_LONG).show();
                                mTv.setText("IP地址：" + result.getIp() + "\n"
                                        + "IP地址段开始：" + result.getIpStart() + "\n"
                                        + "IP地址段结束：" + result.getIpEnd() + "\n"
                                        + "数字格式IP地址：" + result.getInetIP() + "\n"
                                        + "数字格式IP地址开始：" + result.getInetStart() + "\n"
                                        + "数字格式IP地址结束：" + result.getInetEnd() + "\n"
                                        + "IP地址所在地区号：" + result.getAreano() + "\n"
                                        + "网络运营商：" + result.getDetailed() + "\n"
                                        + "归属地：" + result.getAtt()
                                );
                            }else {
                                Toast.makeText(mContext , "IP地址输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }
                    }
                }
            });
        }else{
            Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
            mTv.setText(AppConstance.NULL);
        }
    }
}

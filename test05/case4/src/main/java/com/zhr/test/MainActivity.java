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
import com.zhr.test.bean.PhoneBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例四：手机号码归属地查询
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
            OkHttpUtils.post().url(AppConstance.PHONE + et)
                    .addParams("phone" , et)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "查询手机号码归属地失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        PhoneBean phoneBean = JSON.parseObject(response, PhoneBean.class);
                        if(AppConstance.SUCCESS.equals(phoneBean.getSuccess())){
                            ResultBean result = phoneBean.getResult();
                            if(AppConstance.ALREADY_ATT.equals(result.getStatus())){
                                Toast.makeText(mContext , "查询手机号码归属地成功！" , Toast.LENGTH_LONG).show();
                                mTv.setText("手机号码：" + result.getPhone() + "\n"
                                        + "所在地区号：" + result.getArea() + "\n"
                                        + "所在地邮编：" + result.getPostno() + "\n"
                                        + "归属地：" + result.getAtt() + "\n"
                                        + "卡类型：" + result.getCtype() + "\n"
                                        + "所属运营商：" + result.getOperators());
                            }else {
                                Toast.makeText(mContext , "手机号码输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
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

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
import com.zhr.test.bean.IdcardBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例六：身份证号码归属查询
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
            OkHttpUtils.post().url(AppConstance.BASE + et)
                    .addParams("ip" , et)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "查询身份证号码归属地失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        IdcardBean idcardBean = JSON.parseObject(response, IdcardBean.class);
                        if(AppConstance.SUCCESS.equals(idcardBean.getSuccess())){
                            ResultBean result = idcardBean.getResult();
                            if(AppConstance.STATUE_OK.equals(result.getStatus())){
                                Toast.makeText(mContext , "查询身份证号码归属地成功！" , Toast.LENGTH_LONG).show();
                                mTv.setText("身份证号：" + result.getIdcard() + "\n"
                                        + "行政区划代码：" + result.getPar() + "\n"
                                        + "出生日期：" + result.getBorn() + "\n"
                                        + "性别：" + result.getSex() + "\n"
                                        + "身份证号码所在地区：" + result.getAtt() + "\n"
                                        + "身份证号码所在地邮编：" + result.getPostno() + "\n"
                                        + "身份证号码所在地区号：" + result.getAreano()
                                );
                            }else {
                                Toast.makeText(mContext , "身份证号码输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }else{
                            Toast.makeText(mContext , "身份证号码输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
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

package com.zhr.myapplication;

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
import com.zhr.myapplication.bean.ResultBean;
import com.zhr.myapplication.bean.ShortUrlBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例十：短网址生成
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;
    private EditText mEt;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView(){
        mTv = findViewById(R.id.tv);
        mEt = findViewById(R.id.et);
    }

    public void initData(){}

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String et = mEt.getText().toString().trim();
        if (!TextUtils.isEmpty(et)) {
            OkHttpUtils.post().url(AppConstance.BASE + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询短网址失败！", Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        ShortUrlBean shortUrlBean = JSON.parseObject(response, ShortUrlBean.class);
                        if (AppConstance.SUCCESS.equals(shortUrlBean.getSuccess())) {
                            ResultBean result = shortUrlBean.getResult();
                            Toast.makeText(mContext, "查询短网址成功！", Toast.LENGTH_LONG).show();
                            if(AppConstance.SUCCESS.equals(result.getExits())){
                                mTv.setText("链接类型：短链接" + "\n"
                                        + "原链接：" + result.getSourceUrl() + "\n"
                                        + "短链接：" + result.getShortUrl()
                                );
                            }else{
                                mTv.setText("链接类型：长链接" + "\n"
                                        + "原链接：" + result.getSourceUrl() + "\n"
                                        + "短链接：" + result.getShortUrl()
                                );
                            }
                        } else {
                            Toast.makeText(mContext, "查询短网址失败！", Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询短网址失败！", Toast.LENGTH_LONG).show();
                        mTv.setText(AppConstance.NULL);
                    }
                }
            });
        } else {
            Toast.makeText(mContext, "未输入内容，请重新输入！", Toast.LENGTH_LONG).show();
            mTv.setText(AppConstance.NULL);
        }
    }
}

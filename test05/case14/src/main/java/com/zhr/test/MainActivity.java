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
import com.zhr.test.bean.HanziPinyinInterchangeBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例十四：汉字拼音互转
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
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "汉字拼音互转失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        HanziPinyinInterchangeBean hanziPinyinInterchangeBean = JSON.parseObject(response, HanziPinyinInterchangeBean.class);
                        if(AppConstance.SUCCESS.equals(hanziPinyinInterchangeBean.getSuccess())){
                            ResultBean result = hanziPinyinInterchangeBean.getResult();
                            Toast.makeText(mContext , "汉字拼音互转成功！" , Toast.LENGTH_LONG).show();
                            mTv.setText("需要转换的汉字：" + result.getWd() + "\n"
                                    + "转换结果：" + result.getRet());
                        }else{
                            Toast.makeText(mContext , "汉字拼音互转失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    }else{
                        Toast.makeText(mContext , "汉字拼音互转失败！" , Toast.LENGTH_LONG).show();
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

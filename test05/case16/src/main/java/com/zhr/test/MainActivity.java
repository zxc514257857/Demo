package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.rate.RateBean;
import com.zhr.test.bean.rate.ResultBean;
import com.zhr.test.bean.rate_list.RateListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 案例十六：货币比率获取
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private TextView mTv;
    private AutoCompleteTextView mTv1;
    private AutoCompleteTextView mTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        mTv = findViewById(R.id.tv);
    }

    private void initData() {
        OkHttpUtils.post().url(AppConstance.BASE1)
                .addParams("appkey" , AppConstance.TEST_APP_KEY)
                .addParams("sign" , AppConstance.TEST_SIGN)
                .addParams("format" , AppConstance.TEST_FORMAT)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext , "获取货币名称列表失败！" , Toast.LENGTH_LONG).show();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                    RateListBean rateListBean = JSON.parseObject(response, RateListBean.class);
                    if(AppConstance.SUCCESS.equals(rateListBean.getSuccess())){
                        List<com.zhr.test.bean.rate_list.ResultBean> result = rateListBean.getResult();
                        String[] arr = new String[result.size()];
                        for (int a = 0 ; a < result.size() ; a++){
                            arr[a] = result.get(a).getCurno();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext , android.R.layout.simple_list_item_1, arr);
                        // 设置适配器
                        mTv1.setAdapter(adapter);
                        mTv2.setAdapter(adapter);
                    }else{
                        Toast.makeText(mContext , "获取货币名称列表失败！" , Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mContext , "获取货币名称列表失败！" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String tv1 = mTv1.getText().toString().trim();
        String tv2 = mTv2.getText().toString().trim();
        if(!TextUtils.isEmpty(tv1) && !TextUtils.isEmpty(tv2)){
            OkHttpUtils.post().url(AppConstance.BASE2)
                    .addParams("scur" , tv1)
                    .addParams("tcur" , tv2)
                    .addParams("appkey" , AppConstance.TEST_APP_KEY)
                    .addParams("sign" , AppConstance.TEST_SIGN)
                    .addParams("format" , AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext , "获取货币比率失败！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        RateBean rateBean = JSON.parseObject(response, RateBean.class);
                        if(AppConstance.SUCCESS.equals(rateBean.getSuccess())){
                            ResultBean result = rateBean.getResult();
                            if(AppConstance.STATUE.equals(result.getStatus())){
                                Toast.makeText(mContext , "获取货币比率成功！" , Toast.LENGTH_LONG).show();
                                mTv.setText("源货币：" + result.getScur() + "\n"
                                        + "目的货币：" + result.getTcur() + "\n"
                                        + "货币比率：" + result.getRatenm() + "\n"
                                        + "比率：" + result.getRate() + "\n"
                                        + "更新时间：" + result.getUpdate()+ "\n"
                                );
                            }else {
                                Toast.makeText(mContext , "获取货币比率失败！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }else{
                            Toast.makeText(mContext , "获取货币比率失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    }else{
                        Toast.makeText(mContext , "获取货币比率失败！" , Toast.LENGTH_LONG).show();
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

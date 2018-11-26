package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.zhr.test.bean.rate_history.RateHistoryBean;
import com.zhr.test.bean.rate_list.RateListBean;
import com.zhr.test.bean.rate_list.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 案例十七：历史汇率查询
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private AutoCompleteTextView mTv1;
    private TextView mTv2;
    private EditText mEt;
    private TimePickerView mPvTime;
    private Button mBtn;

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
        mEt = findViewById(R.id.et);
        mBtn = findViewById(R.id.btn);
    }

    private void initData() {
        // 时间选择器
        mPvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mEt.setText(getTime(date));
            }
        }).build();
        mEt.setOnClickListener(this);
        mBtn.setOnClickListener(this);

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
                        List<ResultBean> result = rateListBean.getResult();
                        String[] arr = new String[result.size()];
                        for (int a = 0 ; a < result.size() ; a++){
                            arr[a] = result.get(a).getCurno();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext , android.R.layout.simple_list_item_1, arr);
                        // 设置适配器
                        mTv1.setAdapter(adapter);
                    }else{
                        Toast.makeText(mContext , "获取货币名称列表失败！" , Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mContext , "获取货币名称列表失败！" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                String et = mEt.getText().toString().trim();
                String tv1 = mTv1.getText().toString().trim();
                if(!TextUtils.isEmpty(et) && !TextUtils.isEmpty(tv1)){
                    OkHttpUtils.post().url(AppConstance.BASE2)
                            .addParams("cur" , tv1)
                            .addParams("date" , et)
                            .addParams("appkey" , AppConstance.TEST_APP_KEY)
                            .addParams("sign" , AppConstance.TEST_SIGN)
                            .addParams("format" , AppConstance.TEST_FORMAT)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(mContext , "查询历史汇率失败！" , Toast.LENGTH_LONG).show();
                            mTv2.setText(AppConstance.NULL);
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response, int id) {
                            if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                                RateHistoryBean rateHistoryBean = JSON.parseObject(response, RateHistoryBean.class);
                                if(AppConstance.SUCCESS.equals(rateHistoryBean.getSuccess())){
                                    List<com.zhr.test.bean.rate_history.ResultBean> result = rateHistoryBean.getResult();
                                    com.zhr.test.bean.rate_history.ResultBean resultBean = result.get(0);
                                    Toast.makeText(mContext , "查询历史汇率成功！" , Toast.LENGTH_LONG).show();
                                    mTv2.setText("货币简称：" + resultBean.getCurno() + "\n"
                                            + "查询日期：" + resultBean.getDays() + "\n"
                                            + "查询的汇率：" + resultBean.getRate() + "\n"
                                            + "更新时间：" + resultBean.getUptime()
                                    );
                                }else{
                                    Toast.makeText(mContext , "查询历史汇率失败！" , Toast.LENGTH_LONG).show();
                                    mTv2.setText(AppConstance.NULL);
                                }
                            }else{
                                Toast.makeText(mContext , "查询历史汇率失败！" , Toast.LENGTH_LONG).show();
                                mTv2.setText(AppConstance.NULL);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
                    mTv2.setText(AppConstance.NULL);
                }
                break;

            case R.id.et:
                mPvTime.show();
                break;

            default:
                break;
        }
    }

    private String getTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

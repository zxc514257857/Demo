package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.IdcardBean;
import com.zhr.test.bean.ResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 案例八：未来七天天气预报查询
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private EditText mEt;
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mEt= findViewById(R.id.et);
        mRv = findViewById(R.id.rv);
    }

    private void initData() {
        mRv.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        mRv.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 30, getResources().getColor(R.color.divide_gray_color)));
    }

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
                    Toast.makeText(mContext , "查询天气预报失败！" , Toast.LENGTH_LONG).show();
                    mRv.setAdapter(new WeatherAdapter(mContext , null));
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                        IdcardBean idcardBean = JSON.parseObject(response, IdcardBean.class);
                        if(idcardBean != null){
                            if(AppConstance.SUCCESS.equals(idcardBean.getSuccess())){
                                List<ResultBean> result = idcardBean.getResult();
                                mRv.setAdapter(new WeatherAdapter(mContext , result));
                            }else{
                                Toast.makeText(mContext , "城市信息输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
                                mRv.setAdapter(new WeatherAdapter(mContext , null));
                            }
                        }else {
                            Toast.makeText(mContext , "查询天气预报失败！" , Toast.LENGTH_LONG).show();
                            mRv.setAdapter(new WeatherAdapter(mContext , null));
                        }
                    }else {
                        Toast.makeText(mContext , "查询天气预报失败！" , Toast.LENGTH_LONG).show();
                        mRv.setAdapter(new WeatherAdapter(mContext , null));
                    }
                }
            });
        }else{
            Toast.makeText(mContext , "城市信息输入错误，请重新输入！" , Toast.LENGTH_LONG).show();
            mRv.setAdapter(new WeatherAdapter(mContext , null));
        }
    }
}

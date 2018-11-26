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
import com.zhr.test.bean.area_code.AreaCodeBean;
import com.zhr.test.bean.post_code.ListsBean;
import com.zhr.test.bean.post_code.PostCodeBean;
import com.zhr.test.bean.post_code.ResultBean;
import com.zhr.test.bean.time.TimeBean;
import com.zhr.test.bean.workday.WorkDayBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 案例九：查询邮政编码归属地 查询区号归属地 日期查询 北京时间查询
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

    public void click1(View view){
        view.setOnClickListener(this);
    }

    public void click2(View view){
        view.setOnClickListener(this);
    }

    public void click3(View view){
        view.setOnClickListener(this);
    }

    public void click4(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String et = mEt.getText().toString().trim();
        switch (v.getId()){
            // 邮编
            case R.id.click1:
                if(!TextUtils.isEmpty(et)){
                    OkHttpUtils.post().url(AppConstance.BASE1 + et)
                            .addParams("appkey" , AppConstance.TEST_APP_KEY)
                            .addParams("sign" , AppConstance.TEST_SIGN)
                            .addParams("format" , AppConstance.TEST_FORMAT)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(mContext , "查询邮政编码归属地失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response, int id) {
                            if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                                PostCodeBean postCodeBean = JSON.parseObject(response, PostCodeBean.class);
                                if(AppConstance.SUCCESS.equals(postCodeBean.getSuccess())){
                                    ResultBean result = postCodeBean.getResult();
                                    List<ListsBean> lists = result.getLists();
                                    if(lists != null){
                                        if(lists.size() > 0){
                                            Toast.makeText(mContext , "查询邮政编码归属地成功！" , Toast.LENGTH_LONG).show();
                                            ListsBean listsBean = lists.get(0);
                                            mTv.setText("地区id：" + listsBean.getAreaid() + "\n"
                                                    + "邮政编码：" + listsBean.getPostcode() + "\n"
                                                    + "区号：" +  listsBean.getAreacode() + "\n"
                                                    + "地区名称：" + listsBean.getAreanm() + "\n"
                                                    + "地区简称：" + listsBean.getSimcall()
                                            );
                                        }
                                    }
                                }else {
                                    Toast.makeText(mContext , "查询邮政编码归属地失败！" , Toast.LENGTH_LONG).show();
                                    mTv.setText(AppConstance.NULL);
                                }
                            }else {
                                Toast.makeText(mContext , "查询邮政编码归属地失败！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                break;

            case R.id.click2:
                if(!TextUtils.isEmpty(et)){
                    OkHttpUtils.post().url(AppConstance.BASE2 + et)
                            .addParams("appkey" , AppConstance.TEST_APP_KEY)
                            .addParams("sign" , AppConstance.TEST_SIGN)
                            .addParams("format" , AppConstance.TEST_FORMAT)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(mContext , "查询区号归属地失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response, int id) {
                            if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                                AreaCodeBean areaCodeBean = JSON.parseObject(response, AreaCodeBean.class);
                                if(AppConstance.SUCCESS.equals(areaCodeBean.getSuccess())){
                                    com.zhr.test.bean.area_code.ResultBean result = areaCodeBean.getResult();
                                    List<com.zhr.test.bean.area_code.ListsBean> lists = result.getLists();
                                    if(lists != null){
                                        if(lists.size() > 0){
                                            Toast.makeText(mContext , "查询区号归属地成功！" , Toast.LENGTH_LONG).show();
                                            com.zhr.test.bean.area_code.ListsBean listsBean = lists.get(0);
                                            mTv.setText("地区id：" + listsBean.getAreaid() + "\n"
                                                    + "邮政编码：" + listsBean.getPostcode() + "\n"
                                                    + "区号：" +  listsBean.getAreacode() + "\n"
                                                    + "地区名称：" + listsBean.getAreanm() + "\n"
                                                    + "地区简称：" + listsBean.getSimcall()
                                            );
                                        }
                                    }
                                }else {
                                    Toast.makeText(mContext , "查询区号归属地失败！" , Toast.LENGTH_LONG).show();
                                    mTv.setText(AppConstance.NULL);
                                }
                            }else {
                                Toast.makeText(mContext , "查询区号归属地失败！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                break;

            case R.id.click3:
                if(!TextUtils.isEmpty(et)){
                    OkHttpUtils.post().url(AppConstance.BASE3 + et)
                            .addParams("appkey" , AppConstance.TEST_APP_KEY)
                            .addParams("sign" , AppConstance.TEST_SIGN)
                            .addParams("format" , AppConstance.TEST_FORMAT)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(mContext , "日期查询失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response, int id) {
                            if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                                WorkDayBean workDayBean = JSON.parseObject(response, WorkDayBean.class);
                                if(AppConstance.SUCCESS.equals(workDayBean.getSuccess())){
                                    com.zhr.test.bean.workday.ResultBean result = workDayBean.getResult();
                                    Toast.makeText(mContext , "日期查询成功！" , Toast.LENGTH_LONG).show();
                                    mTv.setText("日期：" + result.getDate() + "\n"
                                            + "是否假期：" + result.getWorknm()+ "\n"
                                            + "中文星期：" +  result.getWeek3() + "\n"
                                            + "英文星期：" + result.getWeek4()+ "\n"
                                            + "节日：" + result.getRemark()
                                    );
                                }else {
                                    Toast.makeText(mContext , "日期查询失败！" , Toast.LENGTH_LONG).show();
                                    mTv.setText(AppConstance.NULL);
                                }
                            }else {
                                Toast.makeText(mContext , "日期查询失败！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
                    mTv.setText(AppConstance.NULL);
                }
                break;

            case R.id.click4:
                OkHttpUtils.post().url(AppConstance.BASE4)
                        .addParams("appkey" , AppConstance.TEST_APP_KEY)
                        .addParams("sign" , AppConstance.TEST_SIGN)
                        .addParams("format" , AppConstance.TEST_FORMAT)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext , "当前北京时间查询失败！" , Toast.LENGTH_LONG).show();
                        mTv.setText(AppConstance.NULL);
                    }
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response, int id) {
                        if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                            TimeBean timeBean = JSON.parseObject(response, TimeBean.class);
                            if(AppConstance.SUCCESS.equals(timeBean.getSuccess())){
                                com.zhr.test.bean.time.ResultBean result = timeBean.getResult();
                                Toast.makeText(mContext , "当前北京时间查询成功！" , Toast.LENGTH_LONG).show();
                                mTv.setText("日期：" + result.getDatetime1()+ "\n"
                                        + "中文星期：" +  result.getWeek3() + "\n"
                                        + "英文星期：" + result.getWeek4()
                                );
                            }else {
                                Toast.makeText(mContext , "当前北京时间查询失败！" , Toast.LENGTH_LONG).show();
                                mTv.setText(AppConstance.NULL);
                            }
                        }else {
                            Toast.makeText(mContext , "当前北京时间查询失败！" , Toast.LENGTH_LONG).show();
                            mTv.setText(AppConstance.NULL);
                        }
                    }
                });
                break;

            default:
                break;
        }
    }
}

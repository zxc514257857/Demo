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
import com.zhr.myapplication.bean.alexa.AlexaBean;
import com.zhr.myapplication.bean.alexa.ResultBean;
import com.zhr.myapplication.bean.backlink.BacklinkBean;
import com.zhr.myapplication.bean.dns.DnsBean;
import com.zhr.myapplication.bean.entry.EntryBean;
import com.zhr.myapplication.bean.whois.WhoisBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 案例十二：域名信息查询(alexa信息、备案信息(未免费开放)、whois信息)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView(){
        mEt = findViewById(R.id.et);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv5 = findViewById(R.id.tv5);
    }

    public void initData(){}

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String et = mEt.getText().toString().trim();
        if (!TextUtils.isEmpty(et)) {
            OkHttpUtils.post().url(AppConstance.BASE1 + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询域名alexa信息失败！", Toast.LENGTH_LONG).show();
                    mTv1.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        AlexaBean alexaBean = JSON.parseObject(response, AlexaBean.class);
                        if (AppConstance.SUCCESS.equals(alexaBean.getSuccess())) {
                            ResultBean result = alexaBean.getResult();
                            if(AppConstance.STATUE_ALEXA.equals(result.getStatus())){
                                Toast.makeText(mContext, "查询域名alexa信息成功！", Toast.LENGTH_LONG).show();
                                mTv1.setText("查询的域名：" + result.getDomain() + "\n"
                                        + "网站名称：" + result.getSitenm() + "\n"
                                        + "网站站长：" + result.getWebmaster() + "\n"
                                        + "联系邮箱：" + result.getEmail() + "\n"
                                        + "收录日期：" + result.getEntryDate() + "\n"
                                        + "所属国家：" + result.getCountry() + "\n"
                                        + "联系邮箱：" + result.getEmail() + "\n"
                                        + "语言：" + result.getLange() + "\n"
                                        + "访问速度：" + result.getAccessSpeed() + "\n"
                                        + "速度评分：" + result.getSpeedScore() + "\n"
                                        + "反向链接：" + result.getBacklinks() + "\n"
                                        + "联系电话：" + result.getContactTel() + "\n"
                                        + "国内排名：" + result.getCountryRank() + "\n"
                                        + "全球排名：" + result.getGlobalRank() + "\n"
                                        + "排名变化趋势：" + result.getTrends()
                                );
                            }else {
                                Toast.makeText(mContext, "查询域名alexa信息失败！", Toast.LENGTH_LONG).show();
                                mTv1.setText(AppConstance.NULL);
                            }
                        }else {
                            Toast.makeText(mContext, "查询域名alexa信息失败！", Toast.LENGTH_LONG).show();
                            mTv1.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询域名alexa信息失败！", Toast.LENGTH_LONG).show();
                        mTv1.setText(AppConstance.NULL);
                    }
                }
            });

            OkHttpUtils.post().url(AppConstance.BASE2 + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询域名whois信息失败！", Toast.LENGTH_LONG).show();
                    mTv2.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        WhoisBean whoisBean = JSON.parseObject(response, WhoisBean.class);
                        if (AppConstance.SUCCESS.equals(whoisBean.getSuccess())) {
                            com.zhr.myapplication.bean.whois.ResultBean result = whoisBean.getResult();
                            if (AppConstance.STATUE_WHOIS.equals(result.getStatus())) {
                                Toast.makeText(mContext, "查询域名whois信息成功！", Toast.LENGTH_LONG).show();
                                mTv2.setText("查询的域名：" + result.getDomain() + "\n"
                                        + "域名注册人：" + result.getRegistrar() + "\n"
                                        + "注册邮箱：" + result.getContactEmail() + "\n"
                                        + "参考网址：" + result.getReferralUrl() + "\n"
                                        + "注册商：" + result.getSponsoringRegistrar() + "\n"
                                        + "注册商英文：" + result.getSponsoringegistrarEng() + "\n"
                                        + "whois服务器地址：" + result.getWhoisServer() + "\n"
                                        + "NS服务器地址：" + result.getNameServer() + "\n"
                                        + "域名状态：" + result.getDomStatus()+ "\n"
                                        + "域名注册时间：" + result.getDomInsdate() + "\n"
                                        + "域名最后更新时间：" + result.getDomUpddate() + "\n"
                                        + "域名过期时间：" + result.getDomExpdate()
                                );
                            }else {
                                Toast.makeText(mContext, "查询域名whois信息失败！", Toast.LENGTH_LONG).show();
                                mTv2.setText(AppConstance.NULL);
                            }
                        } else {
                            Toast.makeText(mContext, "查询域名whois信息失败！", Toast.LENGTH_LONG).show();
                            mTv2.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询域名whois信息失败！", Toast.LENGTH_LONG).show();
                        mTv2.setText(AppConstance.NULL);
                    }
                }
            });

            OkHttpUtils.post().url(AppConstance.BASE3 + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询引擎收录数量信息失败！", Toast.LENGTH_LONG).show();
                    mTv3.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        EntryBean entryBean = JSON.parseObject(response, EntryBean.class);
                        if (AppConstance.SUCCESS.equals(entryBean.getSuccess())) {
                            com.zhr.myapplication.bean.entry.ResultBean result = entryBean.getResult();
                            Toast.makeText(mContext, "查询引擎收录数量信息成功！", Toast.LENGTH_LONG).show();
                                mTv3.setText("网站编号：" + result.getWebid() + "\n"
                                        + "查询网站：" + result.getWebsite() + "\n"
                                        + "收录数量：" + result.getEntry()
                                );
                            }else {
                            Toast.makeText(mContext, "查询引擎收录数量信息失败！", Toast.LENGTH_LONG).show();
                            mTv3.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询引擎收录数量信息失败！", Toast.LENGTH_LONG).show();
                        mTv3.setText(AppConstance.NULL);
                    }
                }
            });

            OkHttpUtils.post().url(AppConstance.BASE4 + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询引擎反链信息失败！", Toast.LENGTH_LONG).show();
                    mTv4.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        BacklinkBean backlinkBean = JSON.parseObject(response, BacklinkBean.class);
                        if (AppConstance.SUCCESS.equals(backlinkBean.getSuccess())) {
                            com.zhr.myapplication.bean.backlink.ResultBean result = backlinkBean.getResult();
                            if(AppConstance.STATUS.equals(result.getStatus())){
                                Toast.makeText(mContext, "查询引擎反链信息成功！", Toast.LENGTH_LONG).show();
                                mTv4.setText("状态：" + result.getWebid() + "\n"
                                        + "网站编号：" + result.getWebsite() + "\n"
                                        + "网站网址：" + result.getWebid() + "\n"
                                        + "反链数量：" + result.getBacklink()
                                );
                            }else {
                                Toast.makeText(mContext, "查询引擎反链信息失败！", Toast.LENGTH_LONG).show();
                                mTv4.setText(AppConstance.NULL);
                            }
                        }else {
                            Toast.makeText(mContext, "查询引擎反链信息失败！", Toast.LENGTH_LONG).show();
                            mTv4.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询引擎反链信息失败！", Toast.LENGTH_LONG).show();
                        mTv4.setText(AppConstance.NULL);
                    }
                }
            });

            OkHttpUtils.post().url(AppConstance.BASE5 + et)
                    .addParams("appkey", AppConstance.TEST_APP_KEY)
                    .addParams("sign", AppConstance.TEST_SIGN)
                    .addParams("format", AppConstance.TEST_FORMAT)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "查询DNS信息失败！", Toast.LENGTH_LONG).show();
                    mTv4.setText(AppConstance.NULL);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)) {
                        DnsBean dnsBean = JSON.parseObject(response, DnsBean.class);
                        if (AppConstance.SUCCESS.equals(dnsBean.getSuccess())) {
                            com.zhr.myapplication.bean.dns.ResultBean result = dnsBean.getResult();
                            Toast.makeText(mContext, "查询DNS信息成功！", Toast.LENGTH_LONG).show();
                            mTv4.setText("域名：" + result.getDomain() + "\n"
                                    + "DNS服务器：" + result.getDnsServer() + "\n"
                                    + "名称服务器：" + result.getNameServer()
                            );
                        }else {
                            Toast.makeText(mContext, "查询DNS信息失败！", Toast.LENGTH_LONG).show();
                            mTv4.setText(AppConstance.NULL);
                        }
                    } else {
                        Toast.makeText(mContext, "查询DNS信息失败！", Toast.LENGTH_LONG).show();
                        mTv4.setText(AppConstance.NULL);
                    }
                }
            });
        } else {
            Toast.makeText(mContext, "未输入内容，请重新输入！", Toast.LENGTH_LONG).show();
            mTv1.setText(AppConstance.NULL);
            mTv2.setText(AppConstance.NULL);
            mTv3.setText(AppConstance.NULL);
            mTv4.setText(AppConstance.NULL);
            mTv5.setText(AppConstance.NULL);
        }
    }
}

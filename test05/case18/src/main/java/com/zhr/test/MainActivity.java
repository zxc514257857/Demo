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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.bank.ABCBean;
import com.zhr.test.bean.bank.BOCBean;
import com.zhr.test.bean.bank.CCBBean;
import com.zhr.test.bean.bank.CEBBean;
import com.zhr.test.bean.bank.CnyquotBean;
import com.zhr.test.bean.bank.ICBCBean;
import com.zhr.test.bean.bank.ResultBean;
import com.zhr.test.bean.coin.AEDBean;
import com.zhr.test.bean.coin.AUDBean;
import com.zhr.test.bean.coin.BRLBean;
import com.zhr.test.bean.coin.CADBean;
import com.zhr.test.bean.coin.CHFBean;
import com.zhr.test.bean.coin.DKKBean;
import com.zhr.test.bean.coin.EURBean;
import com.zhr.test.bean.coin.GBPBean;
import com.zhr.test.bean.coin.HKDBean;
import com.zhr.test.bean.coin.IDRBean;
import com.zhr.test.bean.coin.INRBean;
import com.zhr.test.bean.coin.JPYBean;
import com.zhr.test.bean.coin.KRWBean;
import com.zhr.test.bean.coin.KZTBean;
import com.zhr.test.bean.coin.MOPBean;
import com.zhr.test.bean.coin.MYRBean;
import com.zhr.test.bean.coin.NOKBean;
import com.zhr.test.bean.coin.NZDBean;
import com.zhr.test.bean.coin.PHPBean;
import com.zhr.test.bean.coin.RUBBean;
import com.zhr.test.bean.coin.SEKBean;
import com.zhr.test.bean.coin.SGDBean;
import com.zhr.test.bean.coin.THBBean;
import com.zhr.test.bean.coin.TJSBean;
import com.zhr.test.bean.coin.TWDBean;
import com.zhr.test.bean.coin.USDBean;
import com.zhr.test.bean.coin.ZARBean;
import com.zhr.test.bean.rate_list.RateListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 案例十八：人民币外汇牌价查询
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private AutoCompleteTextView mTv1;
    private TextView mTv2;
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
        mBtn = findViewById(R.id.btn);
    }

    private void initData() {
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
                        List<com.zhr.test.bean.rate_list.ResultBean> result = rateListBean.getResult();
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
                String tv1 = mTv1.getText().toString().trim();
                if(!TextUtils.isEmpty(tv1)){
                    OkHttpUtils.post().url(AppConstance.BASE2)
                            .addParams("curno" , tv1)
                            .addParams("appkey" , AppConstance.TEST_APP_KEY)
                            .addParams("sign" , AppConstance.TEST_SIGN)
                            .addParams("format" , AppConstance.TEST_FORMAT)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(mContext , "查询人民币外汇牌价失败！" , Toast.LENGTH_LONG).show();
                            mTv2.setText(AppConstance.NULL);
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response, int id) {
                            if(!TextUtils.isEmpty(response) && !AppConstance.NULL_STR.equals(response)){
                                CnyquotBean cnyquotBean = JSON.parseObject(response, CnyquotBean.class);
                                if(AppConstance.SUCCESS.equals(cnyquotBean.getSuccess())){
                                    ResultBean result = cnyquotBean.getResult();
                                    AEDBean aed = result.getAED();
                                    AUDBean aud = result.getAUD();
                                    BRLBean brl = result.getBRL();
                                    CADBean cad = result.getCAD();
                                    CHFBean chf = result.getCHF();
                                    DKKBean dkk = result.getDKK();
                                    EURBean eur = result.getEUR();
                                    GBPBean gbp = result.getGBP();
                                    HKDBean hkd = result.getHKD();
                                    IDRBean idr = result.getIDR();
                                    INRBean inr = result.getINR();
                                    JPYBean jpy = result.getJPY();
                                    KRWBean krw = result.getKRW();
                                    KZTBean kzt = result.getKZT();
                                    MOPBean mop = result.getMOP();
                                    MYRBean myr = result.getMYR();
                                    NOKBean nok = result.getNOK();
                                    NZDBean nzd = result.getNZD();
                                    PHPBean php = result.getPHP();
                                    RUBBean rub = result.getRUB();
                                    SEKBean sek = result.getSEK();
                                    SGDBean sgd = result.getSGD();
                                    THBBean thb = result.getTHB();
                                    TJSBean tjs = result.getTJS();
                                    TWDBean twd = result.getTWD();
                                    USDBean usd = result.getUSD();
                                    ZARBean zar = result.getZAR();
                                    if(aed != null){
                                        if(AppConstance.COIN.AED.equals(aed.getCurno())){
                                            BOCBean boc = aed.getBOC();
                                            CCBBean ccb = aed.getCCB();
                                            ICBCBean icbc = aed.getICBC();
                                            ABCBean abc = aed.getABC();
                                            CEBBean ceb = aed.getCEB();
                                            Toast.makeText(mContext , "查询AED外汇牌价成功！" , Toast.LENGTH_LONG).show();
                                            mTv2.setText("货币名称简称：" + aed.getCurno() + "\n"
                                                    + "货币名称全称：" + aed.getCurnm()+ "\n" + "\n"

                                                    + "银行名称简称：" + boc.getBankno() + "\n"
                                                    + "银行名称全称：" + boc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + boc.getSeSell() + "\n"
                                                    + "现汇买入价：" + boc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + boc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + boc.getCnBuy() + "\n"
                                                    + "中间价：" + boc.getMiddle() + "\n"
                                                    + "数据更新时间：" + boc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + ccb.getBankno() + "\n"
                                                    + "银行名称全称：" + ccb.getBanknm() + "\n"
                                                    + "现汇卖出价：" + ccb.getSeSell() + "\n"
                                                    + "现汇买入价：" + ccb.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + ccb.getCnSell()  + "\n"
                                                    + "现钞买入价：" + ccb.getCnBuy() + "\n"
                                                    + "中间价：" + ccb.getMiddle() + "\n"
                                                    + "数据更新时间：" + ccb.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + icbc.getBankno() + "\n"
                                                    + "银行名称全称：" + icbc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + icbc.getSeSell() + "\n"
                                                    + "现汇买入价：" + icbc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + icbc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + icbc.getCnBuy() + "\n"
                                                    + "中间价：" + icbc.getMiddle() + "\n"
                                                    + "数据更新时间：" + icbc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + abc.getBankno() + "\n"
                                                    + "银行名称全称：" + abc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + abc.getSeSell() + "\n"
                                                    + "现汇买入价：" + abc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + abc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + abc.getCnBuy() + "\n"
                                                    + "中间价：" + abc.getMiddle() + "\n"
                                                    + "数据更新时间：" + abc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + ceb.getBankno() + "\n"
                                                    + "银行名称全称：" + ceb.getBanknm() + "\n"
                                                    + "现汇卖出价：" + ceb.getSeSell() + "\n"
                                                    + "现汇买入价：" + ceb.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + ceb.getCnSell()  + "\n"
                                                    + "现钞买入价：" + ceb.getCnBuy() + "\n"
                                                    + "中间价：" + ceb.getMiddle() + "\n"
                                                    + "数据更新时间：" + ceb.getUpddate()
                                            );
                                        }else {
                                            Toast.makeText(mContext , "查询人民币和AED外汇牌价失败！" , Toast.LENGTH_LONG).show();
                                            mTv2.setText(AppConstance.NULL);
                                        }
                                    }else if(usd != null){
                                        if(AppConstance.COIN.USD.equals(usd.getCurno())){
                                            BOCBean boc = usd.getBOC();
                                            CCBBean ccb = usd.getCCB();
                                            ICBCBean icbc = usd.getICBC();
                                            ABCBean abc = usd.getABC();
                                            CEBBean ceb = usd.getCEB();
                                            Toast.makeText(mContext , "查询AED外汇牌价成功！" , Toast.LENGTH_LONG).show();
                                            mTv2.setText("货币名称简称：" + usd.getCurno() + "\n"
                                                    + "货币名称全称：" + usd.getCurnm()+ "\n" + "\n"

                                                    + "银行名称简称：" + boc.getBankno() + "\n"
                                                    + "银行名称全称：" + boc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + boc.getSeSell() + "\n"
                                                    + "现汇买入价：" + boc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + boc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + boc.getCnBuy() + "\n"
                                                    + "中间价：" + boc.getMiddle() + "\n"
                                                    + "数据更新时间：" + boc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + ccb.getBankno() + "\n"
                                                    + "银行名称全称：" + ccb.getBanknm() + "\n"
                                                    + "现汇卖出价：" + ccb.getSeSell() + "\n"
                                                    + "现汇买入价：" + ccb.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + ccb.getCnSell()  + "\n"
                                                    + "现钞买入价：" + ccb.getCnBuy() + "\n"
                                                    + "中间价：" + ccb.getMiddle() + "\n"
                                                    + "数据更新时间：" + ccb.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + icbc.getBankno() + "\n"
                                                    + "银行名称全称：" + icbc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + icbc.getSeSell() + "\n"
                                                    + "现汇买入价：" + icbc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + icbc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + icbc.getCnBuy() + "\n"
                                                    + "中间价：" + icbc.getMiddle() + "\n"
                                                    + "数据更新时间：" + icbc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + abc.getBankno() + "\n"
                                                    + "银行名称全称：" + abc.getBanknm() + "\n"
                                                    + "现汇卖出价：" + abc.getSeSell() + "\n"
                                                    + "现汇买入价：" + abc.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + abc.getCnSell()  + "\n"
                                                    + "现钞买入价：" + abc.getCnBuy() + "\n"
                                                    + "中间价：" + abc.getMiddle() + "\n"
                                                    + "数据更新时间：" + abc.getUpddate() + "\n" + "\n"

                                                    + "银行名称简称：" + ceb.getBankno() + "\n"
                                                    + "银行名称全称：" + ceb.getBanknm() + "\n"
                                                    + "现汇卖出价：" + ceb.getSeSell() + "\n"
                                                    + "现汇买入价：" + ceb.getSeBuy() + "\n"
                                                    + "现钞卖出价：" + ceb.getCnSell()  + "\n"
                                                    + "现钞买入价：" + ceb.getCnBuy() + "\n"
                                                    + "中间价：" + ceb.getMiddle() + "\n"
                                                    + "数据更新时间：" + ceb.getUpddate()
                                            );
                                        }else {
                                            Toast.makeText(mContext , "查询人民币和USD外汇牌价失败！" , Toast.LENGTH_LONG).show();
                                            mTv2.setText(AppConstance.NULL);
                                        }
                                    }

                                }else{
                                    Toast.makeText(mContext , "查询人民币外汇牌价失败！" , Toast.LENGTH_LONG).show();
                                    mTv2.setText(AppConstance.NULL);
                                }
                            }else{
                                Toast.makeText(mContext , "查询人民币外汇牌价失败！" , Toast.LENGTH_LONG).show();
                                mTv2.setText(AppConstance.NULL);
                            }
                        }
                    });
                }else{
                    Toast.makeText(mContext , "未输入内容，请重新输入！" , Toast.LENGTH_LONG).show();
                    mTv2.setText(AppConstance.NULL);
                }
                break;

            default:
                break;
        }
    }
}

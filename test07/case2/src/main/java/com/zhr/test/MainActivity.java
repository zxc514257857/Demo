package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhr.test.bean.CommercailInfoBean;
import com.zhr.test.bean.CommercialVolumeBean;
import com.zhr.test.bean.FanYiBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * 案例二：使用Retrofit 进行网络请求操作
 * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=tree  GET请求
 * https://nm.zhumei.net/index.php?g=Nm&m=MerchantScreenAppScaleNineteenMarch&a=index  POST请求
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10 , TimeUnit.SECONDS)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://fy.iciba.com/")
                        .client(okHttpClient)
                        .addConverterFactory(FastJsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<FanYiBean> call = apiService.getCall("tree");
                call.enqueue(new Callback<FanYiBean>() {
                    @Override
                    public void onResponse(Call<FanYiBean> call, Response<FanYiBean> response) {
                        Log.i(TAG, "onResponse1: ");
                        if(response.isSuccessful()){
                            List<String> word_mean = response.body().getContent().getWord_mean();
                            Log.i(TAG, "onResponse2: " + word_mean.toString());
                        }else {
                            Log.i(TAG, "onResponse3: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<FanYiBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });


                Map<String , String> map = new HashMap<>();
                map.put("a" , "fy");
                map.put("f" , "zh");
                map.put("t" , "en");
                map.put("w" , "tree");
                Call<FanYiBean> call1 = apiService.getCall(map);
                call1.enqueue(new Callback<FanYiBean>() {
                    @Override
                    public void onResponse(Call<FanYiBean> call, Response<FanYiBean> response) {
                        Log.i(TAG, "onResponse1: ");
                        if(response.isSuccessful()){
                            List<String> word_mean = response.body().getContent().getWord_mean();
                            Log.i(TAG, "onResponse2: " + word_mean.toString());
                        }else {
                            Log.i(TAG, "onResponse3: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<FanYiBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });


                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("https://nm.zhumei.net/")
                        .client(okHttpClient)
                        .addConverterFactory(FastJsonConverterFactory.create())
                        .build();

                ApiService apiService1 = retrofit1.create(ApiService.class);
                Call<CommercailInfoBean> commercialInfoCall = apiService1.getCommercialInfo("18786");
                commercialInfoCall.enqueue(new Callback<CommercailInfoBean>() {
                    @Override
                    public void onResponse(Call<CommercailInfoBean> call, Response<CommercailInfoBean> response) {
                        Log.i(TAG, "onResponse1: ");
                        if(response.isSuccessful()){
                            Log.i(TAG, "onResponse2: " + response.body().getTitle());
                        }else {
                            Log.i(TAG, "onResponse3: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<CommercailInfoBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });

                Map<String , String> map2 = new HashMap<>();
                map2.put("device_id" , "18786");
                map2.put("merchant_id" , "2001");
                Call<CommercialVolumeBean> commercialInfo = apiService1.getCommercialInfo(map2);
                commercialInfo.enqueue(new Callback<CommercialVolumeBean>() {
                    @Override
                    public void onResponse(Call<CommercialVolumeBean> call, Response<CommercialVolumeBean> response) {
                        Log.i(TAG, "onResponse1: ");
                        if(response.isSuccessful()){
                            Log.i(TAG, "onResponse2: " + response.body().getData());
                        }else {
                            Log.i(TAG, "onResponse3: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<CommercialVolumeBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });


                Call<FanYiBean> commercialInfo1 = apiService1.getCommercialInfo1("http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=tree");
                commercialInfo1.enqueue(new Callback<FanYiBean>() {
                    @Override
                    public void onResponse(Call<FanYiBean> call, Response<FanYiBean> response) {
                        Log.i(TAG, "onResponse11: ");
                        if(response.isSuccessful()){
                            List<String> word_mean = response.body().getContent().getWord_mean();
                            Log.i(TAG, "onResponse22: " + word_mean.toString());
                        }else {
                            Log.i(TAG, "onResponse33: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<FanYiBean> call, Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });
            }
        });
    }
}

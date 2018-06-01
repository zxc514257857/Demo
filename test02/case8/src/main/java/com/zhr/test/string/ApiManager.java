package com.zhr.test.string;

import com.zhr.test.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class ApiManager {

    private RetrofitService mRetrofitService;
    private static ApiManager instance;

    public static ApiManager getInstance(){
        if(instance == null){
            synchronized (ApiManager.class){
                if(instance == null){
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }

    /**
     * 私有构造方法 不能new 对象
     */
    private ApiManager() {}

    public RetrofitService getRetrofitService() {
        if(mRetrofitService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .build();

            mRetrofitService = retrofit.create(RetrofitService.class);
        }
        return mRetrofitService;
    }
}

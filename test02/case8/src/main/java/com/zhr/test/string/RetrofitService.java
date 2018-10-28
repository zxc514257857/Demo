package com.zhr.test.string;

import com.zhr.test.ApiConstants;
import com.zhr.test.bean.WanAndroidBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET(ApiConstants.PARAMS)
    Call<WanAndroidBean> rtfGetData();
//    Call<WanAndroidBean> rtfGetData(@Query("data") DataBean data);


//    @GET(ApiConstants.PARAMS)
//    Observable<WanAndroidBean> rxGetData();
}

package com.zhr.test.string.cache;

import com.zhr.test.bean.DataBean;
import com.zhr.test.bean.WanAndroidBean;
import com.zhr.test.string.ApiManager;
import com.zhr.test.string.OnRequestListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhr on 2018/5/29.
 * Located:zmkj
 * Des:网络缓存
 */
public class NetCache {

    private static final String TAG = NetCache.class.getSimpleName();
    private static NetCache instance;

    public static NetCache getInstance(){
        if(instance == null){
            synchronized (NetCache.class){
                if(instance == null){
                    instance = new NetCache();
                }
            }
        }
        return instance;
    }

    private NetCache(){}

    /**
     * 想要得到的数据
     * @param requestListener
     */
    public void getData(final OnRequestListener<DataBean> requestListener){
        ApiManager apiManager = ApiManager.getInstance();
        Call<WanAndroidBean> call = apiManager.getRetrofitService().rtfGetData();

        // 测试SP的追缴模式所写代码
//        final DataBean dataBean = new DataBean();
//        dataBean.setCurPage(1);
//        dataBean.setOffset(1);

        // 请求的Bean
        call.enqueue(new Callback<WanAndroidBean>() {
            @Override
            public void onResponse(Call<WanAndroidBean> call, Response<WanAndroidBean> response) {
                requestListener.onsuccess(response.body().getData());
//                requestListener.onsuccess(dataBean);
            }

            @Override
            public void onFailure(Call<WanAndroidBean> call, Throwable t) {
                requestListener.onFail(t.getMessage() , "获取数据失败！");
            }
        });
    }
}















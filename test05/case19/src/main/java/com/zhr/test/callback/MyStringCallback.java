package com.zhr.test.callback;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.bank.ResultBean;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

public abstract class MyStringCallback extends Callback<ResultBean> {

    /**
     * 这个方法的作用就是修改onResponse中的返回值
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResultBean parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        return JSON.parseObject(string, ResultBean.class);
    }
}

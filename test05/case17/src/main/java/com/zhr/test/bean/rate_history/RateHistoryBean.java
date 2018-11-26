package com.zhr.test.bean.rate_history;

import java.util.List;

public class RateHistoryBean {


    /**
     * success : 1
     * result : [{"curno":"CNY","days":"2015-12-12","rate":"6.4581","uptime":"2015-12-12 23:59:59"}]
     */

    private String success;
    private List<ResultBean> result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RateHistoryBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

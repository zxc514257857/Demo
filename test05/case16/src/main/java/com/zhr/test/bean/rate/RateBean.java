package com.zhr.test.bean.rate;

public class RateBean {

    /**
     * success : 1
     * result : {"status":"ALREADY","scur":"AED","tcur":"USD","ratenm":"阿联酋迪拉姆/美元","rate":"0.272279","update":"2018-11-10 07:00:24"}
     */
    private String success;
    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RateBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

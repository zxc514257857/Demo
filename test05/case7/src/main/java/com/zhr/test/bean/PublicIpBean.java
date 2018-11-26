package com.zhr.test.bean;

public class PublicIpBean {

    /**
     * success : 1
     * result : {"ip":"58.100.30.170","proxy":"1","att":"中国,浙江,杭州","operators":"未知"}
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
        return "PublicIpBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

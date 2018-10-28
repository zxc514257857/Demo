package com.zhr.test.bean;

public class IPBean {

    /**
     * success : 1
     * result : {"status":"OK","ip":"8.8.8.8","ip_str":"8.8.8.1","ip_end":"8.8.8.254","inet_ip":"134744072","inet_str":"134744065","inet_end":"134744318","areano":"","postno":"","operators":"未知","att":"美国","detailed":"美国","area_style_simcall":"美国","area_style_areanm":"美利坚合众国"}
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
        return "IPBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

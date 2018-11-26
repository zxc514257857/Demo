package com.zhr.test.bean;

public class IdcardBean {

    /**
     * success : 1
     * result : {"status":"ALREADY_ATT","par":"110101","idcard":"110101199001011114","born":"1990年01月01日","sex":"男","att":"北京市 东城区 ","postno":"100000","areano":"010","style_simcall":"中国,北京","style_citynm":"中华人民共和国,北京市"}
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
        return "IdcardBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

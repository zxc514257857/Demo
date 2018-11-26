package com.zhr.test.bean.area_code;

public class AreaCodeBean {

    /**
     * success : 1
     * result : {"count":"1","lists":[{"areaid":"242","postcode":"528400","areacode":"0760","areanm":"中华人民共和国,广东省,中山市","simcall":"中国,广东,中山"}]}
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
        return "AreaCodeBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

package com.zhr.test.bean;

public class HanziPinyinInterchangeBean {

    /**
     * success : 1
     * result : {"typeid":"1","wd":"需要转换的汉字","ret":"xu yao zhuai huan de han zi "}
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
        return "HanziPinyinInterchangeBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

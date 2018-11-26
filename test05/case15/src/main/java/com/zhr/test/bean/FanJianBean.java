package com.zhr.test.bean;

public class FanJianBean {

    /**
     * success : 1
     * result : {"typeid":"1","wd":"需要轉換的漢字","text":"需要轉換的漢字"}
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
        return "FanJianBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

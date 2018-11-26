package com.zhr.test.bean;

public class PunycodeBean {

    /**
     * success : 1
     * result : baidu
     */
    private String success;
    private String result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PunycodeBean{" +
                "success='" + success + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

package com.zhr.myapplication.bean;

public class ShortUrlBean {

    /**
     * success : 1
     * result : {"short_url":"http://k3v.cn/13lso3","keyid":"13lso3","source_url":"http://www.baidu.com","exits":"1"}
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
        return "ShortUrlBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

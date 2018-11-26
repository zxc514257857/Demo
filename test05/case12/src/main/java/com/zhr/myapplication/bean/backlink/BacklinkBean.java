package com.zhr.myapplication.bean.backlink;

public class BacklinkBean {

    /**
     * success : 1
     * result : {"status":"OK","webid":"146129276827631","website":"k780.com","backlink":"39"}
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
        return "BacklinkBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

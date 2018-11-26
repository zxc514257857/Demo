package com.zhr.test.bean.time;

public class TimeBean {

    /**
     * success : 1
     * result : {"timestamp":"1541401738","datetime_1":"2018-11-05 15:08:58","datetime_2":"2018年11月05日 15时08分58秒","week_1":"1","week_2":"星期一","week_3":"周一","week_4":"Monday"}
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
        return "TimeBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

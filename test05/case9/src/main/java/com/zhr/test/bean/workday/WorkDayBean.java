package com.zhr.test.bean.workday;

public class WorkDayBean {

    /**
     * success : 1
     * result : {"date":"2015-09-03","workmk":"2","worknm":"假日","week_1":"4","week_2":"星期四","week_3":"周四","week_4":"Thursday","remark":"纪念日：世界反法西斯战争胜利70周年纪念日."}
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
        return "WorkDayBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

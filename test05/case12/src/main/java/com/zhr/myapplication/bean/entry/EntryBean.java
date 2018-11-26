package com.zhr.myapplication.bean.entry;

public class EntryBean {

    /**
     * success : 1
     * result : {"status":"OK","webid":"145744923422581","website":"www.baidu.com","entry":"993000"}
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
        return "EntryBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

package com.zhr.myapplication.bean.alexa;

public class AlexaBean {

    /**
     * success : 1
     * result : {"status":"ALREADY_ALEXA","domain":"baidu.com","sitenm":"baidu.com","webmaster":"2009 baidu","email":"","entry_date":"","country":"cn china","lange":"","access_speed":"","speed_score":"","backlinks":"","contact_tel":"","country_rank":"1","global_rank":"4","trends":"0","dmoz":""}
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
        return "AlexaBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

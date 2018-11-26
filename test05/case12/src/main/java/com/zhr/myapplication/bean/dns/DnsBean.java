package com.zhr.myapplication.bean.dns;

public class DnsBean {

    /**
     * success : 1
     * result : {"domain":"baidu.com","dns_server":"223.5.5.5","name_server":"ns2.baidu.com,ns4.baidu.com,ns7.baidu.com,dns.baidu.com,ns3.baidu.com"}
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
        return "DnsBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

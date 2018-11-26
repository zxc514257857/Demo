package com.zhr.test.bean;

public class ResultBean {

    /**
     * ip : 58.100.30.170
     * proxy : 1
     * att : 中国,浙江,杭州
     * operators : 未知
     *
     * 公网ip 代理 公网ip所在地区 网络运营商
     */
    private String ip;
    private String proxy;
    private String att;
    private String operators;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "ip='" + ip + '\'' +
                ", proxy='" + proxy + '\'' +
                ", att='" + att + '\'' +
                ", operators='" + operators + '\'' +
                '}';
    }
}

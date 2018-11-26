package com.zhr.test.bean.rate_history;

public class ResultBean {

    /**
     * curno : CNY
     * days : 2015-12-12
     * rate : 6.4581
     * uptime : 2015-12-12 23:59:59
     *
     * 货币简称 查询日期 查询的汇率 更新时间
     */
    private String curno;
    private String days;
    private String rate;
    private String uptime;

    public String getCurno() {
        return curno;
    }

    public void setCurno(String curno) {
        this.curno = curno;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "curno='" + curno + '\'' +
                ", days='" + days + '\'' +
                ", rate='" + rate + '\'' +
                ", uptime='" + uptime + '\'' +
                '}';
    }
}

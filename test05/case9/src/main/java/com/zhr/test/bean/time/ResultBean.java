package com.zhr.test.bean.time;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * timestamp : 1541401738
     * datetime_1 : 2018-11-05 15:08:58
     * datetime_2 : 2018年11月05日 15时08分58秒
     * week_1 : 1
     * week_2 : 星期一
     * week_3 : 周一
     * week_4 : Monday
     *
     * 当前北京时间 中文星期 英文星期
     */
    private String timestamp;
    @JSONField(name = "datetime_1")
    private String datetime1;
    @JSONField(name = "datetime_2")
    private String datetime2;
    @JSONField(name = "week_1")
    private String week1;
    @JSONField(name = "week_2")
    private String week2;
    @JSONField(name = "week_3")
    private String week3;
    @JSONField(name = "week_4")
    private String week4;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDatetime1() {
        return datetime1;
    }

    public void setDatetime1(String datetime1) {
        this.datetime1 = datetime1;
    }

    public String getDatetime2() {
        return datetime2;
    }

    public void setDatetime2(String datetime2) {
        this.datetime2 = datetime2;
    }

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }

    public String getWeek2() {
        return week2;
    }

    public void setWeek2(String week2) {
        this.week2 = week2;
    }

    public String getWeek3() {
        return week3;
    }

    public void setWeek3(String week3) {
        this.week3 = week3;
    }

    public String getWeek4() {
        return week4;
    }

    public void setWeek4(String week4) {
        this.week4 = week4;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "timestamp='" + timestamp + '\'' +
                ", datetime1='" + datetime1 + '\'' +
                ", datetime2='" + datetime2 + '\'' +
                ", week1='" + week1 + '\'' +
                ", week2='" + week2 + '\'' +
                ", week3='" + week3 + '\'' +
                ", week4='" + week4 + '\'' +
                '}';
    }
}

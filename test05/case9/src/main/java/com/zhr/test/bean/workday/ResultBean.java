package com.zhr.test.bean.workday;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhr on 2018/11/5.
 * Located:zmkj
 * Des:
 */
public class ResultBean {

    /**
     * date : 2015-09-03
     * workmk : 2
     * worknm : 假日
     * week_1 : 4
     * week_2 : 星期四
     * week_3 : 周四
     * week_4 : Thursday
     * remark : 纪念日：世界反法西斯战争胜利70周年纪念日.
     *
     * 日期 是否假日 中文星期 英文星期 节日
     */
    private String date;
    private String workmk;
    private String worknm;
    @JSONField(name = "week_1")
    private String week1;
    @JSONField(name = "week_2")
    private String week2;
    @JSONField(name = "week_3")
    private String week3;
    @JSONField(name = "week_4")
    private String week4;
    private String remark;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkmk() {
        return workmk;
    }

    public void setWorkmk(String workmk) {
        this.workmk = workmk;
    }

    public String getWorknm() {
        return worknm;
    }

    public void setWorknm(String worknm) {
        this.worknm = worknm;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "date='" + date + '\'' +
                ", workmk='" + workmk + '\'' +
                ", worknm='" + worknm + '\'' +
                ", week1='" + week1 + '\'' +
                ", week2='" + week2 + '\'' +
                ", week3='" + week3 + '\'' +
                ", week4='" + week4 + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

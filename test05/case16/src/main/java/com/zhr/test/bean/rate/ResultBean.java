package com.zhr.test.bean.rate;

public class ResultBean {

    /**
     * status : ALREADY
     * scur : AED
     * tcur : USD
     * ratenm : 阿联酋迪拉姆/美元
     * rate : 0.272279
     * update : 2018-11-10 07:00:24
     *
     * 状态 源货币 目的货币 货币比率 比率 更新时间
     */
    private String status;
    private String scur;
    private String tcur;
    private String ratenm;
    private String rate;
    private String update;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScur() {
        return scur;
    }

    public void setScur(String scur) {
        this.scur = scur;
    }

    public String getTcur() {
        return tcur;
    }

    public void setTcur(String tcur) {
        this.tcur = tcur;
    }

    public String getRatenm() {
        return ratenm;
    }

    public void setRatenm(String ratenm) {
        this.ratenm = ratenm;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", scur='" + scur + '\'' +
                ", tcur='" + tcur + '\'' +
                ", ratenm='" + ratenm + '\'' +
                ", rate='" + rate + '\'' +
                ", update='" + update + '\'' +
                '}';
    }
}

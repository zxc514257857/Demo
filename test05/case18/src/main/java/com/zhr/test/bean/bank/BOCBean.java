package com.zhr.test.bean.bank;

import com.alibaba.fastjson.annotation.JSONField;

public class BOCBean {

    /**
     * bankno : BOC
     * banknm : 中国银行
     * se_sell : 698.6400
     * se_buy : 695.6900
     * cn_sell : 698.6400
     * cn_buy : 690.0300
     * middle : 694.7600
     * upddate : 2018-11-13 09:05:21
     *
     * 银行名称简称 银行名称全称 现汇卖出价 现汇买入价 现钞卖出价 现钞买入价 中间价 数据更新时间
     */
    private String bankno;
    private String banknm;
    @JSONField(name = "se_sell")
    private String seSell;
    @JSONField(name = "se_buy")
    private String seBuy;
    @JSONField(name = "cn_sell")
    private String cnSell;
    @JSONField(name = "cn_buy")
    private String cnBuy;
    private String middle;
    private String upddate;

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getBanknm() {
        return banknm;
    }

    public void setBanknm(String banknm) {
        this.banknm = banknm;
    }

    public String getSeSell() {
        return seSell;
    }

    public void setSeSell(String seSell) {
        this.seSell = seSell;
    }

    public String getSeBuy() {
        return seBuy;
    }

    public void setSeBuy(String seBuy) {
        this.seBuy = seBuy;
    }

    public String getCnSell() {
        return cnSell;
    }

    public void setCnSell(String cnSell) {
        this.cnSell = cnSell;
    }

    public String getCnBuy() {
        return cnBuy;
    }

    public void setCnBuy(String cnBuy) {
        this.cnBuy = cnBuy;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getUpddate() {
        return upddate;
    }

    public void setUpddate(String upddate) {
        this.upddate = upddate;
    }

    @Override
    public String toString() {
        return "BOCBean{" +
                "bankno='" + bankno + '\'' +
                ", banknm='" + banknm + '\'' +
                ", seSell='" + seSell + '\'' +
                ", seBuy='" + seBuy + '\'' +
                ", cnSell='" + cnSell + '\'' +
                ", cnBuy='" + cnBuy + '\'' +
                ", middle='" + middle + '\'' +
                ", upddate='" + upddate + '\'' +
                '}';
    }
}

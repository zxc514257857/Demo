package com.zhr.test.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * status : ALREADY_ATT
     * phone : 15527962751
     * area : 027
     * postno : 430000
     * att : 中国,湖北,武汉
     * ctype : 中国联通155卡
     * par : 1552796
     * prefix : 155
     * operators : 中国联通
     * style_simcall : 中国,湖北,武汉
     * style_citynm : 中华人民共和国,湖北省,武汉市
     * msg : 暂无相关归属地信息
     *
     * 执行状态 查询的手机号码 手机号码所在地区区号 所在地区邮编 手机号码归属地 号码卡类型 所有号码区间 运营商号段 所属运营商 简写归属地 完整归属地 出错消息,当出错时将会出现此节点,否则不出现
     */
    private String status;
    private String phone;
    private String area;
    private String postno;
    private String att;
    private String ctype;
    private String par;
    private String prefix;
    private String operators;
    @JSONField(name = "style_simcall")
    private String styleSimcall;
    @JSONField(name = "style_citynm")
    private String styleCitynm;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getStyleSimcall() {
        return styleSimcall;
    }

    public void setStyleSimcall(String styleSimcall) {
        this.styleSimcall = styleSimcall;
    }

    public String getStyleCitynm() {
        return styleCitynm;
    }

    public void setStyleCitynm(String styleCitynm) {
        this.styleCitynm = styleCitynm;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", area='" + area + '\'' +
                ", postno='" + postno + '\'' +
                ", att='" + att + '\'' +
                ", ctype='" + ctype + '\'' +
                ", par='" + par + '\'' +
                ", prefix='" + prefix + '\'' +
                ", operators='" + operators + '\'' +
                ", styleSimcall='" + styleSimcall + '\'' +
                ", styleCitynm='" + styleCitynm + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

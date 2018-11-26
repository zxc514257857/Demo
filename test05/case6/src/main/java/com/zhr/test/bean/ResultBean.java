package com.zhr.test.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * status : ALREADY_ATT
     * par : 110101
     * idcard : 110101199001011114
     * born : 1990年01月01日
     * sex : 男
     * att : 北京市 东城区
     * postno : 100000
     * areano : 010
     * style_simcall : 中国,北京
     * style_citynm : 中华人民共和国,北京市
     * msg : 身份证号码输入不正确
     *
     * 执行状态 行政区划代码 身份证号 出生日期 性别 身份证号码所在地区 邮编 区号 简写归属地 详写归属地 出错消息,当出错时将会出现此节点,否则不出现
     */
    private String status;
    private String par;
    private String idcard;
    private String born;
    private String sex;
    private String att;
    private String postno;
    private String areano;
    @JSONField(name = "style_simcall")
    private String styleSimcall;
    @JSONField(name = "style_citynm")
    private String styleCitynm;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getAreano() {
        return areano;
    }

    public void setAreano(String areano) {
        this.areano = areano;
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

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", par='" + par + '\'' +
                ", idcard='" + idcard + '\'' +
                ", born='" + born + '\'' +
                ", sex='" + sex + '\'' +
                ", att='" + att + '\'' +
                ", postno='" + postno + '\'' +
                ", areano='" + areano + '\'' +
                ", styleSimcall='" + styleSimcall + '\'' +
                ", styleCitynm='" + styleCitynm + '\'' +
                '}';
    }
}

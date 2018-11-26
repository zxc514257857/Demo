package com.zhr.test.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * status : OK
     * ip : 8.8.8.8
     * ip_str : 8.8.8.1
     * ip_end : 8.8.8.254
     * inet_ip : 134744072
     * inet_str : 134744065
     * inet_end : 134744318
     * areano :
     * postno :
     * operators : 未知
     * att : 美国
     * detailed : 美国
     * area_style_simcall : 美国
     * area_style_areanm : 美利坚合众国
     * msg : 无法识别的IP地址或域名
     *
     * 执行状态 查询的IP地址 IP地址段开始 IP地址段结束 数字格式IP地址 数字格式IP地址开始 数字格式IP地址结束 区号 邮编  运营商 详细信息 归属地 出错消息,当出错时将会出现此节点,否则不出现
     */
    private String status;
    private String ip;
    @JSONField(name = "ip_str")
    private String ipStart;
    @JSONField(name = "ip_end")
    private String ipEnd;
    @JSONField(name = "inet_ip")
    private String inetIP;
    @JSONField(name = "inet_str")
    private String inetStart;
    @JSONField(name = "inet_end")
    private String inetEnd;
    private String areano;
    private String postno;
    private String operators;
    private String att;
    private String detailed;
    @JSONField(name = "area_style_simcall")
    private String areaStyleSimcall;
    @JSONField(name = "area_style_areanm")
    private String areaStyleAreanm;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpStart() {
        return ipStart;
    }

    public void setIpStart(String ipStart) {
        this.ipStart = ipStart;
    }

    public String getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(String ipEnd) {
        this.ipEnd = ipEnd;
    }

    public String getInetIP() {
        return inetIP;
    }

    public void setInetIP(String inetIP) {
        this.inetIP = inetIP;
    }

    public String getInetStart() {
        return inetStart;
    }

    public void setInetStart(String inetStart) {
        this.inetStart = inetStart;
    }

    public String getInetEnd() {
        return inetEnd;
    }

    public void setInetEnd(String inetEnd) {
        this.inetEnd = inetEnd;
    }

    public String getAreano() {
        return areano;
    }

    public void setAreano(String areano) {
        this.areano = areano;
    }

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getAreaStyleSimcall() {
        return areaStyleSimcall;
    }

    public void setAreaStyleSimcall(String areaStyleSimcall) {
        this.areaStyleSimcall = areaStyleSimcall;
    }

    public String getAreaStyleAreanm() {
        return areaStyleAreanm;
    }

    public void setAreaStyleAreanm(String areaStyleAreanm) {
        this.areaStyleAreanm = areaStyleAreanm;
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
                ", ip='" + ip + '\'' +
                ", ipStart='" + ipStart + '\'' +
                ", ipEnd='" + ipEnd + '\'' +
                ", inetIP='" + inetIP + '\'' +
                ", inetStart='" + inetStart + '\'' +
                ", inetEnd='" + inetEnd + '\'' +
                ", areano='" + areano + '\'' +
                ", postno='" + postno + '\'' +
                ", operators='" + operators + '\'' +
                ", att='" + att + '\'' +
                ", detailed='" + detailed + '\'' +
                ", areaStyleSimcall='" + areaStyleSimcall + '\'' +
                ", areaStyleAreanm='" + areaStyleAreanm + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

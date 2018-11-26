package com.zhr.myapplication.bean.alexa;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * status : ALREADY_ALEXA
     * domain : baidu.com
     * sitenm : baidu.com
     * webmaster : 2009 baidu
     * email :
     * entry_date :
     * country : cn china
     * lange :
     * access_speed :
     * speed_score :
     * backlinks :
     * contact_tel :
     * country_rank : 1
     * global_rank : 4
     * trends : 0
     * dmoz :
     *
     * 执行结果 查询的域名 网站名称 网站站长 联系邮箱 收录日期 所属国家 语言 访问速度 速度评分 反向链接 联系电话 国内排名 全球排名 排名变化趋势
     */
    private String status;
    private String domain;
    private String sitenm;
    private String webmaster;
    private String email;
    @JSONField(name = "entry_date")
    private String entryDate;
    private String country;
    private String lange;
    @JSONField(name = "access_speed")
    private String accessSpeed;
    @JSONField(name = "speed_score")
    private String speedScore;
    private String backlinks;
    @JSONField(name = "contact_tel")
    private String contactTel;
    @JSONField(name = "country_rank")
    private String countryRank;
    @JSONField(name = "global_rank")
    private String globalRank;
    private String trends;
    private String dmoz;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSitenm() {
        return sitenm;
    }

    public void setSitenm(String sitenm) {
        this.sitenm = sitenm;
    }

    public String getWebmaster() {
        return webmaster;
    }

    public void setWebmaster(String webmaster) {
        this.webmaster = webmaster;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLange() {
        return lange;
    }

    public void setLange(String lange) {
        this.lange = lange;
    }

    public String getAccessSpeed() {
        return accessSpeed;
    }

    public void setAccessSpeed(String accessSpeed) {
        this.accessSpeed = accessSpeed;
    }

    public String getSpeedScore() {
        return speedScore;
    }

    public void setSpeedScore(String speedScore) {
        this.speedScore = speedScore;
    }

    public String getBacklinks() {
        return backlinks;
    }

    public void setBacklinks(String backlinks) {
        this.backlinks = backlinks;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(String countryRank) {
        this.countryRank = countryRank;
    }

    public String getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(String globalRank) {
        this.globalRank = globalRank;
    }

    public String getTrends() {
        return trends;
    }

    public void setTrends(String trends) {
        this.trends = trends;
    }

    public String getDmoz() {
        return dmoz;
    }

    public void setDmoz(String dmoz) {
        this.dmoz = dmoz;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", domain='" + domain + '\'' +
                ", sitenm='" + sitenm + '\'' +
                ", webmaster='" + webmaster + '\'' +
                ", email='" + email + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", country='" + country + '\'' +
                ", lange='" + lange + '\'' +
                ", accessSpeed='" + accessSpeed + '\'' +
                ", speedScore='" + speedScore + '\'' +
                ", backlinks='" + backlinks + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", countryRank='" + countryRank + '\'' +
                ", globalRank='" + globalRank + '\'' +
                ", trends='" + trends + '\'' +
                ", dmoz='" + dmoz + '\'' +
                '}';
    }
}

package com.zhr.myapplication.bean.backlink;

public class ResultBean {

    /**
     * status : OK
     * webid : 146129276827631
     * website : k780.com
     * backlink : 39
     *
     * 状态 网站编号 网站网址 反链数量
     */
    private String status;
    private String webid;
    private String website;
    private String backlink;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWebid() {
        return webid;
    }

    public void setWebid(String webid) {
        this.webid = webid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", webid='" + webid + '\'' +
                ", website='" + website + '\'' +
                ", backlink='" + backlink + '\'' +
                '}';
    }
}

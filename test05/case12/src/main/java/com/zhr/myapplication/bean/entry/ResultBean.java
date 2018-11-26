package com.zhr.myapplication.bean.entry;

public class ResultBean {

    /**
     * status : OK
     * webid : 145744923422581
     * website : www.baidu.com
     * entry : 993000
     *
     * 状态 网站编号 查询网站 收录数量
     */
    private String status;
    private String webid;
    private String website;
    private String entry;

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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", webid='" + webid + '\'' +
                ", website='" + website + '\'' +
                ", entry='" + entry + '\'' +
                '}';
    }
}

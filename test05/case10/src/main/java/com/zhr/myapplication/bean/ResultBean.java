package com.zhr.myapplication.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * short_url : http://k3v.cn/13lso3
     * keyid : 13lso3
     * source_url : http://www.baidu.com
     * exits : 1
     */
    @JSONField(name = "short_url")
    private String shortUrl;
    private String keyid;
    @JSONField(name = "source_url")
    private String sourceUrl;
    private String exits;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getExits() {
        return exits;
    }

    public void setExits(String exits) {
        this.exits = exits;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "shortUrl='" + shortUrl + '\'' +
                ", keyid='" + keyid + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", exits='" + exits + '\'' +
                '}';
    }
}

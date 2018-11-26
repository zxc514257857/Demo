package com.zhr.test.bean;

public class ResultBean {

    /**
     * typeid : 1
     * wd : 需要轉換的漢字
     * text : 需要轉換的漢字
     *
     * 简体 繁体
     */
    private String typeid;
    private String wd;
    private String text;

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "typeid='" + typeid + '\'' +
                ", wd='" + wd + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

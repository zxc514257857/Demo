package com.zhr.test.bean;

public class ResultBean {

    /**
     * typeid : 1
     * wd : 需要转换的汉字
     * ret : xu yao zhuai huan de han zi
     *
     * 类型 需要转换的汉字 拼音
     */
    private String typeid;
    private String wd;
    private String ret;

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

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "typeid='" + typeid + '\'' +
                ", wd='" + wd + '\'' +
                ", ret='" + ret + '\'' +
                '}';
    }
}

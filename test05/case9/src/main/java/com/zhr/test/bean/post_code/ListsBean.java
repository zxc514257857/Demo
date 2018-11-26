package com.zhr.test.bean.post_code;

public class ListsBean {

    /**
     * areaid : 242
     * postcode : 528400
     * areacode : 0760
     * areanm : 中华人民共和国,广东省,中山市
     * simcall : 中国,广东,中山
     *
     * 地区id 邮政编码 区号 地区名称 地区简称
     */
    private String areaid;
    private String postcode;
    private String areacode;
    private String areanm;
    private String simcall;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreanm() {
        return areanm;
    }

    public void setAreanm(String areanm) {
        this.areanm = areanm;
    }

    public String getSimcall() {
        return simcall;
    }

    public void setSimcall(String simcall) {
        this.simcall = simcall;
    }

    @Override
    public String toString() {
        return "ListsBean{" +
                "areaid='" + areaid + '\'' +
                ", postcode='" + postcode + '\'' +
                ", areacode='" + areacode + '\'' +
                ", areanm='" + areanm + '\'' +
                ", simcall='" + simcall + '\'' +
                '}';
    }
}

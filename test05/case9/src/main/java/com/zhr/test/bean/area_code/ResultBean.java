package com.zhr.test.bean.area_code;

import java.util.List;

public class ResultBean {

    /**
     * count : 1
     * lists : [{"areaid":"242","postcode":"528400","areacode":"0760","areanm":"中华人民共和国,广东省,中山市","simcall":"中国,广东,中山"}]
     */
    private String count;
    private List<ListsBean> lists;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "count='" + count + '\'' +
                ", lists=" + lists +
                '}';
    }
}

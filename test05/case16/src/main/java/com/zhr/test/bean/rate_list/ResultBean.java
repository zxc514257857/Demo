package com.zhr.test.bean.rate_list;

public class ResultBean {

    /**
     * curno : AED
     * curnm : 阿联酋迪拉姆
     *
     * 货币名称简写 货币名称中文
     */
    private String curno;
    private String curnm;

    public String getCurno() {
        return curno;
    }

    public void setCurno(String curno) {
        this.curno = curno;
    }

    public String getCurnm() {
        return curnm;
    }

    public void setCurnm(String curnm) {
        this.curnm = curnm;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "curno='" + curno + '\'' +
                ", curnm='" + curnm + '\'' +
                '}';
    }
}

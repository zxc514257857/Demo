package com.zhr.test.bean.coin;

import com.zhr.test.bean.bank.ABCBean;
import com.zhr.test.bean.bank.BOCBean;
import com.zhr.test.bean.bank.CCBBean;
import com.zhr.test.bean.bank.CEBBean;
import com.zhr.test.bean.bank.ICBCBean;

public class HKDBean {

    /**
     * BOC : {"bankno":"BOC","banknm":"中国银行","se_sell":"698.6400","se_buy":"695.6900","cn_sell":"698.6400","cn_buy":"690.0300","middle":"694.7600","upddate":"2018-11-13 09:05:21"}
     * CCB : {"bankno":"CCB","banknm":"建设银行","se_sell":"697.9100","se_buy":"694.8500","cn_sell":"697.9100","cn_buy":"690.0400","middle":"696.3100","upddate":"2018-11-13 10:30:04"}
     * ICBC : {"bankno":"ICBC","banknm":"工商银行","se_sell":"697.8600","se_buy":"694.9400","cn_sell":"697.8600","cn_buy":"689.3700","middle":"0.0000","upddate":"2018-11-13 10:31:31"}
     * ABC : {"bankno":"ABC","banknm":"农业银行","se_sell":"697.7900","se_buy":"695.0100","cn_sell":"0.0000","cn_buy":"689.4400","middle":"0.0000","upddate":"2018-11-13 10:25:32"}
     * CEB : {"bankno":"CEB","banknm":"光大银行","se_sell":"689.4756","se_buy":"695.3257","cn_sell":"698.1115","cn_buy":"698.1115","middle":"0.0000","upddate":"2018-11-13 10:34:00"}
     */
    private String curno;
    private String curnm;
    private BOCBean BOC;
    private CCBBean CCB;
    private ICBCBean ICBC;
    private ABCBean ABC;
    private CEBBean CEB;

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

    public BOCBean getBOC() {
        return BOC;
    }

    public void setBOC(BOCBean BOC) {
        this.BOC = BOC;
    }

    public CCBBean getCCB() {
        return CCB;
    }

    public void setCCB(CCBBean CCB) {
        this.CCB = CCB;
    }

    public ICBCBean getICBC() {
        return ICBC;
    }

    public void setICBC(ICBCBean ICBC) {
        this.ICBC = ICBC;
    }

    public ABCBean getABC() {
        return ABC;
    }

    public void setABC(ABCBean ABC) {
        this.ABC = ABC;
    }

    public CEBBean getCEB() {
        return CEB;
    }

    public void setCEB(CEBBean CEB) {
        this.CEB = CEB;
    }

    @Override
    public String toString() {
        return "USDBean{" +
                "curno='" + curno + '\'' +
                ", curnm='" + curnm + '\'' +
                ", BOC=" + BOC +
                ", CCB=" + CCB +
                ", ICBC=" + ICBC +
                ", ABC=" + ABC +
                ", CEB=" + CEB +
                '}';
    }
}

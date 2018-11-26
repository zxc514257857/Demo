package com.zhr.test.bean.bank;

public class CnyquotBean {

    /**
     * success : 1
     * result : {"USD":{"curno":"USD","curnm":"美元","BOC":{"bankno":"BOC","banknm":"中国银行","se_sell":"698.6400","se_buy":"695.6900","cn_sell":"698.6400","cn_buy":"690.0300","middle":"694.7600","upddate":"2018-11-13 09:05:21"},"CCB":{"bankno":"CCB","banknm":"建设银行","se_sell":"697.9100","se_buy":"694.8500","cn_sell":"697.9100","cn_buy":"690.0400","middle":"696.3100","upddate":"2018-11-13 10:30:04"},"ICBC":{"bankno":"ICBC","banknm":"工商银行","se_sell":"697.8600","se_buy":"694.9400","cn_sell":"697.8600","cn_buy":"689.3700","middle":"0.0000","upddate":"2018-11-13 10:31:31"},"ABC":{"bankno":"ABC","banknm":"农业银行","se_sell":"697.7900","se_buy":"695.0100","cn_sell":"0.0000","cn_buy":"689.4400","middle":"0.0000","upddate":"2018-11-13 10:25:32"},"CEB":{"bankno":"CEB","banknm":"光大银行","se_sell":"689.4756","se_buy":"695.3257","cn_sell":"698.1115","cn_buy":"698.1115","middle":"0.0000","upddate":"2018-11-13 10:34:00"}}}
     */
    private String success;
    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CnyquotBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

package com.zhr.test.bean;

public class PhoneBean {
    /**
     * success : 1
     * result : {"status":"ALREADY_ATT","phone":"15527962751","area":"027","postno":"430000","att":"中国,湖北,武汉","ctype":"中国联通155卡","par":"1552796","prefix":"155","operators":"中国联通","style_simcall":"中国,湖北,武汉","style_citynm":"中华人民共和国,湖北省,武汉市"}
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
        return "PhoneBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}

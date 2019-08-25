package com.zhr.test;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhr on 2019/3/3.
 * Located:zmkj
 * Des:电子秤实体类
 *
 * 1，在project的build.gradle添加
 * 2，在module的build.gradle添加
 * 3，创建实体类 然后重新构建代码 在实体类中生成get set方法等 在配置的目录下面生成dao文件(注意：如果使用int类型的自增id
 * 或者没加上实体类注解 会无法生成get set方法)
 */
@Entity
public class Scale {

    // 自增id
    @Id(autoincrement = true)
    private Long id;
    // 单价
    private double unitPrice;
    // 重量
    private double weightPcs;
    // 总价
    private double totPrice;
    // 交易单号
    private String tradeNo;
    // 秤号
    private String scaleNo;
    // 交易时间
    private long tradeTime;
    // 摊位号
    private String stallNum;
    // 交易方式
    private String payType;
    // 称重单位
    private String tradeUnit;
    public Scale(double unitPrice, double weightPcs, double totPrice,
            String tradeNo, String scaleNo, long tradeTime, String stallNum,
            String payType, String tradeUnit) {
        this.unitPrice = unitPrice;
        this.weightPcs = weightPcs;
        this.totPrice = totPrice;
        this.tradeNo = tradeNo;
        this.scaleNo = scaleNo;
        this.tradeTime = tradeTime;
        this.stallNum = stallNum;
        this.payType = payType;
        this.tradeUnit = tradeUnit;
    }
    @Generated(hash = 1068241852)
    public Scale() {
    }
    @Generated(hash = 570527997)
    public Scale(Long id, double unitPrice, double weightPcs, double totPrice,
            String tradeNo, String scaleNo, long tradeTime, String stallNum,
            String payType, String tradeUnit) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.weightPcs = weightPcs;
        this.totPrice = totPrice;
        this.tradeNo = tradeNo;
        this.scaleNo = scaleNo;
        this.tradeTime = tradeTime;
        this.stallNum = stallNum;
        this.payType = payType;
        this.tradeUnit = tradeUnit;
    }
    public double getUnitPrice() {
        return this.unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getWeightPcs() {
        return this.weightPcs;
    }
    public void setWeightPcs(double weightPcs) {
        this.weightPcs = weightPcs;
    }
    public double getTotPrice() {
        return this.totPrice;
    }
    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }
    public String getTradeNo() {
        return this.tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    public String getScaleNo() {
        return this.scaleNo;
    }
    public void setScaleNo(String scaleNo) {
        this.scaleNo = scaleNo;
    }
    public long getTradeTime() {
        return this.tradeTime;
    }
    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }
    public String getStallNum() {
        return this.stallNum;
    }
    public void setStallNum(String stallNum) {
        this.stallNum = stallNum;
    }
    public String getPayType() {
        return this.payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getTradeUnit() {
        return this.tradeUnit;
    }
    public void setTradeUnit(String tradeUnit) {
        this.tradeUnit = tradeUnit;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}











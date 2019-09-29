package com.platform.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author panchong
 * @Title: DiffOrderInfoVo
 * @Description: TODO
 * @date 2019/9/2716:49
 */
public class DiffOrderInfoVo extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 材料id
     */
    @ExcelProperty(value = {"订单号"}, index = 1)
    private String orderNo;

    @ExcelProperty(value = {"订单分类"}, index = 2)
    private Integer orderType;

    @ExcelProperty(value = {"订单金额"}, index = 3)
    private BigDecimal orderPrice;

    @ExcelProperty(value = {"实际支付"}, index = 4)
    private BigDecimal paymentPrice;

    @ExcelProperty(value = {"优惠金额"}, index = 5)
    private BigDecimal couponPrice;

    @ExcelProperty(value = {"支付渠道"}, index = 6)
    private String payChannel;

    @ExcelProperty(value = {"收款账号"}, index = 6)
    private String shroffAccountNumber;

    @ExcelProperty(value = {"进账流水号"}, index = 6)
    private String paymentNo;

    @ExcelProperty(value = {"门店名称"}, index = 6)
    private String storeName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getShroffAccountNumber() {
        return shroffAccountNumber;
    }

    public void setShroffAccountNumber(String shroffAccountNumber) {
        this.shroffAccountNumber = shroffAccountNumber;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

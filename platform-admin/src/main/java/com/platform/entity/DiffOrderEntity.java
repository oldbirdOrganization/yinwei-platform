package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiffOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //订单号
    private String orderNo;
    //订单分类
    private Integer orderType;
    //订单金额
    private BigDecimal orderPrice;
    //实际支付
    private BigDecimal paymentPrice;
    //优惠券ID
    private Integer couponId;
    //优惠金额
    private BigDecimal couponPrice;
    //支付方式 1线上支付 2线下支付
    private String payType;
    //门店id
    private Integer storeId;
    //支付渠道 1线上默认微信 2线下取数据
    private String payChannel;
    //收款账号
    private String shroffAccountNumber;
    //进账流水号
    private String paymentNo;
    //分类（维修、定制化加配等11个流程）
    private Integer channelId;

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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
}
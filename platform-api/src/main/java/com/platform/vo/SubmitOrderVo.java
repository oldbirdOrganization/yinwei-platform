package com.platform.vo;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class SubmitOrderVo implements java.io.Serializable{

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 渠道id
     */
    private Integer channelId;

    /**
     * 订单类型 1预约订单 2 支付订单
     */
    private Integer orderType;


    /**
     * 订单金额
     */
    private BigDecimal orderPrice;

    /***********************以下为预约单，支付单的内容******************************/

    /**
     * 服务小区名称
     */
    private String serviceHouseName;

    /**
     * 服务时间
     */
    private String serviceTime;

    /**
     * 服务要求 多个以逗号隔开
     */
    private String serviceRequired;

    /**
     * 问题描述
     */
    private String descriptionDescription;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 服务空间
     */
    private String serviceSpace;

    /**
     * 服务面积
     */
    private String serviceAcreage;

    /**
     * 服务方案
     */
    private String serviceIdea;

    /**
     * 空调型号
     */
    private String serviceAirConditionerModel;

    /**
     * 空调类型
     */
    private String serviceAirConditionerType;

    /**
     * 服务家具
     */
    private String serviceFurniture;

    /**
     * 房屋类型
     */
    private String serviceHouseType;

    /**
     * 交付标准
     */
    private String serviceHouseDeliveryStandards;


    /**
     * 图片列表
     */
    private List<ImgVo> imgVoList;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactMobile;
    /**
     * 地址
     */
    private String address;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public String getServiceHouseName() {
        return serviceHouseName;
    }

    public void setServiceHouseName(String serviceHouseName) {
        this.serviceHouseName = serviceHouseName;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceRequired() {
        return serviceRequired;
    }

    public void setServiceRequired(String serviceRequired) {
        this.serviceRequired = serviceRequired;
    }

    public String getDescriptionDescription() {
        return descriptionDescription;
    }

    public void setDescriptionDescription(String descriptionDescription) {
        this.descriptionDescription = descriptionDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceSpace() {
        return serviceSpace;
    }

    public void setServiceSpace(String serviceSpace) {
        this.serviceSpace = serviceSpace;
    }

    public String getServiceAcreage() {
        return serviceAcreage;
    }

    public void setServiceAcreage(String serviceAcreage) {
        this.serviceAcreage = serviceAcreage;
    }

    public String getServiceIdea() {
        return serviceIdea;
    }

    public void setServiceIdea(String serviceIdea) {
        this.serviceIdea = serviceIdea;
    }

    public String getServiceAirConditionerModel() {
        return serviceAirConditionerModel;
    }

    public void setServiceAirConditionerModel(String serviceAirConditionerModel) {
        this.serviceAirConditionerModel = serviceAirConditionerModel;
    }

    public String getServiceAirConditionerType() {
        return serviceAirConditionerType;
    }

    public void setServiceAirConditionerType(String serviceAirConditionerType) {
        this.serviceAirConditionerType = serviceAirConditionerType;
    }

    public String getServiceFurniture() {
        return serviceFurniture;
    }

    public void setServiceFurniture(String serviceFurniture) {
        this.serviceFurniture = serviceFurniture;
    }

    public String getServiceHouseType() {
        return serviceHouseType;
    }

    public void setServiceHouseType(String serviceHouseType) {
        this.serviceHouseType = serviceHouseType;
    }

    public String getServiceHouseDeliveryStandards() {
        return serviceHouseDeliveryStandards;
    }

    public void setServiceHouseDeliveryStandards(String serviceHouseDeliveryStandards) {
        this.serviceHouseDeliveryStandards = serviceHouseDeliveryStandards;
    }

    public List<ImgVo> getImgVoList() {
        return imgVoList;
    }

    public void setImgVoList(List<ImgVo> imgVoList) {
        this.imgVoList = imgVoList;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}

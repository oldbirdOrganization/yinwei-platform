package com.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "SubmitOrderVo" ,description= "下单请求参数")
public class SubmitOrderVo implements java.io.Serializable{

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品ID", example = "1")
    private Integer goodsId;

    /**
     * 渠道id
     */
    @ApiModelProperty(value = "渠道id 1-维修 2-改造 3-设计 4-定制化加配 5-装修 6	-空调维保 7-家具保养8-专业验房", example = "1")
    private Integer channelId;

    /**
     * 订单类型 1预约订单 2 支付订单
     */
    @ApiModelProperty(value = "订单类型 1-预约订单 2-支付订单", example = "1")
    private Integer orderType;


    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额(单位元)", example = "0.01")
    private BigDecimal orderPrice;

    /***********************以下为预约单，支付单的内容******************************/

    /**
     * 服务小区名称
     */
    @ApiModelProperty(value = "服务小区名称", example = "服务小区名称")
    private String serviceHouseName;

    /**
     * 服务时间
     */
    @ApiModelProperty(value = "服务时间", example = "")
    private String serviceTime;

    /**
     * 服务要求 多个以逗号隔开
     */
    @ApiModelProperty(value = "服务要求 多个以逗号隔开", example = "")
    private String serviceRequired;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述", example = "")
    private String problemDescription;

    /**
     * 服务类型
     */
    @ApiModelProperty(value = "服务类型", example = "")
    private String serviceType;

    /**
     * 服务空间
     */
    @ApiModelProperty(value = "服务空间", example = "")
    private String serviceSpace;

    /**
     * 服务面积
     */
    @ApiModelProperty(value = "服务面积", example = "")
    private String serviceAcreage;

    /**
     * 服务方案
     */
    @ApiModelProperty(value = "服务方案", example = "")
    private String serviceIdea;

    /**
     * 空调型号
     */
    @ApiModelProperty(value = "空调型号", example = "")
    private String serviceAirConditionerModel;

    /**
     * 空调类型
     */
    @ApiModelProperty(value = "空调类型", example = "")
    private String serviceAirConditionerType;

    /**
     * 服务家具
     */
    @ApiModelProperty(value = "服务家具", example = "")
    private String serviceFurniture;

    /**
     * 房屋类型
     */
    @ApiModelProperty(value = "房屋类型", example = "")
    private String serviceHouseType;

    /**
     * 交付标准
     */
    @ApiModelProperty(value = "交付标准", example = "")
    private String serviceHouseDeliveryStandards;


    /**
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表集合", example = "")
    private List<ImgVo> imgVoList;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", example = "")
    private String contactName;

    /**
     * 联系人手机
     */
    @ApiModelProperty(value = "联系人手机", example = "")
    private String contactMobile;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", example = "")
    private String address;


    //是否为第三方订单 1-是 0-否
    @ApiModelProperty(value = "是否为第三方订单 1-是 0-否", example = "0")
    private Integer isOuterOrder;
    //第三方服务商名称
    @ApiModelProperty(value = "第三方服务商名称", example = "")
    private String outerMerchantName;
    //第三方服务类
    @ApiModelProperty(value = "第三方服务类", example = "")
    private String outerServiceCategory;
    //第三方服务内容
    @ApiModelProperty(value = "第三方服务内容", example = "")
    private String outerServiceContent;
    //第三方服务品牌
    @ApiModelProperty(value = "第三方服务品牌", example = "")
    private String outerServiceBrand;
    //第三方服务套餐名称
    @ApiModelProperty(value = "第三方服务套餐名称", example = "")
    private String outerServiceCombo;


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

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
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

    public Integer getIsOuterOrder() {
        return isOuterOrder;
    }

    public void setIsOuterOrder(Integer isOuterOrder) {
        this.isOuterOrder = isOuterOrder;
    }

    public String getOuterMerchantName() {
        return outerMerchantName;
    }

    public void setOuterMerchantName(String outerMerchantName) {
        this.outerMerchantName = outerMerchantName;
    }

    public String getOuterServiceCategory() {
        return outerServiceCategory;
    }

    public void setOuterServiceCategory(String outerServiceCategory) {
        this.outerServiceCategory = outerServiceCategory;
    }

    public String getOuterServiceContent() {
        return outerServiceContent;
    }

    public void setOuterServiceContent(String outerServiceContent) {
        this.outerServiceContent = outerServiceContent;
    }

    public String getOuterServiceBrand() {
        return outerServiceBrand;
    }

    public void setOuterServiceBrand(String outerServiceBrand) {
        this.outerServiceBrand = outerServiceBrand;
    }

    public String getOuterServiceCombo() {
        return outerServiceCombo;
    }

    public void setOuterServiceCombo(String outerServiceCombo) {
        this.outerServiceCombo = outerServiceCombo;
    }
}

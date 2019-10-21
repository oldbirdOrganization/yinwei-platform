package com.platform.vo;


import com.platform.utils.excelutils.ExcelColumn;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 *
 * @author parkerwu
 * @email parkerwu@gmail.com
 * @date 2019-09-22 17:30:51
 */
@Data
public class OfflineOrderInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@ExcelColumn(value = "商品id", col = 1)
    private Integer goodsId;
	/**
	 *
	 */
	@ExcelColumn(value = "关联的线上订单号", col = 2)
    private String orderNo;
	/**
	 * 订单类型 1预约订单 2 支付订单
	 */
	@ExcelColumn(value = "订单类型 1预约订单 2 支付订单", col = 3)
	private Integer orderType;
	/**
	 * 订单状态
	 */
	@ExcelColumn(value = "订单状态", col = 4)
	private Integer orderStatus;
	/**
	 * 支付状态 1未支付 2 已支付
	 */
	@ExcelColumn(value = "支付状态 1未支付 2 已支付", col = 5)
	private Integer paymentStatus;
	/**
	 * 上游支付单号
	 */
	@ExcelColumn(value = "上游支付单号", col = 6)
	private String paymentNo;
	@ExcelColumn(value = "支付时间", col = 7)
	private String paymentTime;
	/**
	 * 订单金额
	 */
	@ExcelColumn(value = "订单金额", col = 8)
	private BigDecimal orderPrice;

	//使用的优惠券id
	@ExcelColumn(value = "使用的优惠券id", col = 9)
	private Integer couponId;

    //优惠价格
	@ExcelColumn(value = "优惠价格", col = 10)
	private BigDecimal couponPrice;

	/**
	 * 服务小区名称
	 */
	@ExcelColumn(value = "服务小区名称", col = 11)
	private String serviceHouseName;
	/**
	 * 服务时间
	 */
	@ExcelColumn(value = "服务时间", col = 12)
	private String serviceTime;
	/**
	 * 服务要求 多个以逗号隔开
	 */
	@ExcelColumn(value = "服务要求 多个以逗号隔开", col = 13)
	private String serviceRequired;
	/**
	 * 问题描述
	 */
	@ExcelColumn(value = "问题描述", col = 14)
	private String descriptionDescription;
	/**
	 * 服务类型
	 */
	@ExcelColumn(value = "服务类型", col = 15)
	private String serviceType;
	/**
	 * 服务空间
	 */
	@ExcelColumn(value = "服务空间", col = 16)
	private String serviceSpace;
	/**
	 * 服务面积
	 */
	@ExcelColumn(value = "服务面积", col = 17)
	private String serviceAcreage;
	/**
	 * 服务方案
	 */
	@ExcelColumn(value = "服务方案", col = 18)
	private String serviceIdea;
	/**
	 * 空调型号
	 */
	@ExcelColumn(value = "空调型号", col = 19)
	private String serviceAirConditionerModel;
	/**
	 * 空调类型
	 */
	@ExcelColumn(value = "空调类型", col = 20)
	private String serviceAirConditionerType;
	/**
	 * 服务家具
	 */
	@ExcelColumn(value = "服务家具", col = 21)
	private String serviceFurniture;
	/**
	 * 房屋类型
	 */
	@ExcelColumn(value = "房屋类型", col = 22)
	private String serviceHouseType;
	/**
	 * 交付标准
	 */
	@ExcelColumn(value = "交付标准", col = 23)
	private String serviceHouseDeliveryStandards;
	/**
	 * 联系人
	 */
	@ExcelColumn(value = "联系人", col = 24)
	private String contactName;
	/**
	 * 联系人手机
	 */
	@ExcelColumn(value = "联系人手机", col = 25)
	private String contactMobile;
	/**
	 * 地址
	 */
	@ExcelColumn(value = "地址", col = 26)
	private String address;


	/**
	 * 渠道id
	 */
	@ExcelColumn(value = "渠道id", col = 27)
	private Integer channelId;

	//渠道类型名称
	@ExcelColumn(value = "渠道类型名称", col = 28)
	private String channelName;
	/**
	 * 工人师傅
	 */
	@ExcelColumn(value = "工人师傅", col = 29)
	private String masterWorker;
	/**
	 * 工人师傅编号
	 */
	@ExcelColumn(value = "工人师傅编号", col = 30)
	private Long masterWorkerId;

	//是否为第三方订单 1-是 0-否
	@ExcelColumn(value = "是否为第三方订单 1-是 0-否", col = 31)
	private Integer isOuterOrder;
	//第三方服务商名称
	@ExcelColumn(value = "第三方服务商名称", col = 32)
	private String outerMerchantName;
	//第三方服务类
	@ExcelColumn(value = "第三方服务类", col = 33)
	private String outerServiceCategory;
	//第三方服务内容
	@ExcelColumn(value = "第三方服务内容", col = 34)
	private String outerServiceContent;
	//第三方服务品牌
	@ExcelColumn(value = "第三方服务品牌", col = 35)
	private String outerServiceBrand;
	//第三方服务套餐名称
	@ExcelColumn(value = "第三方服务套餐名称", col = 36)
	private String outerServiceCombo;

	//add by parker 实际支付金额
	@ExcelColumn(value = "实际支付金额", col = 37)
	private BigDecimal paymentPrice;

	//批次号
	@ExcelColumn(value = "批次号", col = 38)
	private String batchNo;


	private Integer parentOrderId;
	private String item;
	private String parentOrderNo;
	private BigDecimal totalAmount;	//订单总金额(单位：元)
	//已付金额
	private BigDecimal alreadyPayAmount;
	//剩余尾款金额
	private BigDecimal residuesPayAmount;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMasterWorker() {
		return masterWorker;
	}

	public void setMasterWorker(String masterWorker) {
		this.masterWorker = masterWorker;
	}

	public Long getMasterWorkerId() {
		return masterWorkerId;
	}

	public void setMasterWorkerId(Long masterWorkerId) {
		this.masterWorkerId = masterWorkerId;
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

	public BigDecimal getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(BigDecimal paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Integer parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getAlreadyPayAmount() {
		return alreadyPayAmount;
	}

	public void setAlreadyPayAmount(BigDecimal alreadyPayAmount) {
		this.alreadyPayAmount = alreadyPayAmount;
	}

	public BigDecimal getResiduesPayAmount() {
		return residuesPayAmount;
	}

	public void setResiduesPayAmount(BigDecimal residuesPayAmount) {
		this.residuesPayAmount = residuesPayAmount;
	}
}

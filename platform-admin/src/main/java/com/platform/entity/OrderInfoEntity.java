package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author oldbirdteam
 * @email oldbirdteam@gmail.com
 * @date 2019-08-17 17:30:51
 */
@TableName("nideshop_order_info")
public class OrderInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 
	 */
	private String orderNo;
	/**
	 * 订单类型 1预约订单 2 支付订单
	 */
	private Integer orderType;
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	/**
	 * 支付状态 1未支付 2 已支付
	 */
	private Integer paymentStatus;
	/**
	 * 上游支付单号
	 */
	private String paymentNo;
	
	private Date paymentTime;
	/**
	 * 实际支付金额
	 */
	private BigDecimal paymentPrice;

	/**
	 * 订单总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 订单金额
	 */
	private BigDecimal orderPrice;

	//使用的优惠券id
    private Integer couponId;
   
    //优惠价格
    private BigDecimal couponPrice;
	
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
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 创建人id
	 */
	private Long createUserId;
	
	private String userName;
	private String userMobile;
	/**
	 * 修改人id
	 */
	private Long updateUserId;

	//修改人
	private String updateName;

	/**
	 * 是否删除 0未删除 1删除
	 */
	private Integer defunct;

	/**
	 * 渠道id
	 */
	private Integer channelId;

	//渠道类型名称
	private String channelName;
	/**
	 * 工人师傅
	 */
	private String masterWorker;
	/**
	 * 工人师傅编号
	 */
	private Long masterWorkerId;

	//支付渠道
	private String payChannel;

	//收款账号
	private String shroffAccountNumber;
	//门店id
	private Long storeId;
	//门店名称
	private String storeName;

	//是否为第三方订单 1-是 0-否
	private Integer isOuterOrder;
	//第三方服务商名称
	private String outerMerchantName;
	//第三方服务类
	private String outerServiceCategory;
	//第三方服务内容
	private String outerServiceContent;
	//第三方服务品牌
	private String outerServiceBrand;
	//第三方服务套餐名称
	private String outerServiceCombo;

	//支付方式 1线上支付 2线下支付
	private String payType;
	private String storeAddress;
	private String storeContact;
	private String parentOrderId;
	private String item;
	private String parentOrderNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getDefunct() {
		return defunct;
	}

	public void setDefunct(Integer defunct) {
		this.defunct = defunct;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreContact() {
		return storeContact;
	}

	public void setStoreContact(String storeContact) {
		this.storeContact = storeContact;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(String parentOrderId) {
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
}

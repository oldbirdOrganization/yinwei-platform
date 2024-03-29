package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author oldbirdteam
 * @email oldbirdteam@gmail.com
 * @date 2019-08-17 17:30:51
 */
@TableName("nideshop_order_info")
public class NideshopOrderInfoEntity implements Serializable {
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
	 * 渠道id
	 */
	private Integer channelId;
	/**
	 * 
	 */
	private String orderNo;
	/**
	 * 订单类型 1预约订单 2 支付订单
	 */
	private Integer orderType;
	/**
	 * 订单状态 1 下单成功（待指派） 2待确认 3已确认 4完成服务 5作废 6待评价
	 */
	private Integer orderStatus;
	/**
	 * 支付状态 1未支付 2 已支付
	 */
	private Integer paymentStatus;

	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 上游支付单号
	 */
	private String paymentNo;

	/**
	 * 实际支付金额
	 */
	private BigDecimal  paymentPrice;
	/**
	 * 订单金额
	 */
	private BigDecimal orderPrice;
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
	private String problemDescription;
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
	/**
	 * 修改人id
	 */
	private Long updateUserId;
	/**
	 * 是否删除 0未删除 1删除
	 */
	private Integer defunct;

	/**
	 * 优惠券Id
	 */
	private Integer couponId;

	/**
	 * 优惠金额
	 */
	private BigDecimal couponPrice ;

	/**
	 * 工人师傅编号
	 */
	private Long masterWorkerId;

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


	private Integer parentOrderId;
	private String item;//进账科目 1定金 2进度款 3尾款


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

	public String getProblemDescription() {
		return problemDescription;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
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

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
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

	public Long getMasterWorkerId() {
		return masterWorkerId;
	}

	public void setMasterWorkerId(Long masterWorkerId) {
		this.masterWorkerId = masterWorkerId;
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

}

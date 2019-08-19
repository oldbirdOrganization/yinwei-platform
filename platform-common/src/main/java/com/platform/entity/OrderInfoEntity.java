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
	private String servicehousename;
	/**
	 * 服务时间
	 */
	private String servicetime;
	/**
	 * 服务要求 多个以逗号隔开
	 */
	private String servicerequired;
	/**
	 * 问题描述
	 */
	private String descriptiondescription;
	/**
	 * 服务类型
	 */
	private String servicetype;
	/**
	 * 服务空间
	 */
	private String servicespace;
	/**
	 * 服务面积
	 */
	private String serviceacreage;
	/**
	 * 服务方案
	 */
	private String serviceidea;
	/**
	 * 空调型号
	 */
	private String serviceairconditionermodel;
	/**
	 * 空调类型
	 */
	private String serviceairconditionertype;
	/**
	 * 服务家具
	 */
	private String servicefurniture;
	/**
	 * 房屋类型
	 */
	private String servicehousetype;
	/**
	 * 交付标准
	 */
	private String servicehousedeliverystandards;
	/**
	 * 联系人
	 */
	private String contactname;
	/**
	 * 联系人手机
	 */
	private String contactmobile;
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
	/**
	 * 修改人id
	 */
	private Long updateUserId;
	
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

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

	public String getServicehousename() {
		return servicehousename;
	}

	public void setServicehousename(String servicehousename) {
		this.servicehousename = servicehousename;
	}

	public String getServicetime() {
		return servicetime;
	}

	public void setServicetime(String servicetime) {
		this.servicetime = servicetime;
	}

	public String getServicerequired() {
		return servicerequired;
	}

	public void setServicerequired(String servicerequired) {
		this.servicerequired = servicerequired;
	}

	public String getDescriptiondescription() {
		return descriptiondescription;
	}

	public void setDescriptiondescription(String descriptiondescription) {
		this.descriptiondescription = descriptiondescription;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getServicespace() {
		return servicespace;
	}

	public void setServicespace(String servicespace) {
		this.servicespace = servicespace;
	}

	public String getServiceacreage() {
		return serviceacreage;
	}

	public void setServiceacreage(String serviceacreage) {
		this.serviceacreage = serviceacreage;
	}

	public String getServiceidea() {
		return serviceidea;
	}

	public void setServiceidea(String serviceidea) {
		this.serviceidea = serviceidea;
	}

	public String getServiceairconditionermodel() {
		return serviceairconditionermodel;
	}

	public void setServiceairconditionermodel(String serviceairconditionermodel) {
		this.serviceairconditionermodel = serviceairconditionermodel;
	}

	public String getServiceairconditionertype() {
		return serviceairconditionertype;
	}

	public void setServiceairconditionertype(String serviceairconditionertype) {
		this.serviceairconditionertype = serviceairconditionertype;
	}

	public String getServicefurniture() {
		return servicefurniture;
	}

	public void setServicefurniture(String servicefurniture) {
		this.servicefurniture = servicefurniture;
	}

	public String getServicehousetype() {
		return servicehousetype;
	}

	public void setServicehousetype(String servicehousetype) {
		this.servicehousetype = servicehousetype;
	}

	public String getServicehousedeliverystandards() {
		return servicehousedeliverystandards;
	}

	public void setServicehousedeliverystandards(String servicehousedeliverystandards) {
		this.servicehousedeliverystandards = servicehousedeliverystandards;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactmobile() {
		return contactmobile;
	}

	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}

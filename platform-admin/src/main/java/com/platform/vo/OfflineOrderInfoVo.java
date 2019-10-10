package com.platform.vo;


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
	private String paymentTime;
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

	//add by parker 实际支付金额
	private BigDecimal paymentPrice;

	//批次号
	private String batchNo;
}

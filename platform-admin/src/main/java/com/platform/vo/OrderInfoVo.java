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
public class OrderInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@ExcelColumn(value = "商品id", col = 1)
    private Integer goodsId;
	/**
	 *
	 */
	@ExcelColumn(value = "订单号", col = 2)
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

	//支付方式 1线上支付 2线下支付
	private String payType;
	private String storeName;
	private String storeAddress;
	private String storeContact;
}

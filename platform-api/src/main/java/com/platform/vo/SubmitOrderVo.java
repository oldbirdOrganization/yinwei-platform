package com.platform.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel
@Data
public class SubmitOrderVo implements java.io.Serializable{

    /**
     * 商品id
     */
    private Integer goodsId;

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


}

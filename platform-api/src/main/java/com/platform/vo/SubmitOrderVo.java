package com.platform.vo;

import lombok.Data;

@Data
public class SubmitOrderVo implements java.io.Serializable{

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 地址id
     */
    private Integer addressId;


}

package com.platform.dao;

import com.platform.entity.OrderVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-11 09:16:46
 */
public interface ApiOrderMapper extends BaseDao<OrderVo> {

    /**
     * 根据订单编号查询订单
     *
     * @param order_sn
     * @return
     */
    OrderVo queryObjectByOrderSn(@Param("orderSn") String order_sn);
}

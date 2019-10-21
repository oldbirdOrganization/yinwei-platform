package com.platform.dao;

import com.platform.entity.OrderInfoEntity;
import com.platform.vo.OrderStatusCountVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
public interface OrderInfoDao extends BaseDao<OrderInfoEntity> {

    OrderInfoEntity selectBySelective(OrderInfoEntity record);

    OrderStatusCountVo countByStatus(@Param("orderType") String orderType);
}

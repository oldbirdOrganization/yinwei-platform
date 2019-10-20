package com.platform.dao;

import com.platform.entity.OrderInfoEntity;

/**
 * 
 * 
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
public interface OrderInfoDao extends BaseDao<OrderInfoEntity> {

    OrderInfoEntity selectBySelective(OrderInfoEntity record);

}

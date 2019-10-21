package com.platform.service;

import com.platform.entity.OrderInfoEntity;
import com.platform.vo.OrderStatusCountVo;

import java.util.List;
import java.util.Map;

/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
public interface OrderInfoService {

    OrderInfoEntity queryObject(Integer id);

    List<OrderInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(OrderInfoEntity order);

    int update(OrderInfoEntity order);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);

    /**
     * 订单作废
     *
     * @param id
     * @return
     */
    int cancelOrder(Integer id);

    /**
     * 订单指派
     */

    int dispatchOrder(OrderInfoEntity order);

    OrderInfoEntity selectBySelective(OrderInfoEntity order);

    OrderStatusCountVo countByStatus(String orderType);
}

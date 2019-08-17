package com.platform.service;

import com.platform.entity.OrderEntity;
import com.platform.entity.OrderInfoEntity;

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
     * 确定收货
     *
     * @param id
     * @return
     */
    int confirm(Integer id);

}

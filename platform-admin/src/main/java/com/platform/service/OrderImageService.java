package com.platform.service;

import com.platform.entity.OrderImageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
public interface OrderImageService {

    OrderImageEntity queryObject(Integer id);

    List<OrderImageEntity> queryList(Map<String, Object> map);

    int save(OrderImageEntity order);

    int update(OrderImageEntity order);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);


}

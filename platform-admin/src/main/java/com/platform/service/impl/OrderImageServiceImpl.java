package com.platform.service.impl;

import com.platform.dao.OrderImageDao;
import com.platform.entity.OrderImageEntity;
import com.platform.service.OrderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderImageService")
public class OrderImageServiceImpl implements OrderImageService {
    @Autowired
    private OrderImageDao orderImageDao;

    @Override
    public OrderImageEntity queryObject(Integer id) {
        return orderImageDao.queryObject(id);
    }

    @Override
    public List<OrderImageEntity> queryList(Map<String, Object> map) {
        return orderImageDao.queryList(map);
    }

    @Override
    public int save(OrderImageEntity order) {
        return orderImageDao.save(order);
    }

    @Override
    public int update(OrderImageEntity order) {
        return orderImageDao.update(order);
    }

    @Override
    public int delete(Integer id) {
        return orderImageDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return orderImageDao.deleteBatch(ids);
    }

}

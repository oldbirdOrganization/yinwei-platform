package com.platform.service.impl;

import com.platform.dao.OrderInfoDao;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;
//    @Autowired
//    private ShippingDao shippingDao;

    @Override
    public OrderInfoEntity queryObject(Integer id) {
        return orderInfoDao.queryObject(id);
    }

    @Override
    public List<OrderInfoEntity> queryList(Map<String, Object> map) {
        return orderInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderInfoDao.queryTotal(map);
    }

    @Override
    public int save(OrderInfoEntity order) {
        return orderInfoDao.save(order);
    }

    @Override
    public int update(OrderInfoEntity order) {
        return orderInfoDao.update(order);
    }

    @Override
    public int delete(Integer id) {
        return orderInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return orderInfoDao.deleteBatch(ids);
    }

    @Override
    public int confirm(Integer id) {
        /*OrderInfoEntity orderEntity = queryObject(id);
        Integer shippingStatus = orderEntity.getShippingStatus();//发货状态
        Integer payStatus = orderEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new RRException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new RRException("此订单未发货，不能确认收货！");
        }
        orderEntity.setShippingStatus(2);
        orderEntity.setOrderStatus(301);*/
//        return orderDao.update(orderEntity);
        return 0;
    }


}

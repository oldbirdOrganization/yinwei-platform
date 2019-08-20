package com.platform.service.impl;

import com.platform.dao.OrderInfoDao;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.OrderInfoService;
import com.platform.utils.RRException;
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
    public int cancelOrder(Integer id) {
        OrderInfoEntity orderEntity = queryObject(id);
        Integer orderStatus = orderEntity.getOrderStatus();//订单状态
        Integer payStatus = orderEntity.getPaymentStatus();//付款状态

        if (2 == payStatus) {
            throw new RRException("此订单未已付款，不能作废！");
        }
        if (3 == orderStatus) {
            throw new RRException("此订单处于施工中，不能作废！");
        }
        if (4 == orderStatus) {
            throw new RRException("此订单已完成服务，不能作废！");
        }
        orderEntity.setOrderStatus(5);
        return orderInfoDao.update(orderEntity);
    }

    @Override
    public int dispatchOrder(OrderInfoEntity order) {
//        Integer payStatus = order.getPayStatus();//付款状态
//        if (2 != payStatus) {
//            throw new RRException("此订单未付款！");
//        }
//
//        ShippingEntity shippingEntity = shippingDao.queryObject(order.getShippingId());
//        if (null != shippingEntity) {
//            order.setShippingName(shippingEntity.getName());
//        }
//        order.setOrderStatus(300);//订单已发货
//        order.setShippingStatus(1);//已发货
//        return orderDao.update(order);


        return 0;
    }
}

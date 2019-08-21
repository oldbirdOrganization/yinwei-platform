package com.platform.service.impl;

import com.platform.dao.OrderInfoDao;
import com.platform.entity.OrderInfoEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.OrderInfoService;
import com.platform.utils.Constant;
import com.platform.utils.RRException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            throw new RRException("此订单已付款，不能作废！");
        }
        if (3 == orderStatus) {
            throw new RRException("此订单处于施工中，不能作废！");
        }
        if (4 == orderStatus || 6 == orderStatus) {
            throw new RRException("此订单已完成服务，不能作废！");
        }
        if (5 == payStatus) {
            throw new RRException("此订单已作废，勿重复操作！");
        }

        orderEntity.setOrderStatus(5);
        orderEntity.setUpdateTime(new Date());
        SysUserEntity sysUser = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute(Constant.CURRENT_USER);
        if (sysUser != null) {
            orderEntity.setUpdateUserId(sysUser.getUserId());
        }
        return orderInfoDao.update(orderEntity);
    }

    @Override
    public int dispatchOrder(OrderInfoEntity order) {
        Integer orderStatus = order.getOrderStatus();//订单状态
        Integer payStatus = order.getPaymentStatus();//付款状态
        if(1 != orderStatus){
            throw new RRException("此订单状态无法指派装修师傅");
        }
        if(2 == order.getOrderType()){//定金订单
            if (2 != payStatus) {
                throw new RRException("此订单未付款，无法指派装修师傅");
            }
        }
        order.setOrderStatus(2);//订单待确认
        order.setUpdateTime(new Date());
        SysUserEntity sysUser = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute(Constant.CURRENT_USER);
        if (sysUser != null) {
            order.setUpdateUserId(sysUser.getUserId());
        }
        return orderInfoDao.update(order);
    }
}

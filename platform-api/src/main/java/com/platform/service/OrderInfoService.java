package com.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.dao.NideshopOrderInfoDao;
import com.platform.entity.NideshopOrderInfoEntity;
import com.platform.entity.UserVo;
import com.platform.vo.SubmitOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoService {

    @Autowired
    private NideshopOrderInfoDao nideshopOrderInfoDao;


    /**
     * 下单
     * @param user
     * @param submitOrderVo
     * @return
     */
    public Integer submitOrder(UserVo user, SubmitOrderVo submitOrderVo) {
        NideshopOrderInfoEntity model = new NideshopOrderInfoEntity();
        BeanUtils.copyProperties(submitOrderVo,model);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        model.setOrderNo(fmt.format(new Date()));
        model.setPaymentStatus(1);//未支付
        model.setOrderStatus(1);//下单成功
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setDefunct(0);
        model.setCreateUserId(user.getUserId());
        model.setUpdateUserId(user.getUserId());
        nideshopOrderInfoDao.insert(model);
        return model.getId();
    }

    /**
     * 订单列表
     * 待指派订单：查询orderStatus=1
     * 待确认订单：查询orderStatus=2
     * 已完成订单，查询orderStatus=4
     * @param user
     * @param orderType 订单类型 1预约订单 2支付订单
     * @param orderStatus 订单状态 参照实体
     * @param payementStatus 支付状态 1未支付  2已支付
     * @return
     */
    public List<NideshopOrderInfoEntity> findOrderList(UserVo user,Integer orderType,Integer orderStatus,Integer payementStatus){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("create_user_id",user.getUserId());
        if(orderType != null){
            columnMap.put("order_type",orderType);
        }
        if(orderStatus != null) {
            columnMap.put("order_status", orderStatus);
        }
        if(payementStatus != null) {
            columnMap.put("payment_status", payementStatus);
        }
        return nideshopOrderInfoDao.selectByMap(columnMap);
    }

    /**
     * 待付款订单列表
     * 待付款订单：查询orderType=2 ，paymentStatus=1 ，orderStatus!=5
     * @param user
     * @return
     */
    public List<NideshopOrderInfoEntity> findPaymentingOrderList(UserVo user){
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("create_user_id",user.getUserId());
        wrapper.eq("order_type",2);
        wrapper.ne("order_status",5);
        wrapper.eq("payment_status",1);
        return nideshopOrderInfoDao.selectList(wrapper);
    }

    public NideshopOrderInfoEntity findDetail(UserVo user,Integer orderId){
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("create_user_id",user.getUserId());
        wrapper.eq("id",orderId);
        return nideshopOrderInfoDao.selectOne(wrapper);
    }
}



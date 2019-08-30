package com.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.dao.NideshopOrderImageDao;
import com.platform.dao.NideshopOrderInfoDao;
import com.platform.entity.NideshopOrderImageEntity;
import com.platform.entity.NideshopOrderInfoEntity;
import com.platform.entity.UserVo;
import com.platform.vo.ImgVo;
import com.platform.vo.SubmitOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ApiOrderInfoService {

    private static Logger logger= LoggerFactory.getLogger(ApiOrderInfoService.class);

    @Autowired
    private NideshopOrderInfoDao nideshopOrderInfoDao;
    @Autowired
    private NideshopOrderImageDao nideshopOrderImageDao;


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

        //保存订单图片
        if(CollectionUtils.isNotEmpty(submitOrderVo.getImgVoList())){
            for(ImgVo imgVo : submitOrderVo.getImgVoList()) {
                NideshopOrderImageEntity imageEntity = new NideshopOrderImageEntity();
                imageEntity.setOrderId(model.getId());
                imageEntity.setUrl(imgVo.getUrl());
                imageEntity.setCreateTime(new Date());
                imageEntity.setCreateUserId(user.getUserId());
                imageEntity.setUpdateTime(new Date());
                nideshopOrderImageDao.insert(imageEntity);
            }
        }
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

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    public NideshopOrderInfoEntity findDetail(Integer orderId){
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("id",orderId);
        return nideshopOrderInfoDao.selectOne(wrapper);
    }

    /**
     * 根据订单号
     * @param orderNo
     * @return
     */
    public NideshopOrderInfoEntity findByOrderNo(String orderNo){
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("order_no",orderNo);
        return nideshopOrderInfoDao.selectOne(wrapper);
    }

    /**
     * 修改订单
     * @param order
     */
    public void update(NideshopOrderInfoEntity order){
        order.setUpdateTime(new Date());
        nideshopOrderInfoDao.updateById(order);
    }

    /**
     * 一小时未支付的订单（支付订单）
     * @return
     */
    private List<NideshopOrderInfoEntity> findNotPayment(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,-1);
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("order_type",2);
        wrapper.ne("payment_status",2);
        wrapper.le("create_time",calendar.getTime());
        return nideshopOrderInfoDao.selectList(wrapper);
    }

    /**
     * 24小时未指派的订单（预约订单）
     * @return
     */
    private List<NideshopOrderInfoEntity> findNotDesignate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,-24);
        QueryWrapper<NideshopOrderInfoEntity> wrapper = new QueryWrapper();
        wrapper.eq("order_type",1);
        wrapper.eq("order_status",1);
        wrapper.le("create_time",calendar.getTime());
        return nideshopOrderInfoDao.selectList(wrapper);
    }

    /**
     * 查询订单图片
     * @param orderId
     * @return
     */
    public List<NideshopOrderImageEntity> findOrderImageList(Integer orderId){
        QueryWrapper<NideshopOrderImageEntity> wrapper = new QueryWrapper();
        wrapper.eq("order_id",orderId);
        return nideshopOrderImageDao.selectList(wrapper);
    }


    /**
     * 定时作废订单
     */
    public void doRun(){
        logger.info("@@ApiOrderInfoService.doRun  order job begin");
        List<NideshopOrderInfoEntity> list1 = findNotPayment();
        for(NideshopOrderInfoEntity m1 : list1){
            m1.setOrderStatus(5);
            nideshopOrderInfoDao.updateById(m1);
        }
        List<NideshopOrderInfoEntity> list2 = findNotDesignate();
        for(NideshopOrderInfoEntity m2 : list2){
            m2.setOrderStatus(5);
            nideshopOrderInfoDao.updateById(m2);
        }
        logger.info("@@ApiOrderInfoService.doRun  order job end");
    }
}



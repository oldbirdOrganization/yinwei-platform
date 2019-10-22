package com.platform.service.impl;

import com.alibaba.excel.metadata.Sheet;
import com.platform.dao.OrderInfoDao;
import com.platform.entity.DiffOrderEntity;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.DiffOrderService;
import com.platform.service.OfflineOrderService;
import com.platform.service.OrderInfoService;
import com.platform.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service("diffOrderServer")
public class DiffOrderServiceImpl implements DiffOrderService {

    @Autowired
    private OfflineOrderService offlineOrderService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public int queryTotal(Map<String, Object> map){
        Query query = new Query(map);
        String payType = (String)map.get("payType");
        if("1".equals(payType)){
            return orderInfoService.queryTotal(map);
        }else if("2".equals(payType)){
            return offlineOrderService.queryTotal(query);
        }else{
            return orderInfoService.queryTotal(map)+offlineOrderService.queryTotal(query);
        }
    }

    @Override
    public DiffOrderEntity queryObject(Long id) {
        return null;
    }

    @Override
    public List<DiffOrderEntity> queryList(Map<String, Object> map) {
        Query query = new Query(map);
        List<DiffOrderEntity> dataList;
        String payType = (String)map.get("payType");
        List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(map);
        if("1".equals(payType)){
            dataList = orderInfoList.stream().map(orderInfoEntity -> {
                        DiffOrderEntity diffOrderEntity = new DiffOrderEntity();
                        diffOrderEntity.setOrderType(orderInfoEntity.getOrderType());
                        diffOrderEntity.setOrderNo(orderInfoEntity.getOrderNo());
                        diffOrderEntity.setChannelId(orderInfoEntity.getChannelId());
                        diffOrderEntity.setCouponId(orderInfoEntity.getCouponId());
                        diffOrderEntity.setCouponPrice(orderInfoEntity.getCouponPrice());
                        diffOrderEntity.setPaymentNo(orderInfoEntity.getPaymentNo());
                        diffOrderEntity.setOrderPrice(orderInfoEntity.getOrderPrice());
                        diffOrderEntity.setPaymentPrice(orderInfoEntity.getOrderPrice());
                        diffOrderEntity.setPayChannel("微信支付");
                        diffOrderEntity.setPayType("1");
                        diffOrderEntity.setShroffAccountNumber(orderInfoEntity.getShroffAccountNumber());
                        diffOrderEntity.setStoreId(orderInfoEntity.getStoreId());
                        diffOrderEntity.setPaymentPrice(orderInfoEntity.getPaymentPrice());
                        diffOrderEntity.setStoreName(orderInfoEntity.getStoreName());
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());
        }else if("2".equals(payType)){
            List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryListByMap(query);
            dataList = offlineOrderList.stream().map(offlineOrderInfoPo -> {
                        DiffOrderEntity diffOrderEntity = new DiffOrderEntity();
                        diffOrderEntity.setOrderType(offlineOrderInfoPo.getOrderType());
                        diffOrderEntity.setOrderNo(offlineOrderInfoPo.getOrderNo());
                        diffOrderEntity.setChannelId(offlineOrderInfoPo.getChannelId());
                        diffOrderEntity.setCouponId(offlineOrderInfoPo.getCouponId());
                        diffOrderEntity.setCouponPrice(offlineOrderInfoPo.getCouponPrice());
                        diffOrderEntity.setPaymentNo(offlineOrderInfoPo.getPaymentNo());
                        diffOrderEntity.setOrderPrice(offlineOrderInfoPo.getOrderPrice());
                        diffOrderEntity.setPaymentPrice(offlineOrderInfoPo.getOrderPrice());
                        diffOrderEntity.setPayChannel(offlineOrderInfoPo.getPayChannel());
                        diffOrderEntity.setPayType("2");
                        diffOrderEntity.setShroffAccountNumber(offlineOrderInfoPo.getShroffAccountNumber());
                        diffOrderEntity.setStoreId(offlineOrderInfoPo.getStoreId());
                        diffOrderEntity.setPaymentPrice(offlineOrderInfoPo.getPaymentPrice());
                        diffOrderEntity.setStoreName(offlineOrderInfoPo.getStoreName());
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());
        }else{
            if(orderInfoList.size()==query.getLimit()){
                dataList = orderInfoList.stream().map(orderInfoEntity -> {
                            DiffOrderEntity diffOrderEntity = new DiffOrderEntity();
                            diffOrderEntity.setOrderType(orderInfoEntity.getOrderType());
                            diffOrderEntity.setOrderNo(orderInfoEntity.getOrderNo());
                            diffOrderEntity.setChannelId(orderInfoEntity.getChannelId());
                            diffOrderEntity.setCouponId(orderInfoEntity.getCouponId());
                            diffOrderEntity.setCouponPrice(orderInfoEntity.getCouponPrice());
                            diffOrderEntity.setPaymentNo(orderInfoEntity.getPaymentNo());
                            diffOrderEntity.setOrderPrice(orderInfoEntity.getOrderPrice());
                            diffOrderEntity.setPaymentPrice(orderInfoEntity.getOrderPrice());
                            diffOrderEntity.setPayChannel("微信支付");
                            diffOrderEntity.setPayType("1");
                            diffOrderEntity.setShroffAccountNumber(orderInfoEntity.getShroffAccountNumber());
                            diffOrderEntity.setStoreId(orderInfoEntity.getStoreId());
                            diffOrderEntity.setPaymentPrice(orderInfoEntity.getPaymentPrice());
                            diffOrderEntity.setStoreName(orderInfoEntity.getStoreName());
                            return diffOrderEntity;
                        }

                ).collect(Collectors.toList());
            }else{
                map.put("limit","0");
                Query query2 = new Query(map);
                orderInfoList = orderInfoService.queryList(query2);
                dataList = orderInfoList.stream().map(orderInfoEntity -> {
                            DiffOrderEntity diffOrderEntity = new DiffOrderEntity();
                            diffOrderEntity.setOrderType(orderInfoEntity.getOrderType());
                            diffOrderEntity.setOrderNo(orderInfoEntity.getOrderNo());
                            diffOrderEntity.setChannelId(orderInfoEntity.getChannelId());
                            diffOrderEntity.setCouponId(orderInfoEntity.getCouponId());
                            diffOrderEntity.setCouponPrice(orderInfoEntity.getCouponPrice());
                            diffOrderEntity.setPaymentNo(orderInfoEntity.getPaymentNo());
                            diffOrderEntity.setOrderPrice(orderInfoEntity.getOrderPrice());
                            diffOrderEntity.setPaymentPrice(orderInfoEntity.getOrderPrice());
                            diffOrderEntity.setPayChannel("微信支付");
                            diffOrderEntity.setPayType("1");
                            diffOrderEntity.setShroffAccountNumber(orderInfoEntity.getShroffAccountNumber());
                            diffOrderEntity.setStoreId(orderInfoEntity.getStoreId());
                            diffOrderEntity.setPaymentPrice(orderInfoEntity.getPaymentPrice());
                            diffOrderEntity.setStoreName(orderInfoEntity.getStoreName());
                            return diffOrderEntity;
                        }

                ).collect(Collectors.toList());
                List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryListByMap(query2);
                List<DiffOrderEntity> dataOfflineList = offlineOrderList.stream().map(offlineOrderInfoPo -> {
                            DiffOrderEntity diffOrderEntity = new DiffOrderEntity();
                            diffOrderEntity.setOrderType(offlineOrderInfoPo.getOrderType());
                            diffOrderEntity.setOrderNo(offlineOrderInfoPo.getOrderNo());
                            diffOrderEntity.setChannelId(offlineOrderInfoPo.getChannelId());
                            diffOrderEntity.setCouponId(offlineOrderInfoPo.getCouponId());
                            diffOrderEntity.setCouponPrice(offlineOrderInfoPo.getCouponPrice());
                            diffOrderEntity.setPaymentNo(offlineOrderInfoPo.getPaymentNo());
                            diffOrderEntity.setOrderPrice(offlineOrderInfoPo.getOrderPrice());
                            diffOrderEntity.setPaymentPrice(offlineOrderInfoPo.getOrderPrice());
                            diffOrderEntity.setPayChannel(offlineOrderInfoPo.getPayChannel());
                            diffOrderEntity.setPayType("2");
                            diffOrderEntity.setShroffAccountNumber(offlineOrderInfoPo.getShroffAccountNumber());
                            diffOrderEntity.setStoreId(offlineOrderInfoPo.getStoreId());
                            diffOrderEntity.setPaymentPrice(offlineOrderInfoPo.getPaymentPrice());
                            diffOrderEntity.setStoreName(offlineOrderInfoPo.getStoreName());
                            return diffOrderEntity;
                        }

                ).collect(Collectors.toList());

                dataList.addAll(dataOfflineList);
                if(dataList.size()<query.getPage()*query.getLimit()){
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),dataList.size());
                }else{
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),query.getPage()*query.getLimit());
                }
            }
//            Collections.sort(dataList, new Comparator<DiffOrderEntity>() {
//                @Override
//                public int compare(DiffOrderEntity o1, DiffOrderEntity o2) {
//                    return o1.getOrderNo().compareTo(o2.getOrderNo());
//                }
//            });
        }
        return dataList;
    }

}

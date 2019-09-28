package com.platform.service.impl;

import com.alibaba.excel.metadata.Sheet;
import com.platform.dao.MaterialMapper;
import com.platform.dao.OfflineOrderInfoPoMapper;
import com.platform.dao.OrderInfoDao;
import com.platform.entity.DiffOrderEntity;
import com.platform.entity.MaterialEntity;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.DiffOrderService;
import com.platform.service.MaterialService;
import com.platform.service.OfflineOrderService;
import com.platform.service.OrderInfoService;
import com.platform.utils.Query;
import com.platform.utils.excelutils.ExcelUtil;
import com.platform.utils.excelutils.MultipleSheelPropety;
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
        if("1".equals(payType)){
            List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(map);
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
                        diffOrderEntity.setShroffAccountNumber("0");
                        diffOrderEntity.setStoreId(0);
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());
        }else if("2".equals(payType)){
            List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryList(query);
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
                        diffOrderEntity.setPayChannel("银行支付");
                        diffOrderEntity.setPayType("2");
                        diffOrderEntity.setShroffAccountNumber("0");
                        diffOrderEntity.setStoreId(0);
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());
        }else{
            List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryList(query);
            List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(map);
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
                        diffOrderEntity.setShroffAccountNumber("0");
                        diffOrderEntity.setStoreId(0);
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());
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
                        diffOrderEntity.setPayChannel("银行支付");
                        diffOrderEntity.setPayType("2");
                        diffOrderEntity.setShroffAccountNumber("0");
                        diffOrderEntity.setStoreId(0);
                        return diffOrderEntity;
                    }

            ).collect(Collectors.toList());

            dataList.addAll(dataOfflineList);

            Collections.sort(dataList, new Comparator<DiffOrderEntity>() {
                @Override
                public int compare(DiffOrderEntity o1, DiffOrderEntity o2) {
                    return o1.getOrderNo().compareTo(o2.getOrderNo());
                }
            });
        }
        return dataList;
    }

    @Override
    public void downLoadMaterial(List dataList, ServletOutputStream outputStream) throws Exception {

        List<MultipleSheelPropety> multipleSheelPropetys = new ArrayList<MultipleSheelPropety>();


        Sheet index = new Sheet(0, 2);
        index.setSheetName("对账");
        index.setHead(createPaymentRateIndexHead());
        index.setAutoWidth(Boolean.TRUE);

        MultipleSheelPropety indexSheel = new MultipleSheelPropety();
        indexSheel.setSheet(index);
        indexSheel.setData(dataList);

        multipleSheelPropetys.add(indexSheel);
        ExcelUtil.writeDownloadWithMultipleSheel(outputStream, multipleSheelPropetys);
    }

    public static List<List<String>> createPaymentRateIndexHead(){
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        List<String> headCoulumn5 = new ArrayList<String>();
        List<String> headCoulumn6 = new ArrayList<String>();

        headCoulumn1.add("订单号");
        headCoulumn2.add("订单分类");
        headCoulumn3.add("订单金额");
        headCoulumn4.add("实际支付");
        headCoulumn5.add("优惠金额");
        headCoulumn6.add("支付方式");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        head.add(headCoulumn6);
        return head;
    }

}

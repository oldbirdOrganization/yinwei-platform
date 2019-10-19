package com.platform.controller;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderInfoEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.OfflineOrderService;
import com.platform.service.OrderInfoService;
import com.platform.utils.*;
import com.platform.vo.InfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
@RestController
@RequestMapping("order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OfflineOrderService offlineOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public R list(@RequestParam Map<String, Object> params) {
        List<OrderInfoEntity> orderList = queryListByMap(params);
        int total = queryTotalByMap(params);
        // 查询列表数据
        Query query = new Query(params);
        PageUtils pageUtil = new PageUtils(orderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
//    @RequestMapping("/info/{id}")
//    @RequiresPermissions("order:info")
//    public R info(@PathVariable("id") Integer id) {
//        OrderInfoEntity order = orderInfoService.queryObject(id);
//        return R.ok().put("order", order);
//    }

    @RequestMapping("/info")
    @RequiresPermissions("order:info")
    public R info(@RequestBody InfoVo infoVo) {
        String payType = infoVo.getPayType();
        String id = infoVo.getId();
        if("1".equals(payType)){
            OrderInfoEntity order = orderInfoService.queryObject(Integer.valueOf(id));
            if(!ObjectUtils.isEmpty(order)){
                //查询预约单信息
                Map<String, Object> premap = new HashMap<>();
                premap.put("parentOrderId",order.getParentOrderId());
                List<OrderInfoEntity> preorderList=orderInfoService.queryList(premap);
                if(preorderList.size()> 0){
                    BigDecimal alreadyAmount=new BigDecimal(0);
                    for (OrderInfoEntity preorderlist:preorderList){
                        if(preorderlist.getPaymentStatus() == 2){//已付金额= 总金额 - 历次已支付金额 -历次优惠金额
                            alreadyAmount=(alreadyAmount.add(preorderlist.getOrderPrice())).subtract(order.getCouponPrice());
                        }
                    }
                    order.setAlreadyPayAmount(alreadyAmount);//此预约单已支付金额
                    order.setResiduesPayAmount(order.getTotalAmount().subtract(alreadyAmount));//预约单剩余尾款金额=总金额 -已付金额
                }
            }

            order.setPayType("1");
            return R.ok().put("order", order);
        }else if("2".equals(payType)){
            OfflineOrderInfoPo order = offlineOrderService.queryDetailById(Integer.valueOf(id));
            order.setPayType("2");
            return R.ok().put("order", order);
        }
        return R.ok();
    }

    /**
     * 预约订单号是否首次新增支付单
     * @param order
     * @return
     */
    @RequestMapping("/isFirstPayOrder")
    @RequiresPermissions("order:info")
    public R isFirstPayOrder(@RequestBody OrderInfoEntity order) {
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        if(!StringUtils.isNotEmpty(order.getOrderNo())){
            return R.error(400,"预约单号为空，请重新输入");
        }
        orderInfoEntity.setOrderNo(order.getOrderNo());
        orderInfoEntity.setOrderType(1);
        orderInfoEntity = orderInfoService.selectBySelective(orderInfoEntity);
        if(ObjectUtils.isEmpty(orderInfoEntity)){
            return R.error(400,"未查到此预约单号信息，请重新输入");
        }

        //查询此预约单关联的支付单信息
        Map<String, Object> resultmap=new HashMap<>();
        Map<String, Object> premap = new HashMap<>();
        premap.put("parentOrderId",orderInfoEntity.getId());
        List<OrderInfoEntity> preorderList=orderInfoService.queryList(premap);
        if(preorderList.size()>0){
            for (OrderInfoEntity preorderlist:preorderList){
                if((preorderlist.getTotalAmount()).compareTo(BigDecimal.ZERO) !=0 ){
                    resultmap.put("totalAmount", preorderlist.getTotalAmount());
                    resultmap.put("isFirstPayOrder", false);
                }
            }
            return R.ok(resultmap);
        }else{
            resultmap.put("isFirstPayOrder", true);
            return R.ok(resultmap);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public R save(@RequestBody OrderInfoEntity order) {
        if("1".equals(order.getPayType())){
            OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
            orderInfoEntity.setOrderNo(order.getOrderNo());
            orderInfoEntity = orderInfoService.selectBySelective(orderInfoEntity);
            if(ObjectUtils.isEmpty(orderInfoEntity)){
                return R.error(400,"此预约单号不正确，无法添加线上待支付订单");
            }
            if(orderInfoEntity.getOrderStatus() == 1){
                return R.error(400,"此预约单号未指派，请先指派工人师傅");
            }
            if(orderInfoEntity.getOrderStatus() == 4){
                return R.error(400,"此预约单号已作废，无法添加线上待支付订单");
            }
            order.setParentOrderId(orderInfoEntity.getId().toString());
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            order.setOrderNo(fmt.format(new Date()));
            order.setPaymentStatus(1);//未支付
            order.setOrderStatus(2);//已确认
            order.setChannelId(orderInfoEntity.getChannelId());
            order.setContactMobile(orderInfoEntity.getContactMobile());
            order.setContactName(orderInfoEntity.getContactName());
            order.setCreateUserId(orderInfoEntity.getCreateUserId());
            order.setMasterWorkerId(orderInfoEntity.getMasterWorkerId());
            order.setMasterWorker(orderInfoEntity.getMasterWorker());
            order.setStoreId(orderInfoEntity.getStoreId());
            order.setAddress(orderInfoEntity.getAddress());
            order.setProblemDescription(orderInfoEntity.getProblemDescription());
            order.setServiceTime(orderInfoEntity.getServiceTime());
            order.setIsOuterOrder(orderInfoEntity.getIsOuterOrder());
            order.setServiceRequired(orderInfoEntity.getServiceRequired());
            order.setServiceHouseName(orderInfoEntity.getServiceHouseName());
            order.setServiceHouseType(orderInfoEntity.getServiceHouseType());
            order.setServiceAcreage(orderInfoEntity.getServiceAcreage());
            order.setServiceAirConditionerModel(orderInfoEntity.getServiceAirConditionerModel());
            order.setServiceAirConditionerType(orderInfoEntity.getServiceAirConditionerType());
            order.setServiceFurniture(orderInfoEntity.getServiceFurniture());
            order.setServiceIdea(orderInfoEntity.getServiceIdea());
            order.setServiceSpace(orderInfoEntity.getServiceSpace());
            order.setServiceType(orderInfoEntity.getServiceType());
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setDefunct(0);
            orderInfoService.save(order);
        }else if("2".equals(order.getPayType())){
            OfflineOrderInfoPo offlineOrderInfo = new OfflineOrderInfoPo();
            offlineOrderInfo.setOrderNo(order.getOrderNo());
            offlineOrderInfo = offlineOrderService.selectBySelective(offlineOrderInfo);

            if(ObjectUtils.isEmpty(offlineOrderInfo)){
                return R.error(400,"此预约单号不正确，无法添加线下已支付订单");
            }
            if(offlineOrderInfo.getOrderStatus() == 1){
                return R.error(400,"此预约单号未指派，请先指派工人师傅");
            }
            if(offlineOrderInfo.getOrderStatus() == 4){
                return R.error(400,"此预约单号已作废，无法添加线下已支付订单");
            }

            OfflineOrderInfoPo offlineOrderInfoPo = new OfflineOrderInfoPo();
            BeanUtils.copyProperties(order, offlineOrderInfoPo);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            offlineOrderInfoPo.setParentOrderId(offlineOrderInfo.getId().toString());
            offlineOrderInfoPo.setOrderNo(fmt.format(new Date()));
            offlineOrderInfoPo.setPaymentStatus(2);//已支付
            offlineOrderInfoPo.setOrderStatus(2);//已确认
            offlineOrderInfoPo.setChannelId(offlineOrderInfo.getChannelId());
            offlineOrderInfoPo.setContactMobile(offlineOrderInfo.getContactMobile());
            offlineOrderInfoPo.setContactName(offlineOrderInfo.getContactName());
            offlineOrderInfoPo.setProblemDescription(offlineOrderInfo.getProblemDescription());
            offlineOrderInfoPo.setCreateUserId(offlineOrderInfo.getCreateUserId());
            offlineOrderInfoPo.setMasterWorkerId(offlineOrderInfo.getMasterWorkerId());
            offlineOrderInfoPo.setStoreId(offlineOrderInfo.getStoreId());
            offlineOrderInfoPo.setAddress(offlineOrderInfo.getAddress());
            offlineOrderInfoPo.setServiceTime(offlineOrderInfo.getServiceTime());
            offlineOrderInfoPo.setIsOuterOrder(offlineOrderInfo.getIsOuterOrder());
            offlineOrderInfoPo.setCreateTime(new Date());
            offlineOrderInfoPo.setUpdateTime(new Date());
            offlineOrderInfoPo.setDefunct(0);
            offlineOrderService.save(offlineOrderInfoPo);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:update")
    public R update(@RequestBody OrderInfoEntity order) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        order.setUpdateUserId(user.getUserId());
        order.setUpdateTime(new Date());
        order.setOrderStatus(2);
        if("1".equals(order.getPayType())){
            orderInfoService.update(order);
        }else if("2".equals(order.getPayType())){
            OfflineOrderInfoPo offlineOrderInfoPo = new OfflineOrderInfoPo();
            BeanUtils.copyProperties(order, offlineOrderInfoPo);
            offlineOrderService.update(offlineOrderInfoPo);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:delete")
    public R delete(@RequestBody Integer[] ids) {
        orderInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<OrderInfoEntity> list = orderInfoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {
        int sum = orderInfoService.queryTotal(params);

        return R.ok().put("sum", sum);
    }

    /**
     * 订单作废
     *
     * @param id
     * @return
     */
    @RequestMapping("/cancelOrder")
    @RequiresPermissions("order:cancelOrder")
    public R cancelOrder(@RequestBody Integer id) {
        orderInfoService.cancelOrder(id);
        return R.ok();
    }

    /**
     * 订单指派工人师傅
     *
     * @param order
     * @return
     */
    @RequestMapping("/dispatchOrder")
    @RequiresPermissions("order:dispatchOrder")
    public R dispatchOrder(@RequestBody OrderInfoEntity order) {
        orderInfoService.dispatchOrder(order);
        return R.ok();
    }

    public int queryTotalByMap(Map<String, Object> map) {
        Query query = new Query(map);
        String payType = (String) map.get("payType");
        int total = 0;
        if ("1".equals(payType)) {
            total = orderInfoService.queryTotal(map);
        } else if ("2".equals(payType)) {
            total = offlineOrderService.queryTotal(query);
        } else {
            int s1 = orderInfoService.queryTotal(map);
            int s2 = offlineOrderService.queryTotal(query);
            total = orderInfoService.queryTotal(map) + offlineOrderService.queryTotal(query);
        }
        return total;
    }

    public List<OrderInfoEntity> queryListByMap(Map<String, Object> map) {
        Query query = new Query(map);
        List<OrderInfoEntity> dataList;
        String payType = (String) map.get("payType");
        List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(query);
        if ("1".equals(payType)) {
            dataList = orderInfoList.stream().map(orderInfoEntity -> {
                        OrderInfoEntity orderInfoVo = new OrderInfoEntity();
                        BeanUtils.copyProperties(orderInfoEntity, orderInfoVo);
                        orderInfoVo.setPayType("1");
                        return orderInfoVo;
                    }

            ).collect(Collectors.toList());
        } else if ("2".equals(payType)) {
            List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryListByMap(query);
            dataList = offlineOrderList.stream().map(offlineOrderInfoPo -> {
                        OrderInfoEntity orderInfoVo = new OrderInfoEntity();
                        BeanUtils.copyProperties(offlineOrderInfoPo, orderInfoVo);
                        orderInfoVo.setPayType("2");
                        return orderInfoVo;
                    }

            ).collect(Collectors.toList());
        } else {
            if(orderInfoList.size()==query.getLimit()){
                dataList = orderInfoList.stream().map(orderInfoEntity -> {
                            OrderInfoEntity orderInfoVo = new OrderInfoEntity();
                            BeanUtils.copyProperties(orderInfoEntity, orderInfoVo);
                            orderInfoVo.setPayType("1");
                            return orderInfoVo;
                        }

                ).collect(Collectors.toList());
            }else{
                map.put("limit","0");
                Query query2 = new Query(map);
                orderInfoList = orderInfoService.queryList(query2);
                dataList = orderInfoList.stream().map(orderInfoEntity -> {
                            OrderInfoEntity orderInfoVo = new OrderInfoEntity();
                            BeanUtils.copyProperties(orderInfoEntity, orderInfoVo);
                            orderInfoVo.setPayType("1");
                            return orderInfoVo;
                        }

                ).collect(Collectors.toList());

                if(dataList.size()<query.getPage()*query.getLimit()){
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),dataList.size());
                }else{
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),query.getPage()*query.getLimit());
                }
                map.put("limit","10");
            }
        }
        return dataList;
    }
}

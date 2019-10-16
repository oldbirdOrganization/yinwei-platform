package com.platform.controller;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderInfoEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.OfflineOrderService;
import com.platform.service.OrderInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.vo.InfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public R save(@RequestBody OrderInfoEntity order) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo",order.getOrderNo());
        map.put("page",1);
        map.put("limit",1);
        map.put("offset",10);
        map.put("sidx","");
        map.put("order","asc");
        if("1".equals(order.getPayType())){
            map.put("payType","1");
            List<OrderInfoEntity> orderList = queryListByMap(map);
            if(ObjectUtils.isEmpty(orderList)){
                return R.error(400,"此预约单号不正确，无法添加线上待支付订单");
            }
            order.setParentOrderId(orderList.get(0).getId().toString());
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            order.setOrderNo(fmt.format(new Date()));
            order.setPaymentStatus(1);//未支付
            order.setOrderStatus(1);//下单成功
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setDefunct(0);
            orderInfoService.save(order);
        }else if("2".equals(order.getPayType())){
            map.put("payType","2");
            OfflineOrderInfoPo offlineOrderInfoPo = new OfflineOrderInfoPo();
            BeanUtils.copyProperties(order, offlineOrderInfoPo);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            offlineOrderInfoPo.setOrderNo(fmt.format(new Date()));
            offlineOrderInfoPo.setPaymentStatus(2);//已支付
            offlineOrderInfoPo.setOrderStatus(1);//下单成功
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

                List<OfflineOrderInfoPo> offlineOrderList = offlineOrderService.queryListByMap(query2);
                List<OrderInfoEntity> dataOfflineList = offlineOrderList.stream().map(offlineOrderInfoPo -> {
                            OrderInfoEntity orderInfoVo = new OrderInfoEntity();
                            BeanUtils.copyProperties(offlineOrderInfoPo, orderInfoVo);
                            orderInfoVo.setPayType("2");
                            return orderInfoVo;
                        }

                ).collect(Collectors.toList());
                dataList.addAll(dataOfflineList);
                if(dataList.size()<query.getPage()*query.getLimit()){
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),dataList.size());
                }else{
                    dataList = dataList.subList((query.getPage()-1)*query.getLimit(),query.getPage()*query.getLimit());
                }
                map.put("limit","10");
            }

//            Collections.sort(dataList, new Comparator<OrderInfoVo>() {
//                @Override
//                public int compare(OrderInfoVo o1, OrderInfoVo o2) {
//                    return o1.getOrderNo().compareTo(o2.getOrderNo());
//                }
//            });
        }
        return dataList;
    }
}

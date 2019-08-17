package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.NideshopOrderImageEntity;
import com.platform.entity.NideshopOrderInfoEntity;
import com.platform.entity.UserVo;
import com.platform.service.OrderInfoService;
import com.platform.util.ApiBaseAction;
import com.platform.vo.SubmitOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author oldbirdteam <br>
 * 时间: 2019-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "订单相关")
@RestController
@RequestMapping("/api/orderInfo")
public class ApiOrderInfoController extends ApiBaseAction {

    @Autowired
    private OrderInfoService orderInfoService;


    /**
     * 下单
     */
    @ApiOperation(value = "下单")
    @IgnoreAuth
    @PostMapping("submitOrder")
    public Object submitOrder(@LoginUser UserVo loginUser,@RequestBody SubmitOrderVo submitOrderVo) {
        Integer orderId = orderInfoService.submitOrder(loginUser,submitOrderVo);
        return toResponsSuccess(orderId);
    }

    @ApiOperation(value = "订单详情")
    @IgnoreAuth
    @GetMapping("detail")
    public Object detail(@LoginUser UserVo loginUser,@RequestParam(name="orderId",required = true) Integer orderId) {
        NideshopOrderInfoEntity order = orderInfoService.findDetail(loginUser,orderId);
        List<NideshopOrderImageEntity> imageList = orderInfoService.findOrderImageList(orderId);
        Map<String,Object> result = new HashMap<>();
        result.put("order",order);
        result.put("imageList",imageList);
        return toResponsSuccess(result);
    }

    @ApiOperation(value = "待指派订单列表")
    @IgnoreAuth
    @GetMapping("designateingOrderList")
    public Object designateingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,null,1,null));
    }

    @ApiOperation(value = "待确认订单列表")
    @IgnoreAuth
    @GetMapping("confirmingOrderList")
    public Object confirmingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,null,2,null));
    }

    @ApiOperation(value = "待付款订单列表")
    @IgnoreAuth
    @GetMapping("paymentingOrderList")
    public Object paymentingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findPaymentingOrderList(loginUser));
    }

    @ApiOperation(value = "已完成订单列表")
    @IgnoreAuth
    @GetMapping("finishOrderList")
    public Object finishOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,null,4,null));
    }






}
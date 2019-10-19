package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.ApiGoodsService;
import com.platform.service.ApiOrderInfoService;
import com.platform.service.SysUserService;
import com.platform.util.ApiBaseAction;
import com.platform.vo.MasterWorkerVo;
import com.platform.vo.SubmitOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private ApiOrderInfoService orderInfoService;
    @Autowired
    private ApiGoodsService apiGoodsService;
    @Autowired
    private SysUserService sysUserService;



    @ApiOperation(value = "下单")
//    @IgnoreAuth
    @PostMapping("submitOrder")
    public Object submitOrder(@LoginUser UserVo loginUser,@RequestBody SubmitOrderVo submitOrderVo) {
        String orderNo = orderInfoService.submitOrder(loginUser,submitOrderVo);
        return toResponsSuccess(orderNo);
    }

    @ApiOperation(value = "订单详情")
    @IgnoreAuth
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", dataType = "String",required = true)
    })
    @GetMapping("detail")
    public Object detail(@RequestParam(name="orderNo",required = true) String orderNo) {
        Map<String,Object> result = new HashMap<>();
        NideshopOrderInfoEntity order = orderInfoService.findDetail(orderNo);
        if(!Objects.isNull(order)){
            List<NideshopOrderImageEntity> imageList = orderInfoService.findOrderImageList(order.getId());
            GoodsVo goodsVo = null;
            if (!Objects.isNull(order.getGoodsId())) {
                goodsVo = apiGoodsService.queryObject(order.getGoodsId());
            }

            //查询订单指派师傅信息
            SysUserEntity user=sysUserService.queryObject(order.getMasterWorkerId());
            if(!Objects.isNull(user)){
                MasterWorkerVo worker=new MasterWorkerVo();
                worker.setUserName(user.getUsername());
                worker.setMobile(user.getMobile());
                worker.setEvaluateLevel(5);
                result.put("worker",worker);
            }
            result.put("order",order);
            result.put("imageList",imageList);
            result.put("googds",goodsVo);
        }
        return toResponsSuccess(result);
    }

    @ApiOperation(value = "待指派订单列表")
//    @IgnoreAuth
    @GetMapping("designateingOrderList")
    public Object designateingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,null,1,null));
    }

    @ApiOperation(value = "待确认订单列表")
//    @IgnoreAuth
    @GetMapping("confirmingOrderList")
    public Object confirmingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,1,2,null));
    }

    @ApiOperation(value = "待付款订单列表")
//    @IgnoreAuth
    @GetMapping("paymentingOrderList")
    public Object paymentingOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findPaymentingOrderList(loginUser));
    }

    @ApiOperation(value = "已完成订单列表")
//    @IgnoreAuth
    @GetMapping("finishOrderList")
    public Object finishOrderList(@LoginUser UserVo loginUser) {
        return toResponsSuccess(orderInfoService.findOrderList(loginUser,null,3,null));
    }






}
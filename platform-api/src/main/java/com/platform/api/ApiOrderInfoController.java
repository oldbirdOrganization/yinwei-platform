package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.dao.NideshopOrderInfoDao;
import com.platform.entity.NideshopOrderInfoEntity;
import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiKdniaoService;
import com.platform.service.ApiOrderGoodsService;
import com.platform.service.ApiOrderService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.util.wechat.WechatRefundApiResult;
import com.platform.util.wechat.WechatUtil;
import com.platform.utils.Query;
import com.platform.vo.SubmitOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
    private NideshopOrderInfoDao nideshopOrderInfoDao;


    /**
     */
    @ApiOperation(value = "下单")
    @IgnoreAuth
    @PostMapping("submitOrder")
    public Object submitOrder(@LoginUser UserVo loginUser,@RequestBody SubmitOrderVo submitOrderVo) {
        NideshopOrderInfoEntity model = new NideshopOrderInfoEntity();
        BeanUtils.copyProperties(submitOrderVo,model);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        model.setOrderNo(fmt.format(new Date()));
        model.setPaymentstatus(1);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        //model.setCreateUserId(loginUser.getUserId());
       // model.setUpdateUserId(loginUser.getUserId());
        nideshopOrderInfoDao.insert(model);
        return toResponsSuccess(model.getId());
    }


}
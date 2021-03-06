package com.platform.api;

import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Base64;
import com.platform.utils.CharUtil;
import com.platform.utils.Query;
import com.platform.utils.StringUtils;
import com.platform.vo.CommentContentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 作者: @author oldbirdteam <br>
 * 时间: 2019-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController extends ApiBaseAction {
    @Autowired
    private ApiCommentService commentService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiCommentPictureService commentPictureService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiOrderInfoService orderInfoService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@LoginUser UserVo loginUser, @RequestBody CommentContentVo commentContentVo ) {
        Map resultObj = new HashMap();
        CommentVo commentEntity = new CommentVo();
        commentEntity.setType_id(commentContentVo.getTypeId());
        commentEntity.setValue_id(commentContentVo.getValueId());
        commentEntity.setContent(commentContentVo.getContent());
        commentEntity.setStatus(0);
        commentEntity.setQuality_evaluate_level(commentContentVo.getQualityEvaluateLevel());
        commentEntity.setService_evaluate_level(commentContentVo.getServiceEvaluateLevel());
        commentEntity.setAdd_time(System.currentTimeMillis() / 1000);
        commentEntity.setUser_id(loginUser.getUserId());
        commentEntity.setContent(Base64.encode(commentEntity.getContent()));
        Integer insertId = commentService.save(commentEntity);
        //
        if (insertId > 0 && null != commentContentVo.getImagesList() && commentContentVo.getImagesList().size() > 0) {
            int i = 0;
            for (Object imgLink : commentContentVo.getImagesList()) {
                i++;
                CommentPictureVo pictureVo = new CommentPictureVo();
                pictureVo.setComment_id(commentEntity.getId());
                pictureVo.setPic_url(imgLink.toString());
                pictureVo.setSort_order(i);
                commentPictureService.save(pictureVo);
            }
        }
        // 是否领取优惠券
        if (insertId > 0 && commentContentVo.getTypeId() == 0) {
            // 当前评价的次数
            Map param = new HashMap();
            param.put("value_id", commentContentVo.getValueId());
            List<CommentVo> commentVos = commentService.queryList(param);
            boolean hasComment = false;
            for (CommentVo commentVo : commentVos) {
                if (commentVo.getUser_id().equals(loginUser.getUserId())
                        && !commentVo.getId().equals(insertId)) {
                    hasComment = true;
                }
            }
            if (!hasComment) {
                Map couponParam = new HashMap();
                couponParam.put("send_type", 6);
                CouponVo newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
                if (null != newCouponConfig
                        && newCouponConfig.getMin_transmit_num() >= commentVos.size()) {
                    UserCouponVo userCouponVo = new UserCouponVo();
                    userCouponVo.setAdd_time(new Date());
                    userCouponVo.setCoupon_id(newCouponConfig.getId());
                    userCouponVo.setCoupon_number(CharUtil.getRandomString(12));
                    userCouponVo.setUser_id(loginUser.getUserId());
                    apiUserCouponService.save(userCouponVo);
                    resultObj.put("coupon", newCouponConfig);
                }
            }
        }
        if (insertId > 0) {
            //判断此订单是否尾款支付订单，是则更新预约单订单状态为已评价，否则只更新此订单状态为已评价
            NideshopOrderInfoEntity order = orderInfoService.findDetail(commentContentVo.getValueId());
            if(order.getOrderType()==1){//预约订单
                if(!ObjectUtils.isEmpty(order) && order.getOrderStatus().intValue() ==3) { //已完成服务并已完成订单支付
                    order.setOrderStatus(5);//状态为已评价
                    order.setUpdateTime(new Date());
                    orderInfoService.update(order);
                }
            }
            if(order.getOrderType()==2){//支付订单
                if(!ObjectUtils.isEmpty(order) && "3".equals(order.getItem())){//是尾款支付订单
                    order.setOrderStatus(5);//状态为已评价
                    order.setUpdateTime(new Date());
                    orderInfoService.update(order);
                    if(null !=order.getParentOrderId()){
                        //更新预约单订单状态为已完成
                        NideshopOrderInfoEntity preorder = orderInfoService.selectById(order.getParentOrderId());
                        preorder.setOrderStatus(3);
                        preorder.setUpdateTime(new Date());
                        orderInfoService.update(preorder);
                    }
                }else{//否尾款支付订单
                    order.setOrderStatus(5);//状态为已评价
                    order.setUpdateTime(new Date());
                    orderInfoService.update(order);
                }
            }
            return toResponsObject(0, "评论添加成功", resultObj);
        } else {
            return toResponsFail("评论保存失败");
        }
    }

    /**
     */
    @ApiOperation(value = "评论数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类型（1-订单评价 2-商品评价）", paramType = "query", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "valueId", value = "对应类型值", paramType = "query", dataType = "String",required = false)
    })
    @PostMapping("count")
    public Object count(Integer typeId, String valueId) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        Integer allCount = commentService.queryTotal(param);
        Integer hasPicCount = commentService.queryhasPicTotal(param);
        //
        resultObj.put("allCount", allCount);
        resultObj.put("hasPicCount", hasPicCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * @param typeId
     * @param valueId
     * @param showType 选择评论的类型 0 全部， 1 只显示图片
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "选择评论类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类型（1-订单评价 2-商品评价）", paramType = "query", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "valueId", value = "对应类型值", paramType = "query", dataType = "String",required = false),
            @ApiImplicitParam(name = "showType", value = "是否显示图片(0-全部 1-有图片)", paramType = "query", dataType = "Integer",required = false)
    })

    @PostMapping("list")
    public Object list(Integer typeId, String valueId, Integer showType,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        Map<String, Object> resultObj = new HashMap();
        List<CommentVo> commentList = new ArrayList();
        Map param = new HashMap();
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        param.put("page", page);
        param.put("limit", size);
        if (StringUtils.isNullOrEmpty(sort)) {
            param.put("order", "desc");
        } else {
            param.put("order", sort);
        }
        if (StringUtils.isNullOrEmpty(order)) {
            param.put("sidx", "id");
        } else {
            param.put("sidx", order);
        }
        if (null != showType && showType == 1) {
            param.put("hasPic", 1);
        }
        //查询列表数据
        Query query = new Query(param);
        commentList = commentService.queryList(query);
        int total = commentService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(commentList, total, query.getLimit(), query.getPage());
        //
        for (CommentVo commentItem : commentList) {
            commentItem.setContent(Base64.decode(commentItem.getContent()));
            commentItem.setUser_info(userService.queryObject(commentItem.getUser_id()));

            Map paramPicture = new HashMap();
            paramPicture.put("comment_id", commentItem.getId());
            List<CommentPictureVo> commentPictureEntities = commentPictureService.queryList(paramPicture);
            commentItem.setPic_list(commentPictureEntities);
        }
        return toResponsSuccess(pageUtil);
    }
}
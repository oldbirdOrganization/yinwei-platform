package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Base64;
import com.platform.utils.CharUtil;
import com.platform.utils.DateUtils;
import com.platform.utils.Query;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 作者: @author oldbirdteam <br>
 * 时间: 2019-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/goods")
public class ApiGoodsController extends ApiBaseAction {
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private ApiProductService productService;
    @Autowired
    private ApiGoodsGalleryService goodsGalleryService;
    @Autowired
    private ApiGoodsIssueService goodsIssueService;
    @Autowired
    private ApiAttributeService attributeService;
    @Autowired
    private ApiBrandService brandService;
    @Autowired
    private ApiCommentService commentService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiCommentPictureService commentPictureService;
    @Autowired
    private ApiCollectService collectService;
    @Autowired
    private ApiFootprintService footprintService;
    @Autowired
    private ApiCategoryService categoryService;
    @Autowired
    private ApiSearchHistoryService searchHistoryService;
    @Autowired
    private ApiRelatedGoodsService relatedGoodsService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiCartService cartService;

    @ApiOperation(value = "获取分类下面的商品列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "categoryId", value = "分类id", paramType = "path", required = true)})
    @IgnoreAuth
    @GetMapping(value = "goodsList")
    public Object goodsList(@RequestParam(name = "categoryId" , required = true) Integer categoryId) {
        Map params = new HashMap();
        params.put("category_id", categoryId);
        params.put("is_delete", 0);
        params.put("is_on_sale", 1);
        params.put("page", 1);
        params.put("limit", Integer.MAX_VALUE);
        params.put("sidx", "id");
        params.put("order", "desc");

        Query query = new Query(params);
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<GoodsVo> goodsList = goodsService.queryList(query);
        return toResponsSuccess(goodsList);
    }

    @ApiOperation(value = "商品详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", value = "商品id", paramType = "path", required = true)})
    @IgnoreAuth
    @GetMapping(value = "goodsDetail")
    public Object goodsDetail(@RequestParam(name = "goodsId" , required = true) Integer goodsId) {
       GoodsVo  goodsVo = goodsService.queryObject(goodsId);
        return toResponsSuccess(goodsVo);
    }

}
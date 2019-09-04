package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.GoodsVo;
import com.platform.service.ApiGoodsService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @ApiOperation(value = "获取分类下面的商品列表")
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
    @IgnoreAuth
    @GetMapping(value = "goodsDetail")
    public Object goodsDetail(@RequestParam(name = "goodsId" , required = true) Integer goodsId) {
       GoodsVo  goodsVo = goodsService.queryObject(goodsId);
        return toResponsSuccess(goodsVo);
    }

}
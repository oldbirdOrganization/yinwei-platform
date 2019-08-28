package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.BrandVo;
import com.platform.service.ApiBrandService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class ApiBrandController extends ApiBaseAction {
    @Autowired
    private ApiBrandService brandService;

    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @IgnoreAuth
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "size", value = "每页数量", paramType = "query", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "isOuterBrand", value = "是否第三方品牌（0-否 1-是）", paramType = "query", dataType = "Integer",required = false)
    })
    @PostMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       @RequestParam(value = "isOuterBrand",required = false) Integer isOuterBrand) {
        //查询列表数据
        Map params = new HashMap();
        params.put("fields", "id, name, floor_price, app_list_pic_url");
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        if(null != isOuterBrand){
            params.put("isOuterBrand", isOuterBrand);
        }
        Query query = new Query(params);
        List<BrandVo> brandEntityList = brandService.queryList(query);
        int total = brandService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(brandEntityList, total, query.getLimit(), query.getPage());
        //
        return toResponsSuccess(pageUtil);
    }

    /**
     * 品牌详情
     */
    @ApiOperation(value = "品牌详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(@RequestParam Integer id) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        BrandVo entity = brandService.queryObject(id);
        //
        resultObj.put("brand", entity);
        return toResponsSuccess(resultObj);
    }
}
package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.CategoryVo;
import com.platform.service.ApiCategoryService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author oldbirdteam <br>
 * 时间: 2019-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */

@Api(tags = "栏目")
@RestController
@RequestMapping("/api/catalog")
public class ApiCatalogController extends ApiBaseAction {
    @Autowired
    private ApiCategoryService categoryService;

    /**
     * 获取分类栏目数据
     */
    @ApiOperation(value = "获取分类栏目数据")
    @IgnoreAuth
    @GetMapping(value = "indexCatalog")
    public Object indexCatalog() {
        Map<String, Object> resultObj = new HashMap();
        Map params = new HashMap();
        params.put("page", 1);
        params.put("limit", Integer.MAX_VALUE);
        params.put("sidx", "sort_order");
        params.put("order", "asc");
        params.put("parent_id", 0);
        //查询列表数据
        List<CategoryVo> categoryVoList = categoryService.queryList(params);
        //默认第一个位当前分类
        CategoryVo currentCategory = new CategoryVo();
        if (CollectionUtils.isNotEmpty(categoryVoList)) {
            currentCategory = categoryVoList.get(0);
        }
        resultObj.put("categoryList", categoryVoList);
        resultObj.put("currentCategory", currentCategory);
        return toResponsSuccess(resultObj);
    }

    /**
     */
    @ApiOperation(value = "分类详细信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true)})
    @IgnoreAuth
    @GetMapping(value = "currentCatalog")
    public Object currentCatalog(@RequestParam(name = "id",required = true) Integer id) {
        Map<String, Object> resultObj = new HashMap();
        CategoryVo currentCategory = categoryService.queryObject(id);;
        resultObj.put("currentCategory", currentCategory);
        return toResponsSuccess(resultObj);
    }


}
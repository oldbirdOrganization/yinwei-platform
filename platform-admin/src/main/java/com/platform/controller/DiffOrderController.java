package com.platform.controller;

import com.platform.entity.DiffOrderEntity;
import com.platform.service.DiffOrderService;
import com.platform.utils.BeanUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.DiffOrderInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author panchong
 * @Title: DiffOrderController
 * @Description: TODO
 * @date 2019/9/2810:16
 */

@RestController
@RequestMapping("diffOrder")
public class DiffOrderController extends AbstractController {

    @Autowired
    private DiffOrderService diffOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("diffOrder:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<DiffOrderEntity> diffOrderList = diffOrderService.queryList(params);
        int total = diffOrderService.queryTotal(params);

        Query query = new Query(params);
        PageUtils pageUtil = new PageUtils(diffOrderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 导出excel
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public R exportExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        //查询列表数据
        List<DiffOrderEntity> resultList = diffOrderService.queryList(params);
        List<DiffOrderInfoVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            list = resultList.stream().map(a -> {
                DiffOrderInfoVo vo = new DiffOrderInfoVo();
                BeanUtils.copyProperties(a, vo);
                return vo;
            }).collect(Collectors.toList());
        }
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, list, DiffOrderInfoVo.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
        return R.ok();
    }
}

package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderGoodsEntity;
import com.platform.service.OfflineOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.OfflineOrderInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("offlineOrder")
public class OfflineOrderController {
    @Autowired
    private OfflineOrderService offlineOrderService;

    /**
     * 导入excel
     * @param file
     */
    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public R readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file){
        long t1 = System.currentTimeMillis();
        List<OfflineOrderInfoVo> list = ExcelUtils.readExcel("", OfflineOrderInfoVo.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        list.forEach(
                b -> System.out.println(JSON.toJSONString(b))
        );
        //入库
        offlineOrderService.importOfflineOrders(list);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("ordergoods:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        int total = offlineOrderService.queryTotal(query);
        List<OfflineOrderInfoPo> orderGoodsList = offlineOrderService.queryList(query);

        PageUtils pageUtil = new PageUtils(orderGoodsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
}

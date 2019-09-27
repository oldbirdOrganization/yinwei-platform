package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderGoodsEntity;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.OfflineOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.OfflineOrderInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 导出excel
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public R exportExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        //查询列表数据
        Query query = new Query(params);
        query.put("offset", null);
        List<OfflineOrderInfoPo> resultList = offlineOrderService.queryList(query);
        List<OfflineOrderInfoVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            list = resultList.stream().map(a -> {
                OfflineOrderInfoVo vo = new OfflineOrderInfoVo();
                BeanUtils.copyProperties(a, vo);
                vo.setPaymentTime(a.getPaymentTime().toString());
                vo.setCouponPrice(a.getCouponPrice());
                vo.setDescriptionDescription(a.getProblemDescription());
                vo.setIsOuterOrder((int)a.getIsOuterOrder());
                vo.setCouponPrice(a.getCouponPrice());
                return vo;
            }).collect(Collectors.toList());
        }
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, list, OfflineOrderInfoVo.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
        return R.ok();
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public R info(@PathVariable("id") Integer id) {
        OfflineOrderInfoPo a = offlineOrderService.queryDetailById(id);
        OfflineOrderInfoVo vo = new OfflineOrderInfoVo();
        BeanUtils.copyProperties(a, vo);
        vo.setPaymentTime(a.getPaymentTime().toString());
        vo.setCouponPrice(a.getCouponPrice());
        vo.setDescriptionDescription(a.getProblemDescription());
        vo.setIsOuterOrder((int)a.getIsOuterOrder());
        vo.setCouponPrice(a.getCouponPrice());
        return R.ok().put("order", vo);
    }
}

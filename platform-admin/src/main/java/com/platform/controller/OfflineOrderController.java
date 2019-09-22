package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.service.OfflineOrderService;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.OfflineOrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public String readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file){
        long t1 = System.currentTimeMillis();
        List<OfflineOrderInfoVo> list = ExcelUtils.readExcel("", OfflineOrderInfoVo.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        list.forEach(
                b -> System.out.println(JSON.toJSONString(b))
        );
        //入库
        offlineOrderService.importOfflineOrders(list);
        return "导入成功";
    }
}

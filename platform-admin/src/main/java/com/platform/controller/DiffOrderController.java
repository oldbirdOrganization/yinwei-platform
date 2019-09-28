package com.platform.controller;

import com.platform.entity.DiffOrderEntity;
import com.platform.entity.MaterialEntity;
import com.platform.entity.OrderGoodsEntity;
import com.platform.service.DiffOrderService;
import com.platform.service.OrderGoodsService;
import com.platform.utils.BeanUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.vo.MaterialInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
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
        Query query = new Query(params);
        query.put("offset", null);
        List<MaterialEntity> resultList = null;//materialService.queryList(query);
        List<MaterialInfoVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            list = resultList.stream().map(a -> {
                MaterialInfoVo vo = new MaterialInfoVo();
                BeanUtils.copyProperties(a, vo);
                return vo;
            }).collect(Collectors.toList());
        }
        long t1 = System.currentTimeMillis();

        ServletOutputStream outputStream = null;
        try {
            String fileName = "材料列表";
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            outputStream = response.getOutputStream();
            //materialService.downLoadMaterial(list,outputStream);
            outputStream.flush();

        } catch (Exception e) {
            logger.error("download异常", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return R.ok();
    }
}

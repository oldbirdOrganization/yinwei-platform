package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.annotation.SysLog;
import com.platform.entity.MaterialEntity;
import com.platform.service.MaterialService;
import com.platform.utils.BeanUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.excelutils.ExcelUtil;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.MaterialInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 材料管理Controller
 *
 * @author panchong
 * @date 2019-09-25 10:58:47
 */
@RestController
@RequestMapping("/material")
public class MaterialController extends AbstractController {

    @Autowired
    private MaterialService materialService;

    /**
     * 材料列表
     *
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("material:list")
    public R list(@RequestParam Map<String, Object> params) {

        //查询列表数据
        Query query = new Query(params);
        Map<String, Object> map = new HashMap<>();
        List<MaterialEntity> materialList = materialService.queryList(map);
        int total = materialService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(materialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

//    /**
//     * 选择材料(添加、修改菜单)
//     *
//     * @return R
//     */
//    @RequestMapping("/select")
//    @RequiresPermissions("material:select")
//    public R select() {
//        Map<String, Object> map = new HashMap<>();
//
//        List<MaterialEntity> storeList = materialService.queryList(map);
//
//        return R.ok().put("materialList", storeList);
//    }

    /**
     * 根据主键获取材料信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("material:info")
    public R info(@PathVariable("id") Long id) {
        MaterialEntity store = materialService.queryObject(id);

        return R.ok().put("material", store);
    }

    /**
     * 新增材料
     *
     * @param store 材料
     * @return R
     */
    @SysLog("新增材料")
    @RequestMapping("/save")
    @RequiresPermissions("material:save")
    public R save(@RequestBody MaterialEntity store) {
        materialService.save(store);

        return R.ok();
    }

    /**
     * 修改材料
     *
     * @param store 材料
     * @return R
     */
    @SysLog("修改材料")
    @RequestMapping("/update")
    @RequiresPermissions("material:update")
    public R update(@RequestBody MaterialEntity store) {
        materialService.update(store);

        return R.ok();
    }

    /**
     * 删除材料
     *
     * @param ids 主键
     * @return R
     */
    @SysLog("删除材料")
    @RequestMapping("/delete")
    @RequiresPermissions("material:delete")
    public R delete(@RequestBody Integer[] ids){
        materialService.deleteBatch(ids);
        return R.ok();
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
        List<MaterialEntity> resultList = materialService.queryList(query);
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
            materialService.downLoadMaterial(list,outputStream);
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
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
        return R.ok();
    }

    /**
     * 导入excel
     * @param file
     */
    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public R readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file){
        long t1 = System.currentTimeMillis();
        List<MaterialInfoVo> list = ExcelUtils.readExcel("", MaterialInfoVo.class, file);
        long t2 = System.currentTimeMillis();
        //入库
        //materialService.importOfflineOrders(list);
        return R.ok();
    }

}

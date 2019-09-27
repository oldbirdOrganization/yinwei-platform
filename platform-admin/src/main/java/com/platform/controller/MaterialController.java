package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.MaterialEntity;
import com.platform.entity.SysStoreEntity;
import com.platform.service.MaterialService;
import com.platform.service.SysStoreService;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public R list() {
        Map<String, Object> map = new HashMap<>();
        List<MaterialEntity> storeList = materialService.queryList(map);
        return R.ok().put("list", storeList);
    }

    /**
     * 选择材料(添加、修改菜单)
     *
     * @return R
     */
    @RequestMapping("/select")
    @RequiresPermissions("material:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();

        List<MaterialEntity> storeList = materialService.queryList(map);

        return R.ok().put("materialList", storeList);
    }

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
     * @param id 主键
     * @return R
     */
    @SysLog("删除材料")
    @RequestMapping("/delete")
    @RequiresPermissions("material:delete")
    public R delete(long id) {
        materialService.delete(id);
        return R.ok();
    }

}

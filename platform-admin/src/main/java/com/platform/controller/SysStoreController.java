package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.SysStoreEntity;
import com.platform.service.SysStoreService;
import com.platform.utils.Constant;
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
 * 门店管理Controller
 *
 * @author panchong
 * @date 2019-09-25 10:58:47
 */
@RestController
@RequestMapping("/sys/store")
public class SysStoreController extends AbstractController {

    @Autowired
    private SysStoreService sysStoreService;

    /**
     * 门店列表
     *
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:store:list")
    public R list() {
        Map<String, Object> map = new HashMap<>();
        //map.put("storeFilter", sysStoreService.getSubstoreIdList(getstoreId()));
        List<SysStoreEntity> storeList = sysStoreService.queryList(map);
        return R.ok().put("list", storeList);
    }

    /**
     * 选择门店(添加、修改菜单)
     *
     * @return R
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:store:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();

        List<SysStoreEntity> storeList = sysStoreService.queryList(map);

        //添加一级门店
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysStoreEntity root = new SysStoreEntity();
            root.setStoreId(0L);
            root.setName("一级门店");
            root.setParentId(-1L);
            root.setOpen(true);
            storeList.add(root);
        }

        return R.ok().put("storeList", storeList);
    }

//    /**
//     * 获取用户门店Id(管理员则为0)
//     *
//     * @return
//     */
//    @RequestMapping("/info")
//    @RequiresPermissions("sys:store:list")
//    public R info() {
//        long storeId = 0;
//        return R.ok().put("storeId", storeId);
//    }

    /**
     * 根据主键获取门店信息
     *
     * @param storeId 主键
     * @return R
     */
    @RequestMapping("/info/{storeId}")
    @RequiresPermissions("sys:store:info")
    public R info(@PathVariable("storeId") Long storeId) {
        SysStoreEntity store = sysStoreService.queryObject(storeId);

        return R.ok().put("store", store);
    }

    /**
     * 新增门店
     *
     * @param store 门店
     * @return R
     */
    @SysLog("新增门店")
    @RequestMapping("/save")
    @RequiresPermissions("sys:store:save")
    public R save(@RequestBody SysStoreEntity store) {
        sysStoreService.save(store);

        return R.ok();
    }

    /**
     * 修改门店
     *
     * @param store 门店
     * @return R
     */
    @SysLog("修改门店")
    @RequestMapping("/update")
    @RequiresPermissions("sys:store:update")
    public R update(@RequestBody SysStoreEntity store) {
        sysStoreService.update(store);

        return R.ok();
    }

    /**
     * 删除门店
     *
     * @param storeId 主键
     * @return R
     */
    @SysLog("删除门店")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:store:delete")
    public R delete(long storeId) {
        //判断是否有子门店
        List<Long> storeList = sysStoreService.queryStoreIdList(storeId);
        if (storeList.size() > 0) {
            return R.error("请先删除子门店");
        }

        sysStoreService.delete(storeId);

        return R.ok();
    }

}

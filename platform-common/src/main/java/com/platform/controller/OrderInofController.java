package com.platform.controller;

import com.platform.entity.OrderInfoEntity;
import com.platform.service.OrderInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:09
 */
@RestController
@RequestMapping("order")
public class OrderInofController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public R list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);

        List<OrderInfoEntity> orderList = orderInfoService.queryList(query);
        int total = orderInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(orderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public R info(@PathVariable("id") Integer id) {
        OrderInfoEntity order = orderInfoService.queryObject(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public R save(@RequestBody OrderInfoEntity order) {
        orderInfoService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:update")
    public R update(@RequestBody OrderInfoEntity order) {
        orderInfoService.update(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:delete")
    public R delete(@RequestBody Integer[] ids) {
        orderInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<OrderInfoEntity> list = orderInfoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {
        int sum = orderInfoService.queryTotal(params);

        return R.ok().put("sum", sum);
    }

    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    @RequestMapping("/confirm")
    @RequiresPermissions("order:confirm")
    public R confirm(@RequestBody Integer id) {
        orderInfoService.confirm(id);

        return R.ok();
    }

}

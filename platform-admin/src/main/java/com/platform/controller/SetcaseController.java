package com.platform.controller;

import com.platform.entity.SetcaseEntity;
import com.platform.service.SetcaseService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author oldbirdteam
 * @email oldbirdteam@gmail.com
 * @date 2019-10-26 10:26:47
 */
@RestController
@RequestMapping("setcase")
public class SetcaseController extends AbstractController{
    @Autowired
    private SetcaseService setcaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("setcase:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SetcaseEntity> caseList = setcaseService.queryList(query);
        int total = setcaseService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(caseList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("setcase:info")
    public R info(@PathVariable("id") Integer id){
		SetcaseEntity setcase = setcaseService.queryObject(id);
        return R.ok().put("setcase", setcase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("setcase:save")
    public R save(@RequestBody SetcaseEntity setcase){

        setcase.setCreateTime(new Date());
        setcase.setCreateBy(getUser().getUsername());
		setcaseService.save(setcase);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("setcase:update")
    public R update(@RequestBody SetcaseEntity setcase){
        setcase.setUpdateTime(new Date());
        setcase.setUpdateBy(getUser().getUsername());
		setcaseService.update(setcase);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("setcase:delete")
    public R delete(@RequestBody Integer[] ids){
		setcaseService.deleteBatch(ids);
        return R.ok();
    }

}

package com.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.entity.SysLogEntity;
import com.platform.utils.PageUtilsPlus;

import java.util.Map;

/**
 * 系统日志
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {
    /**
     * 分页
     *
     * @param params
     * @return
     */
    PageUtilsPlus queryPage(Map<String, Object> params);
}

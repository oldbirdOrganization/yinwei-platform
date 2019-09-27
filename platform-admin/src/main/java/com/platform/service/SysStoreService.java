package com.platform.service;

import com.platform.entity.SysStoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 门店管理
 *
 * @author panchong
 * @date 2019-09-25 11:06:47
 */
public interface SysStoreService {

    SysStoreEntity queryObject(Long StoreId);

    List<SysStoreEntity> queryList(Map<String, Object> map);

    void save(SysStoreEntity sysStore);

    void update(SysStoreEntity sysStore);

    void delete(Long StoreId);
}

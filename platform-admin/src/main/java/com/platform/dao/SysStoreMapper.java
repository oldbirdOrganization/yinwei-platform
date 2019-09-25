package com.platform.dao;

import com.platform.entity.SysStoreEntity;

import java.util.List;
import java.util.Map;

public interface SysStoreMapper {
    int deleteByPrimaryKey(Long storeId);

    List<SysStoreEntity> queryList(Map<String, Object> map);

    int insert(SysStoreEntity record);

    int insertSelective(SysStoreEntity record);

    SysStoreEntity selectByPrimaryKey(Long storeId);

    int updateByPrimaryKeySelective(SysStoreEntity record);

    int updateByPrimaryKey(SysStoreEntity record);

    List<Long> queryStoreIdList(Long StoreId);
}
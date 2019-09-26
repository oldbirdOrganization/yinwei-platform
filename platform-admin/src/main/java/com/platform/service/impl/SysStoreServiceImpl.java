package com.platform.service.impl;

import com.platform.dao.SysStoreMapper;
import com.platform.entity.SysStoreEntity;
import com.platform.service.SysStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysStoreService")
public class SysStoreServiceImpl implements SysStoreService {
    @Autowired
    private SysStoreMapper sysStoreMapper;

    @Override
    public SysStoreEntity queryObject(Long StoreId) {
        return sysStoreMapper.selectByPrimaryKey(StoreId);
    }

    @Override
    public List<SysStoreEntity> queryList(Map<String, Object> map) {
        return sysStoreMapper.queryList(map);
    }

    @Override
    public void save(SysStoreEntity sysStore) {
        sysStoreMapper.insertSelective(sysStore);
    }

    @Override
    public void update(SysStoreEntity sysStore) {
        sysStoreMapper.updateByPrimaryKey(sysStore);
    }

    @Override
    public void delete(Long StoreId) {
        sysStoreMapper.deleteByPrimaryKey(StoreId);
    }

}

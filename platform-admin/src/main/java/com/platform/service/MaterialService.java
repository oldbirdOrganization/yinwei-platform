package com.platform.service;

import com.platform.entity.MaterialEntity;
import com.platform.entity.SysStoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 材料管理
 *
 * @author panchong
 * @date 2019-09-25 11:06:47
 */
public interface MaterialService {

    MaterialEntity queryObject(Long id);

    List<MaterialEntity> queryList(Map<String, Object> map);

    void save(MaterialEntity material);

    void update(MaterialEntity material);

    void delete(Long id);
}

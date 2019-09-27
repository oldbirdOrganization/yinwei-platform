package com.platform.dao;

import com.platform.entity.MaterialEntity;

import java.util.List;
import java.util.Map;

public interface MaterialMapper {
    int insert(MaterialEntity record);

    int insertSelective(MaterialEntity record);

    int deleteByPrimaryKey(Long storeId);

    int deleteBatch(Integer[] ids);

    int queryTotal(Map<String, Object> map);

    List<MaterialEntity> queryList(Map<String, Object> map);

    MaterialEntity selectByPrimaryKey(Long storeId);

    int updateByPrimaryKeySelective(MaterialEntity record);

    int updateByPrimaryKey(MaterialEntity record);
}
package com.platform.service.impl;

import com.platform.dao.MaterialMapper;
import com.platform.entity.MaterialEntity;
import com.platform.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public MaterialEntity queryObject(Long id) {
        return materialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MaterialEntity> queryList(Map<String, Object> map) {
        return materialMapper.queryList(map);
    }

    @Override
    public void save(MaterialEntity materialEntity) {
        materialMapper.insertSelective(materialEntity);
    }

    @Override
    public void update(MaterialEntity materialEntity) {
        materialMapper.updateByPrimaryKey(materialEntity);
    }

    @Override
    public void delete(Long id) {
        materialMapper.deleteByPrimaryKey(id);
    }

}

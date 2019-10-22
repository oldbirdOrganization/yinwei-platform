package com.platform.service.impl;

import com.platform.dao.MaterialMapper;
import com.platform.entity.MaterialEntity;
import com.platform.service.MaterialService;
import com.platform.vo.MaterialInfoUpVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return materialMapper.queryTotal(map);
    }

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

    @Override
    public void deleteBatch(Integer[] ids) {
        materialMapper.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void importOfflineOrders(List<MaterialInfoUpVo> vos) {
        if (CollectionUtils.isNotEmpty(vos)) {
            vos.forEach(
                    a -> {
                        MaterialEntity materialEntity = new MaterialEntity();
                        BeanUtils.copyProperties(a, materialEntity);
                        materialMapper.insertSelective(materialEntity);
                    }
            );
        }
    }

}

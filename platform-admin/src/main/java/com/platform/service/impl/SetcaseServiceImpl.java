package com.platform.service.impl;

import com.platform.dao.SetcaseDao;
import com.platform.entity.SetcaseEntity;
import com.platform.service.SetcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("setcaseService")
public class SetcaseServiceImpl implements SetcaseService {
    @Autowired
    private SetcaseDao setcaseDao;

    @Override
    public SetcaseEntity queryObject(Integer id) {
        return setcaseDao.queryObject(id);
    }

    @Override
    public List<SetcaseEntity> queryList(Map<String, Object> map) {
        return setcaseDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return setcaseDao.queryTotal(map);
    }

    @Override
    public int save(SetcaseEntity topic) {
        return setcaseDao.save(topic);
    }

    @Override
    public int update(SetcaseEntity topic) {
        return setcaseDao.update(topic);
    }

    @Override
    public int delete(Integer id) {
        return setcaseDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return setcaseDao.deleteBatch(ids);
    }
}
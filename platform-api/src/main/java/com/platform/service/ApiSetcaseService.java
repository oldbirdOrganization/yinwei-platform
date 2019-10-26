package com.platform.service;

import com.platform.dao.ApiSetcaseMapper;
import com.platform.entity.SetcaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiSetcaseService {
    @Autowired
    private ApiSetcaseMapper setcaseDao;


    public SetcaseVo queryObject(Integer id) {
        return setcaseDao.queryObject(id);
    }


    public List<SetcaseVo> queryList(Map<String, Object> map) {
        return setcaseDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return setcaseDao.queryTotal(map);
    }


    public void save(SetcaseVo setcase) {
        setcaseDao.save(setcase);
    }


    public void update(SetcaseVo setcase) {
        setcaseDao.update(setcase);
    }


    public void delete(Integer id) {
        setcaseDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        setcaseDao.deleteBatch(ids);
    }

}

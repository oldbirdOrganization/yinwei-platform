package com.platform.service;

import com.platform.entity.SpecificationEntity;

import java.util.List;
import java.util.Map;

/**
 * 规格表
 * 
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:10
 */
public interface SpecificationService {
	
	SpecificationEntity queryObject(Integer id);
	
	List<SpecificationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SpecificationEntity specification);
	
	void update(SpecificationEntity specification);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

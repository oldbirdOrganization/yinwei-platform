package com.platform.service;

import com.platform.entity.CartEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:06
 */
public interface CartService {
	
	CartEntity queryObject(Integer id);
	
	List<CartEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CartEntity cart);
	
	void update(CartEntity cart);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

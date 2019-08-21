package com.platform.dao;

import com.platform.entity.GoodsGalleryEntity;

import java.util.Map;

/**
 * Dao
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-23 14:41:43
 */
public interface GoodsGalleryDao extends BaseDao<GoodsGalleryEntity> {
    int deleteByGoodsId(Map<String, Integer> map);
}

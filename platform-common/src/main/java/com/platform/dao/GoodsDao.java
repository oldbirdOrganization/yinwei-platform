package com.platform.dao;

import com.platform.entity.GoodsEntity;

/**
 * Dao
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-21 21:19:49
 */
public interface GoodsDao extends BaseDao<GoodsEntity> {
    Integer queryMaxId();
}

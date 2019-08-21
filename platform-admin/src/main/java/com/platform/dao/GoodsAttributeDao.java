package com.platform.dao;

import com.platform.entity.GoodsAttributeEntity;

/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-13 10:41:08
 */
public interface GoodsAttributeDao extends BaseDao<GoodsAttributeEntity> {

    int updateByGoodsIdAttributeId(GoodsAttributeEntity goodsAttributeEntity);
}

package com.platform.dao;

import com.platform.entity.CategoryEntity;

/**
 * Dao
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-21 15:32:31
 */
public interface CategoryDao extends BaseDao<CategoryEntity> {

    public int deleteByParentBatch(Object[] id);
}

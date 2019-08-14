package com.platform.dao;

import com.platform.entity.CommentPictureEntity;

/**
 * 评价图片Dao
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-29 14:45:55
 */
public interface CommentPictureDao extends BaseDao<CommentPictureEntity> {
    /**
     * 根据commentId删除
     *
     * @param commentId
     * @return
     */
    int deleteByCommentId(Integer commentId);
}

package com.platform.service;

import com.platform.entity.DiffOrderEntity;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 材料管理
 *
 * @author panchong
 * @date 2019-09-25 11:06:47
 */
public interface DiffOrderService {

    DiffOrderEntity queryObject(Long id);

    int queryTotal(Map<String, Object> map);

    List<DiffOrderEntity> queryList(Map<String, Object> map);

    public void downLoadDiffOrder(List dataList, ServletOutputStream outputStream) throws Exception;
}

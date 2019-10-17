package com.platform.service;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.utils.Query;
import com.platform.vo.OfflineOrderInfoVo;

import java.util.List;

public interface OfflineOrderService {
    void importOfflineOrders(List<OfflineOrderInfoVo> vos);

    int save(OfflineOrderInfoPo order);

    int update(OfflineOrderInfoPo order);

    List<OfflineOrderInfoPo> queryList(Query query);

    List<OfflineOrderInfoPo> queryListByMap(Query query);

    int queryTotal(Query query);

    OfflineOrderInfoPo queryDetailById(Integer id);

    OfflineOrderInfoPo selectBySelective(OfflineOrderInfoPo order);
}

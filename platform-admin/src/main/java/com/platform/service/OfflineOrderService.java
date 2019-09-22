package com.platform.service;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.utils.Query;
import com.platform.vo.OfflineOrderInfoVo;

import java.util.List;

public interface OfflineOrderService {
    void importOfflineOrders(List<OfflineOrderInfoVo> vos);

    List<OfflineOrderInfoPo> queryList(Query query);

    int queryTotal(Query query);
}

package com.platform.service;

import com.platform.vo.OfflineOrderInfoVo;

import java.util.List;

public interface OfflineOrderService {
    void importOfflineOrders(List<OfflineOrderInfoVo> vos);
}

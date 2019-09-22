package com.platform.service.impl;

import com.platform.dao.OfflineOrderInfoPoMapper;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OfflineOrderInfoPoExample;
import com.platform.service.OfflineOrderService;
import com.platform.utils.ShiroUtils;
import com.platform.vo.OfflineOrderInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfflineOrderServiceImpl implements OfflineOrderService {
    @Autowired
    private OfflineOrderInfoPoMapper offlineOrderInfoPoMapper;

    @Override
    @Transactional
    public void importOfflineOrders(List<OfflineOrderInfoVo> vos) {
        if (CollectionUtils.isNotEmpty(vos)) {
            Set<String> orderNos = vos.stream().map(OfflineOrderInfoVo::getOrderNo).collect(Collectors.toSet());
            //删除原来的线下订单
            OfflineOrderInfoPoExample example = new OfflineOrderInfoPoExample();
            example.createCriteria().andOrderNoIn(new ArrayList<>(orderNos));
            offlineOrderInfoPoMapper.deleteByExample(example);
            vos.forEach(
                  a -> {
                      OfflineOrderInfoPo offlineOrderInfoEntity = new OfflineOrderInfoPo();
                      BeanUtils.copyProperties(a, offlineOrderInfoEntity);
                      Long userId = ShiroUtils.getUserEntity().getUserId();
                      SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                      try {
                          offlineOrderInfoEntity.setPaymentTime(formatter.parse(a.getPaymentTime()));
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      offlineOrderInfoEntity.setIsOuterOrder(a.getIsOuterOrder().shortValue());
                      offlineOrderInfoEntity.setProblemDescription(a.getDescriptionDescription());
                      offlineOrderInfoEntity.setCreateUserId(userId);
                      offlineOrderInfoEntity.setUpdateUserId(userId);
                      offlineOrderInfoEntity.setDefunct(0);
                      offlineOrderInfoEntity.setCreateTime(new Date());
                      offlineOrderInfoEntity.setUpdateTime(new Date());
                      offlineOrderInfoPoMapper.insertSelective(offlineOrderInfoEntity);
                  }
            );
        }
    }
}

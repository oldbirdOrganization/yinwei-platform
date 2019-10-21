package com.platform.service.impl;

import com.platform.dao.OfflineOrderInfoPoMapper;
import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OfflineOrderInfoPoExample;
import com.platform.service.OfflineOrderService;
import com.platform.utils.Query;
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
            String batchNo = vos.get(0).getBatchNo();
            Set<String> orderNos = vos.stream().map(OfflineOrderInfoVo::getOrderNo).collect(Collectors.toSet());
            //删除原来的线下订单
            OfflineOrderInfoPoExample example = new OfflineOrderInfoPoExample();
            example.createCriteria().andOrderNoIn(new ArrayList<>(orderNos)).andBatchNoEqualTo(batchNo);
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

    @Override
    public List<OfflineOrderInfoPo> queryList(Query query) {
        OfflineOrderInfoPoExample example = new OfflineOrderInfoPoExample();
        OfflineOrderInfoPoExample.Criteria criteria = example.createCriteria();
        if (notNull(query.get("orderNo"))) {
            criteria.andOrderNoEqualTo(query.get("orderNo").toString());
        }
        if (notNull(query.get("orderStatus"))) {
            criteria.andOrderStatusEqualTo(Integer.valueOf(query.get("orderStatus").toString()));
        }
        if (notNull(query.get("orderType"))) {
            criteria.andOrderNoEqualTo(query.get("orderType").toString());
        }
        if (notNull(query.get("channelId"))) {
            criteria.andOrderNoEqualTo(query.get("channelId").toString());
        }
        if (notNull(query.get("isOuterOrder"))) {
            criteria.andOrderNoEqualTo(query.get("isOuterOrder").toString());
        }
        if (notNull(query.get("parentOrderId"))) {
            criteria.andParentOrderIdEqualTo(Integer.parseInt(query.get("parentOrderId").toString()));
        }
        if (notNull(query.get("offset"))) {
            example.setOffset(Integer.valueOf(query.get("offset").toString()));
            example.setPageSize(Integer.valueOf(query.get("limit").toString()));
        }
        return offlineOrderInfoPoMapper.selectByExample(example);
    }

    @Override
    public List<OfflineOrderInfoPo> queryListByMap(Query query) {
        return offlineOrderInfoPoMapper.queryListByMap(query);
    }

    private boolean notNull(Object o) {
        if (o != null && !o.toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int queryTotal(Query query) {
        OfflineOrderInfoPoExample example = new OfflineOrderInfoPoExample();
        OfflineOrderInfoPoExample.Criteria criteria = example.createCriteria();
        if (notNull(query.get("orderNo"))) {
            criteria.andOrderNoEqualTo(query.get("orderNo").toString());
        }
        if (notNull(query.get("orderStatus"))) {
            criteria.andOrderStatusEqualTo(Integer.valueOf(query.get("orderStatus").toString()));
        }
        if (notNull(query.get("orderType"))) {
            criteria.andOrderNoEqualTo(query.get("orderType").toString());
        }
        if (notNull(query.get("channelId"))) {
            criteria.andOrderNoEqualTo(query.get("channelId").toString());
        }
        if (notNull(query.get("isOuterOrder"))) {
            criteria.andOrderNoEqualTo(query.get("isOuterOrder").toString());
        }
        return offlineOrderInfoPoMapper.countByExample(example);
    }

    @Override
    public OfflineOrderInfoPo queryDetailById(Integer id) {
        return offlineOrderInfoPoMapper.selectByPrimaryKey(id);
    }

    @Override
    public OfflineOrderInfoPo selectBySelective(OfflineOrderInfoPo order) {
        return offlineOrderInfoPoMapper.selectBySelective(order);
    }

    @Override
    public int save(OfflineOrderInfoPo order) {
        return offlineOrderInfoPoMapper.insertSelective(order);
    }

    @Override
    public int update(OfflineOrderInfoPo order) {
        return offlineOrderInfoPoMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<OfflineOrderInfoPo> queryListCondtion(Map<String, Object> map) {
        OfflineOrderInfoPoExample example = new OfflineOrderInfoPoExample();
        OfflineOrderInfoPoExample.Criteria criteria = example.createCriteria();
        if (notNull(map.get("orderNo"))) {
            criteria.andOrderNoEqualTo(map.get("orderNo").toString());
        }
        if (notNull(map.get("orderStatus"))) {
            criteria.andOrderStatusEqualTo(Integer.valueOf(map.get("orderStatus").toString()));
        }
        if (notNull(map.get("orderType"))) {
            criteria.andOrderNoEqualTo(map.get("orderType").toString());
        }
        if (notNull(map.get("channelId"))) {
            criteria.andOrderNoEqualTo(map.get("channelId").toString());
        }
        if (notNull(map.get("isOuterOrder"))) {
            criteria.andOrderNoEqualTo(map.get("isOuterOrder").toString());
        }
        if (notNull(map.get("parentOrderId"))) {
            criteria.andParentOrderIdEqualTo(Integer.parseInt(map.get("parentOrderId").toString()));
        }
        return offlineOrderInfoPoMapper.selectByExample(example);
    }
}

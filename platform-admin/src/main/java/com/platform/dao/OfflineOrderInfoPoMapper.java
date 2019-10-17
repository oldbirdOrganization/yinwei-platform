package com.platform.dao;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OfflineOrderInfoPoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OfflineOrderInfoPoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int countByExample(OfflineOrderInfoPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int deleteByExample(OfflineOrderInfoPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int insert(OfflineOrderInfoPo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int insertSelective(OfflineOrderInfoPo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    List<OfflineOrderInfoPo> selectByExample(OfflineOrderInfoPoExample example);

    List<OfflineOrderInfoPo> queryListByMap(Map<String, Object> map);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    OfflineOrderInfoPo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int updateByExampleSelective(@Param("record") OfflineOrderInfoPo record, @Param("example") OfflineOrderInfoPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int updateByExample(@Param("record") OfflineOrderInfoPo record, @Param("example") OfflineOrderInfoPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int updateByPrimaryKeySelective(OfflineOrderInfoPo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nideshop_offline_order_info
     *
     * @mbggenerated Mon Sep 23 10:05:23 CST 2019
     */
    int updateByPrimaryKey(OfflineOrderInfoPo record);

    OfflineOrderInfoPo selectBySelective(OfflineOrderInfoPo record);
}
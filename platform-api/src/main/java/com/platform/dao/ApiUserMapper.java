package com.platform.dao;

import com.platform.entity.SmsLogVo;
import com.platform.entity.UserCouponDto;
import com.platform.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-03-23 15:22:06
 */
public interface ApiUserMapper extends BaseDao<UserVo> {

    UserVo queryByMobile(String mobile);

    UserVo queryByOpenId(@Param("openId") String openId);

    /**
     * 获取用户最后一条短信
     *
     * @param user_id
     * @return
     */
    SmsLogVo querySmsCodeByUserId(@Param("user_id") Long user_id);

    /**
     * 保存短信
     *
     * @param smsLogVo
     * @return
     */
    int saveSmsCodeLog(SmsLogVo smsLogVo);

    /**
     * 获取用户优惠券
     * @param map
     * @return
     */
    List<UserCouponDto> queryUserCouponList(Map<String, Object> map);
}

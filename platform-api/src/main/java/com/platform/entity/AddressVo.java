package com.platform.entity;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:39
 */
public class AddressVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //会员ID
    private Long userId;
    //收货人姓名
    private String contactName;
    //手机
    private String contactMobile;

    //详细收货地址信息
    private String address;

    //默认
    private Integer isDefault = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}

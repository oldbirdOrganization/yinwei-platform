package com.platform.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:39
 */
public class AddressVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //会员ID
    @ApiModelProperty(value = "会员ID")
    private Long userId;
    //收货人姓名
    @ApiModelProperty(value = "收货人姓名")
    private String contactName;
    //手机
    @ApiModelProperty(value = "手机")
    private String contactMobile;

    //详细收货地址信息
    @ApiModelProperty(value = "详细收货地址信息")
    private String address;

    //默认
    private Integer isDefault = 0;

    //性别 1-男 2- 女
    @ApiModelProperty(value = "性别 1-男 2- 女")
    private Integer sex ;
    @ApiModelProperty(value = "地址标签 1-家 2-公司 3-学校")
    //地址标签 1-家 2-公司 3-学校
    private Integer addressIndex;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAddressIndex() {
        return addressIndex;
    }

    public void setAddressIndex(Integer addressIndex) {
        this.addressIndex = addressIndex;
    }
}

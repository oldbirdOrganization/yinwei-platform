package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_address
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-10-02 14:11:24
 */
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 会员名
     */
    private String shopUserName;

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

    //性别 1-男 2- 女
    private Integer sex ;
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

    public String getShopUserName() {
        return shopUserName;
    }

    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName;
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

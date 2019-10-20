package com.platform.entity;

public class ImgVo implements java.io.Serializable{

    /**
     * 图片地址
     */
    private String serviceFrontImgUrl;
    private String serviceLaterImgUrl;

    public String getServiceFrontImgUrl() {
        return serviceFrontImgUrl;
    }

    public void setServiceFrontImgUrl(String serviceFrontImgUrl) {
        this.serviceFrontImgUrl = serviceFrontImgUrl;
    }

    public String getServiceLaterImgUrl() {
        return serviceLaterImgUrl;
    }

    public void setServiceLaterImgUrl(String serviceLaterImgUrl) {
        this.serviceLaterImgUrl = serviceLaterImgUrl;
    }
}

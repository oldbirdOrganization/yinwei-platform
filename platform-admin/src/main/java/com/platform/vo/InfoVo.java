package com.platform.vo;

import java.io.Serializable;

/**
 * @author panchong
 * @Title: InfoVo
 * @Description: TODO
 * @date 2019/10/1215:27
 */
public class InfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String payType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}

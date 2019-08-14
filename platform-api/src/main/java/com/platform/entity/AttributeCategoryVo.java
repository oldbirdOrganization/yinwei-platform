package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:41
 */
public class AttributeCategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String name;
    //
    private Integer enabled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}

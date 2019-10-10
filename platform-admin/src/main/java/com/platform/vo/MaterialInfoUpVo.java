package com.platform.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author panchong
 * @Title: MaterialInfoVo
 * @Description: TODO
 * @date 2019/9/2716:49
 */
public class MaterialInfoUpVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 材料id
     */
    private Integer id;

    private String name;

    private String brand;

    private String model;

    private String unit;

    private BigDecimal price;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

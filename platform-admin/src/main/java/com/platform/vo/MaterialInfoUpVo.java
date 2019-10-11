package com.platform.vo;

import com.platform.utils.excelutils.ExcelColumn;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author panchong
 * @Title: MaterialInfoVo
 * @Description: TODO
 * @date 2019/9/2716:49
 */

@Data
public class MaterialInfoUpVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 材料id
     */
    @ExcelColumn(value = "商品id", col = 1)
    private Integer id;

    @ExcelColumn(value = "商品名称", col = 2)
    private String name;

    @ExcelColumn(value = "品牌", col = 3)
    private String brand;

    @ExcelColumn(value = "规格型号", col = 4)
    private String model;

    @ExcelColumn(value = "单位", col = 5)
    private String unit;

    @ExcelColumn(value = "单价", col = 6)
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

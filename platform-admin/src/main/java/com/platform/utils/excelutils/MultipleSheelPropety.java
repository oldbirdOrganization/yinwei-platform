package com.platform.utils.excelutils;

import java.util.List;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

/**
 * @author panchong
 * @Title: MultipleSheelPropety
 * @Description: TODO
 * @date 2019/9/2718:51
 */
public class MultipleSheelPropety {

    private List<? extends BaseRowModel> data;

    private Sheet sheet;

    public List<? extends BaseRowModel> getData() {
        return data;
    }

    public void setData(List<? extends BaseRowModel> data) {
        this.data = data;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }


}

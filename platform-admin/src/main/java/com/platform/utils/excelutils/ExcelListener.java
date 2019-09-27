package com.platform.utils.excelutils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panchong
 * @Title: ExcelListener
 * @Description: TODO
 * @date 2019/9/2718:58
 */
public class ExcelListener extends AnalysisEventListener<Object> {

    private List<Object> datas = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        // TODO Auto-generated method stub
        if (object != null) {
            datas.add(object);
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源

    }


    public List<Object> getDatas() {
        return datas;
    }


}
